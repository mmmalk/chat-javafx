package net.malkkis.portfolio;

import java.io.Serializable;

public class Message implements Serializable {


    final String sender;
    final String header;
    final String content;

    public Message(String sender, String header, String content){
        this.sender = sender;
        this.header = header;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public String getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }


}
