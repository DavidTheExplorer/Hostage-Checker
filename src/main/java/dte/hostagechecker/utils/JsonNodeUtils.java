package dte.hostagechecker.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class JsonNodeUtils
{
    public static List<JsonNode> asList(JsonNode node)
    {
        if(node.isObject())
            return List.of(node);

        List<JsonNode> children = new ArrayList<>(node.size());

        for(JsonNode child : node)
            children.add(child);

        return children;
    }

    public static JsonNode readTree(ObjectMapper objectMapper, String json)
    {
        try
        {
            return objectMapper.readTree(json);
        }
        catch(JsonProcessingException exception)
        {
            throw new RuntimeException("Can't parse the provided JSON", exception);
        }
    }
}
