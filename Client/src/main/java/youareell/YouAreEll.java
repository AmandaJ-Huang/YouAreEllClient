package youareell;

import controllers.*;

public class YouAreEll {

    private TransactionController tt;
    private String rootURL = "http://zipcode.rocks:8085";

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
        JsonController jsonController = new JsonController(this.rootURL, url);
        return jsonController.idGet(message);
    }

}
