package dte.hostagechecker.hostage.listprovider;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.machinezoo.noexception.Exceptions;
import dte.hostagechecker.hostage.Hostage;
import dte.hostagechecker.jackson.JsonNodeUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public abstract class OnlineJsonProvider extends AbstractListProvider
{
    private final URI endpoint;

    private final HttpClient httpClient = HttpClient.newBuilder().build();

    private final JsonMapper jsonMapper = new JsonMapper();

    protected OnlineJsonProvider(String name, URI endpoint)
    {
        super(name);
        this.endpoint = endpoint;
    }

    @Override
    public CompletableFuture<Collection<Hostage>> fetchHostages()
    {
        return createRequest().thenApply(this::toHostageList);
    }

    private CompletableFuture<HttpResponse<String>> createRequest()
    {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(this.endpoint)
                .build();

        return this.httpClient.sendAsync(request, BodyHandlers.ofString());
    }

    private Collection<Hostage> toHostageList(HttpResponse<String> response)
    {
        try
        {
            JsonNode bodyNode = this.jsonMapper.readTree(response.body());

            return JsonNodeUtils.asList(navigateToHostageArray(bodyNode)).stream()
                    .map(Exceptions.sneak().function(this::parseHostage))
                    .toList();
        }
        catch(Exception exception)
        {
            throw new RuntimeException(exception);
        }
    }

    protected abstract JsonNode navigateToHostageArray(JsonNode bodyNode) throws Exception;

    protected abstract Hostage parseHostage(JsonNode hostageNode) throws Exception;
}
