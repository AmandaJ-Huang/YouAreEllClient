package controllers;

import models.Id;

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

    public String makecall(String url, String command, String message) throws Exception {
        JsonController jsonController = new JsonController(this.rootURL, url);
        return jsonController.idGet(message);
    }
}
