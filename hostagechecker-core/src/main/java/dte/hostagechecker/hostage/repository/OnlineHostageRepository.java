package dte.hostagechecker.hostage.repository;

import dte.hostagechecker.hostage.Hostage;
import dte.hostagechecker.utils.JsonNodeUtils;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public abstract class OnlineHostageRepository implements HostageRepository
{
    private final URI endpoint;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final JsonMapper jsonMapper = new JsonMapper();

    protected OnlineHostageRepository(URI endpoint)
    {
        this.endpoint = endpoint;
    }

    @Override
    public CompletableFuture<Collection<Hostage>> getHostages()
    {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(this.endpoint)
                .build();

        return this.httpClient.sendAsync(request, BodyHandlers.ofString())
                .thenApply(this::toHostageList);
    }

    private Collection<Hostage> toHostageList(HttpResponse<String> response)
    {
        JsonNode hostageArray = navigateToHostageArray(this.jsonMapper.readTree(response.body()));

        return JsonNodeUtils.asList(hostageArray).stream()
                .map(this::parseHostage)
                .toList();
    }

    protected abstract JsonNode navigateToHostageArray(JsonNode bodyNode);
    protected abstract Hostage parseHostage(JsonNode hostageNode);
}
