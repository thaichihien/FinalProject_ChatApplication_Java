package datastructure;

public class LoginHistory {
    private int ID;
    private int userID;
    private String userName;
    private String loginTime;

    public LoginHistory() {
        this.ID = -1;
        this.userID = -1;
        this.loginTime = "";

    }

    public LoginHistory(int userID, String loginTime) {
        this.ID = -1;
        this.userID = userID;
        this.loginTime = loginTime;
    }



    public LoginHistory(int ID, int userID, String loginTime) {
        this.ID = ID;
        this.userID = userID;
        this.loginTime = loginTime;
    }


    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

}
