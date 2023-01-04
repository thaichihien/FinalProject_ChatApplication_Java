package chatservice;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    ServerSocket server;
    final int PORT = 500;

    public static HashMap<Integer,ClientRoom> clientList = new HashMap<>();

    public Server(){
        try {
            server = new ServerSocket(PORT);
        } catch (IOException ie) {
            System.out.println("Cannot open socket." + ie);
            System.exit(1);
        }
        System.out.println("ServerSocket is created " + server);
        listening();
    }

    public void listening(){
        while(true) {
            try {
                System.out.println("Waiting for connect request...");
                Socket client = server.accept();
                
                ClientRoom newClient = new ClientRoom(client);
                //clientList.add(newClient);
                newClient.start();
   
            } catch (IOException ie) {

                try {
                    server.close();
                } catch (IOException e) {        
                    e.printStackTrace();
                }
                break;
            }
        }
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Server();
    }


}
