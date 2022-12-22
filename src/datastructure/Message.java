package datastructure;



public class Message {
    private String chatboxID;
    private String userName;
    private String dateSend;
    private String content;
    private int visible_only;

    public final int NOT_HIDE = -90;

    public Message() {
    }


    public Message(String chatboxID, String userName, String dateSend, String content, int visible_only) {
        this.chatboxID = chatboxID;
        this.userName = userName;
        this.dateSend = dateSend;
        this.content = content;
        this.visible_only = visible_only;
    }


    public String getChatboxID() {
        return this.chatboxID;
    }

    public void setChatboxID(String chatboxID) {
        this.chatboxID = chatboxID;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateSend() {
        return this.dateSend;
    }

    public void setDateSend(String dateSend) {
        this.dateSend = dateSend;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVisible_only() {
        return this.visible_only;
    }

    public void setVisible_only(int visible_only) {
        this.visible_only = visible_only;
    }
   


}