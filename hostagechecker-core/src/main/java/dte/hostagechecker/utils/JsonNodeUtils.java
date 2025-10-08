package dte.hostagechecker.utils;

import tools.jackson.databind.JsonNode;

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
}
