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
    public ArrayList<Message> getMessagesForId(String github) {
        String getMsgsUrl = ServerController.urlGet("/ids/" + github + "/messages");
        try {
            return objectMapper
                    .readValue(getMsgsUrl, new TypeReference<ArrayList<Message>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public Message getMessageForSequence(String github, String seq) {
        String getMsgsUrl = ServerController.urlGet("/ids/" + github + "/messages/" + seq);
        try {
            return objectMapper.readValue(getMsgsUrl, Message.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(String github, String friendName) {
        String getMsgsUrl = ServerController.urlGet("/ids/" + github
                + "/from/" + friendName);
        try {
            return objectMapper.readValue(getMsgsUrl, new TypeReference<ArrayList<Message>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Message postMessage(Message msg) {
        String postMsgsUrl;
        String message;
        try {
            message = objectMapper.writeValueAsString(msg);
            postMsgsUrl = ServerController.urlPost("/ids" + msg.getFromId() + "/messages", message);
            return objectMapper.readValue(postMsgsUrl, Message.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
 
}