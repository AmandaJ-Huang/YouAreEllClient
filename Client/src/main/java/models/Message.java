package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * POJO for an Message object
 *
 *   {
    "sequence": "-",
    "timestamp": "_",
    "fromid": "xt0fer",
    "toid": "kristofer",
    "message": "Hello, Kristofer!"
  },

*
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Message implements Comparable {

    @JsonProperty("message")
    private String message = "";
    @JsonProperty("toid")
    private String toId = "";
    @JsonProperty("fromid")
    private String fromId = "";

    @JsonIgnoreProperties
    private String timestamp = "";
    @JsonProperty("sequence")
    private String seqId = "";

    public Message () {
    }

    public Message (String message, String fromId) {
        this(message, fromId, "", null, "");
    }

    public Message (String message, String fromId, String toId) {
        this(message, fromId, toId, null, "");
    }

    public Message (String message, String fromId, String toId, String timestamp, String seqId) {
        this.message = message;
        this.fromId = fromId;
        this.toId = toId;
        this.timestamp = timestamp;
        this.seqId = seqId;
    }

    public int compareTo(Object o) {
        return this.seqId.compareTo(((Message) o).getSeqId());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSeqId() {
        return seqId;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("\n\t{")
                .append("\n\t\tsequence: " + this.seqId)
                .append("\n\t\ttimestamp: " + this.timestamp)
                .append("\n\t\tfrom: " + this.fromId)
                .append("\n\t\tto: " + this.toId)
                .append("\n\t\tmessage: " + this.message)
                .append("\n\t},")
                .toString();
    }
}