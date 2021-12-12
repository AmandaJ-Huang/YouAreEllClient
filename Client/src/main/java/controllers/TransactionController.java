package controllers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private MessageController msgCtrl;
    private IdController idCtrl;

    public TransactionController(MessageController m, IdController j) {
        this.msgCtrl = m;
        this.idCtrl = j;
    }

    @GetMapping
    public List<Id> getIds() {
        return idCtrl.getIds();
    }

    public String postId(String idtoRegister, String githubName) {
        Id tid = new Id(idtoRegister, githubName);
        tid = idCtrl.postId(tid);
        return ("Id registered.");
    }

    public String makecall(String url, String command, String message) throws Exception {
        //        URL urlObj = new URL(this.rootURL + url);
        //        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        //        con.setRequestMethod(command);
        //        return con.getResponseMessage();
        //      ObjectMapper objectMapper = new ObjectMapper();
//        URL urlObj = new URL(this.rootURL + url);
//        return objectMapper.readValue(urlObj, String.class);

        JsonController jsonController = new JsonController(this.rootURL, url);
        return jsonController.idGet(message);
    }
}
