package datastructure;

import java.util.ArrayList;

public class GroupChat {
    private int ID;
    private String groupname;
    private boolean online;
    private String createdAt;
    ArrayList<UserAccount> members;
    ArrayList<UserAccount> admins;


    public GroupChat() {
        this.groupname = "";
        this.ID = -1;
        this.members = new ArrayList<>();
        this.admins = new ArrayList<>();
    }


    public GroupChat(int ID, String groupname, boolean online, ArrayList<UserAccount> members, ArrayList<UserAccount> admins) {
        this.ID = ID;
        this.groupname = groupname;
        this.online = online;
        this.members = members;
        this.admins = admins;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    


    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getGroupname() {
        return this.groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public boolean isOnline() {
        return this.online;
    }

    public boolean getOnline() {
        return this.online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public ArrayList<UserAccount> getMembers() {
        return this.members;
    }

    public void setMembers(ArrayList<UserAccount> members) {
        this.members = members;
    }


    public ArrayList<UserAccount> getAdmins() {
        return this.admins;
    }

    public void setAdmins(ArrayList<UserAccount> admins) {
        this.admins = admins;
    }

    public boolean isEmpty(){
        if(this.groupname.isEmpty() || this.admins.isEmpty()){
            return false;
        }
        else return true;
    }



}
