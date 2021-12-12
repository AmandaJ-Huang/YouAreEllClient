package controllers;

import org.springframework.web.bind.annotation.*;

public class ServerController {
    private String rootURL = "http://zipcode.rocks:8085";
    private ServerController svr = new ServerController();

    private ServerController() {}

    public ServerController shared() {
        return svr;
    }

    @GetMapping(value = "/read/{id}")
    public String idGet() {
        // url -> /ids/
        // send the server a get with url
        // return json from server
        return null;
    }

    @PostMapping(value="/create")
    public String idPost(@RequestBody Long id) {
        // url -> /ids/
        // create json from Id
        // request
        // reply
        // return json
        return null;
    }

    @PutMapping(value="/update/{id}")
    public String idPut(Long id) {
        // url -> /ids/
        return null;
    }


}

// ServerController.shared.doGet()