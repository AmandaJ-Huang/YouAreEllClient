package controllers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonController {
    private JsonFactory factory;
    private JsonParser parser;
    private URL urlObj;

    public JsonController() {
    }

    public JsonController (String url, String address) throws Exception {
        this.urlObj = new URL(url + address);
        this.factory = new JsonFactory();
        this.parser = factory.createParser(urlObj);
    }

    public String idGet(String message) throws IOException {
        StringBuilder messageBuilder = new StringBuilder(message);
        while(!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();
            if(JsonToken.START_ARRAY.equals(jsonToken)){
                messageBuilder.append("[\n");
            } else if (JsonToken.END_ARRAY.equals(jsonToken)) {
                messageBuilder.append("]\n");
                break;
            } else if(JsonToken.START_OBJECT.equals(jsonToken)){
                messageBuilder.append("\t{\n");
            } else if(JsonToken.END_OBJECT.equals(jsonToken)){
                messageBuilder.append("\t}\n");
            } else {
                String fieldName = parser.getCurrentName();
                parser.nextToken();
                messageBuilder.append("\t\t" + fieldName + " : "
                        + parser.getValueAsString() + "\n");
            }
        }
        return messageBuilder.toString();
    }

    public void postIds() throws Exception{
        URL urlObj = new URL("http://zipcode.rocks:8085/ids");
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");

        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
        JSONObject obj = new JSONObject();
        obj.put("github","AmandaJ-Huang");
        obj.put("name","Amanda Huang");
        writer.write(obj.toString());
        writer.flush();
        con.connect();
        System.out.println(con.getResponseMessage());
    }

}
