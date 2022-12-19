package datastructure;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class UserAccount {
    private int ID;
    private String username;
    private String password;
    private String fullname;
    private String address;
    private String birthDay;
    private boolean gender;
    private String email;
    private boolean online;


    public UserAccount() {
        this.ID = -1;
        this.username = "";
        this.password = "";
        this.fullname = "";
        this.address = "";
        this.birthDay = "";
        this.gender = false;
        this.email = "";
        this.online = false;
    }

    public boolean isEmpty(){
        if(username.isEmpty() || password.isEmpty() || email.isEmpty()){
            return false;
        }
        else return true;
    }


    public UserAccount(int ID, String username, String password, String fullname, String address, String birthDay, boolean gender, String email, boolean online) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.birthDay = birthDay;
        this.gender = gender;
        this.email = email;
        this.online = online;
    }
    
    
    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDay() {
        return this.birthDay;
    }

    public String getBirthDayString() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String value = df.format(this.birthDay);
        return value;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public boolean isMale() {
        return this.gender;
    }

    public boolean getGender() {
        return this.gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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


}
