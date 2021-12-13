package controllers;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

public class ServerController {
    private static String rootURL = "http://zipcode.rocks:8085";
    private ServerController svr = new ServerController();

    private ServerController() {}

    public ServerController shared() {
        return svr;
    }

    public static String urlGet(String subDir) {
        // url -> /ids/
        // send the server a get with url
        // return json from server
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";

        try {
            HttpGet request = new HttpGet(rootURL + subDir);
            CloseableHttpResponse response = httpClient.execute(request);

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result = EntityUtils.toString(entity);
            }

            response.close();
            httpClient.close();

        } catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }

    public static String urlPost(String subDir, String json) throws IOException {
        // url -> /ids/
        // create json from Id
        // request
        // reply
        // return json
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";

        try {
            HttpPost post = new HttpPost(rootURL + subDir);
            CloseableHttpResponse response = httpClient.execute(post);

            post.setEntity(new StringEntity(json));

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result = EntityUtils.toString(entity);
            }

            response.close();
            httpClient.close();

        } catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }

    public static String urlPut(String subDir, String json) {
        // url -> /ids/
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";

        try {
            HttpPut put = new HttpPut(rootURL + subDir);
            CloseableHttpResponse response = httpClient.execute(put);

            put.setEntity(new StringEntity(json));

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                result = EntityUtils.toString(entity);
            }

            response.close();
            httpClient.close();

        } catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }


}

// ServerController.shared.doGet()