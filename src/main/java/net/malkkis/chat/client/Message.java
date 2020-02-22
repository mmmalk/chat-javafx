package net.malkkis.chat.client;

import java.io.Serializable;

public class Message implements Serializable {


    private final String sender;
    private final header header;
    private final String content;

    public Message(String sender, header header){
        this.sender = sender;
        this.header = header;
        this.content = null;
    }

    public Message(String sender, header header, String content){
        this.sender = sender;
        this.header = header;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public header getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }

    public enum header {
        CONNECT,
        DISCONNECT,
        MESSAGE
    }


}
