package dte.hostagechecker.hostage.listprovider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import dte.hostagechecker.hostage.Hostage;
import dte.hostagechecker.utils.JsonNodeUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public abstract class OnlineListProvider implements HostageListProvider
{
    private final URI endpoint;
    private final HttpClient httpClient = HttpClient.newBuilder().build();
    private final JsonMapper jsonMapper = new JsonMapper();

    protected OnlineListProvider(URI endpoint)
    {
        this.endpoint = endpoint;
    }

    @Override
    public CompletableFuture<Collection<Hostage>> fetchHostages()
    {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(this.endpoint)
                .build();

        return this.httpClient.sendAsync(request, BodyHandlers.ofString())
                .thenApply(this::toHostageList);
    }

    private Collection<Hostage> toHostageList(HttpResponse<String> response)
    {
        JsonNode hostageArray = navigateToHostageArray(JsonNodeUtils.readTree(this.jsonMapper, response.body()));

        return JsonNodeUtils.asList(hostageArray).stream()
                .map(this::parseHostage)
                .toList();
    }

    protected abstract JsonNode navigateToHostageArray(JsonNode bodyNode);
    protected abstract Hostage parseHostage(JsonNode hostageNode);
}
