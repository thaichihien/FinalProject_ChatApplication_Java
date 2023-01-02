package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UserAccount {
    private int ID;
    private String username;
    private String password;
    private String fullname;
    private String address;
    private String birthDay;
    private String gender;
    private String email;
    private boolean online;
    private String createdAt;
    private String position;
    private boolean banned;

    // Socket
    public Socket clienSocket;
    public PrintWriter pw;
    public BufferedReader br;


    public UserAccount() {
        this.ID = -1;
        this.username = "";
        this.password = "";
        this.fullname = "";
        this.address = "";
        this.birthDay = "";
        this.gender = "";
        this.email = "";
        this.online = false;
        this.position = "";
    }

    public boolean isEmpty(){
        if(username.isEmpty() || password.isEmpty() || email.isEmpty()){
            return true;
        }
        else return false;
    }


    public UserAccount(int ID, String username, String password, String fullname, String address, String birthDay, String gender, String email, boolean online) {
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


    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
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


    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public String toString(){
        String result = String.format("{%d, %s, %s, %s, %b}", this.ID,this.username,this.fullname,this.email,this.online);
        return result;
    }


    public boolean isBanned() {
        return this.banned;
    }

    public boolean getBanned() {
        return this.banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }




    public void sendPacket(String packet){
        pw.println(packet);
    }

    public String receivePacket() throws IOException{
        String packet = br.readLine();
        return packet;
    }


    public Socket getClienSocket() {
        return this.clienSocket;
    }

    public void setClienSocket(Socket clienSocket) {
        this.clienSocket = clienSocket;
    }

    public PrintWriter getPw() {
        return this.pw;
    }

    public void setPw(PrintWriter pw) {
        this.pw = pw;
    }


    public BufferedReader getBr() {
        return this.br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }


}
