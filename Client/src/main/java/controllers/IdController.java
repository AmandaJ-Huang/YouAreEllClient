package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.bind.v2.model.core.ID;
import models.Id;

public class IdController {
    private HashMap<String, Id> allIds;
    private ObjectMapper objectMapper = new ObjectMapper();

    private Id myId;

    public Id getMyId() {
        return myId;
    }

    public ArrayList<Id> getIds() {
        String getIdsUrl = ServerController.urlGet("/ids");
        try {
            return objectMapper
                    .readValue(getIdsUrl, new TypeReference<ArrayList<Id>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Id postId(Id id) {
        // create json from id
        // call server, get json result Or error
        // result json to Id obj

        String postIdsUrl;
        String json;
        try {
            json = objectMapper.writeValueAsString(id);
            postIdsUrl = ServerController.urlPost("/ids", json);
            myId = objectMapper.readValue(postIdsUrl, Id.class);
            return myId;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Id putId(Id id) {
        String putIdsUrl;
        String json;
        try {
            json = objectMapper.writeValueAsString(id);
            putIdsUrl = ServerController.urlPut("/ids", json);
            myId = objectMapper.readValue(putIdsUrl, Id.class);
            return myId;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
 
}