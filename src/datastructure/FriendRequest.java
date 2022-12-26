package datastructure;

public class FriendRequest {
    private int fromID;
    private String fromName;
    private int toID;
    private String status;
    private int tryTime;
    

    public FriendRequest() {
    }


    public FriendRequest(int fromID, int toID, String status, int tryTime) {
        this.fromID = fromID;
        this.toID = toID;
        this.status = status;
        this.tryTime = tryTime;
    }

    public String getFromName() {
        return this.fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
    

    public int getFromID() {
        return this.fromID;
    }

    public void setFromID(int fromID) {
        this.fromID = fromID;
    }

    public int getToID() {
        return this.toID;
    }

    public void setToID(int toID) {
        this.toID = toID;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTryTime() {
        return this.tryTime;
    }

    public void setTryTime(int tryTime) {
        this.tryTime = tryTime;
    }
    



}
