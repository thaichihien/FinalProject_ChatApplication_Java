package chatservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.SwingUtilities;

import adminchatapp.MainFormAdmin;


public class AdminClient {
    Socket client;
    final int PORT = 500;
    PrintWriter pw;
    BufferedReader br;

    public AdminClient(){
        try {
            client = new Socket(InetAddress.getLocalHost(), PORT);
            OutputStream clientOut = client.getOutputStream();
            pw = new PrintWriter(new OutputStreamWriter(clientOut, "UTF-8"), true);
   
            // Create an input stream of the client socket
            InputStream clientIn = client.getInputStream();
            br = new BufferedReader(new InputStreamReader(clientIn,"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Client socket is created " + client);
        connect();
    }

    public void connect(){

              

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Admin login");
                    MainFormAdmin adminForm = new MainFormAdmin(client, pw, br);
                    adminForm.setVisible(true);
                    
                }
            });

    }

    public static void main(String[] args) {
        new AdminClient();
    }



}
