package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;

public class MessageController {

    private HashSet<Message> messagesSeen;
    // why a HashSet??

    private ObjectMapper objectMapper = new ObjectMapper();

    public ArrayList<Message> getMessages() {
        String getMsgsUrl = ServerController.urlGet("/messages");
        try {
            return objectMapper
                    .readValue(getMsgsUrl, new TypeReference<ArrayList<Message>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public ArrayList<Message> getMessagesForId(Id id) {
        String getMsgsUrl = ServerController.urlGet("/ids/" + id.getName() + "/messages");
        try {
            return objectMapper
                    .readValue(getMsgsUrl, new TypeReference<ArrayList<Message>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public Message getMessageForSequence(Id id, String seq) {
        String getMsgsUrl = ServerController.urlGet("/ids/" + id.getName() + "/messages/" + seq);
        try {
            return objectMapper.readValue(getMsgsUrl, Message.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        String getMsgsUrl = ServerController.urlGet("/ids/" + myId.getName()
                + "/from/" + friendId.getName());
        try {
            return objectMapper.readValue(getMsgsUrl, new TypeReference<ArrayList<Message>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        String postMsgsUrl;
        String message;
        try {
            message = objectMapper.writeValueAsString(msg);
            postMsgsUrl = ServerController.urlPost("/ids" + myId.getName() + "/messages", message);
            return objectMapper.readValue(postMsgsUrl, Message.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
 
}