package framework.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class JsonBuilder {
    ObjectMapper mapper;
    JsonNode rootNode;

    public JsonBuilder(){
        mapper = new ObjectMapper();
        rootNode = mapper.createObjectNode();
    }

    public JsonBuilder putIntInRoot(String name, int value){
        ((ObjectNode) rootNode).put(name, value);
        return this;
    }

    public JsonBuilder putLongInRoot(String name, Long value){
        ((ObjectNode) rootNode).put(name, value);
        return this;
    }

    public JsonBuilder putStringInRoot(String name, String value){
        ((ObjectNode) rootNode).put(name, value);
        return this;
    }

    public JsonNode getJsonObj(){
        return rootNode;
    }

    public String getJsonString(){
        return rootNode.toString();
    }

    public static JsonNode convertStringToJson(String string) {
        JsonNode actualObj = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            actualObj = mapper.readTree(string);
        } catch (IOException e) {
            AllureLogger.logWarning("Can't convert String to JsonNode: " + string);
            e.printStackTrace();
        }
        return actualObj;
    }
}
