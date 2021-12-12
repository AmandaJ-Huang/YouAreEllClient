package youareell;

import controllers.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class YouAreEll {

    TransactionController tt;

    public YouAreEll (TransactionController t) {
        this.tt = t;
    }

    public YouAreEll (MessageController m, IdController ic) {
        this.tt = new TransactionController(m, ic);
    }

    public static void main(String[] args) {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(
            new TransactionController(
                new MessageController(), new IdController()
        ));
        try {
            System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
            System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get_ids() throws Exception {
        return tt.makecall("/ids", "GET", "");
    }

    public String get_messages() throws Exception {
        return MakeURLCall("/messages", "GET", "");
    }

    public String MakeURLCall(String url, String command, String message) throws Exception {
        URL urlObj = new URL("http://zipcode.rocks:8085" + url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod(command);
        return con.getResponseMessage();
    }




}
