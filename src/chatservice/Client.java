package chatservice;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    Socket client;
    final int PORT = 500;

    public Client(){
        try {
            client = new Socket(InetAddress.getLocalHost(), PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Client socket is created " + client);
        connect();
    }

    public void connect(){

    }

    public static void main(String[] args) {
        new Client();
    }



}
