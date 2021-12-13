package controllers;

import models.Id;
import models.Message;

import java.util.ArrayList;
import java.util.List;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private MessageController msgCtrl;
    private IdController idCtrl;

    public TransactionController(MessageController m, IdController j) {
        this.msgCtrl = m;
        this.idCtrl = j;
    }

    public List<Id> getIds() {
        return idCtrl.getIds();
    }

    public String postId(String idtoRegister, String githubName) {
        Id tid = new Id(idtoRegister, githubName);
        tid = idCtrl.postId(tid);
        return ("Id registered.");
    }

    public String putID(String changeName, String githubName) {
        Id cid = new Id(changeName, idCtrl.getMyId().getGithub());

        return ("Name changed.");
    }

    public List<Message> getAllMessages() {
        return msgCtrl.getMessages();
    }

    public List<Message> getMessagesById(String githubName) {
        return msgCtrl.getMessagesForId(githubName);
    }

    public Message getMessageBySequence(String githubName, String seq) {
        return msgCtrl.getMessageForSequence(githubName, seq);
    }

    public ArrayList<Message> getMessageFromFriend(String githubName, String friendName) {
        return msgCtrl.getMessagesFromFriend(githubName, friendName);
    }

    public Message postMessage(String githubName, String message) {
        Message send = new Message(message, githubName);
        return msgCtrl.postMessage(send);
    }

    public Message postMessageToFriend(String githubName, String friendName, String message) {
        Message send = new Message(message, githubName, friendName);
        return msgCtrl.postMessage(send);
    }

    public String makecall(String url, String command, String message) throws Exception {
        JsonController jsonController = new JsonController(this.rootURL, url);
        return jsonController.idGet(message);
    }
}
