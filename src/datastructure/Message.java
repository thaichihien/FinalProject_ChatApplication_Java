package datastructure;

import java.sql.Date;

public class Message {
    private String name;
    private Date time;
    private String content;

    public Message(String name, Date time, String content) {
        this.name = name;
        this.time = time;
        this.content = content;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}
