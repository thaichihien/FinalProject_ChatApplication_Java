package chatservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class ClientRoom extends Thread {

    public Socket clientSocket;
    public String name;
    private BufferedReader br;
    private PrintWriter pw;



    public ClientRoom(Socket socket) {
        clientSocket = socket;
        System.out.println("Connect request is accepted...");
        String clientHost = clientSocket.getInetAddress().getHostAddress();
        int clientPort = clientSocket.getPort();
        System.out.println("Client host = " + clientHost + " Client port = " + clientPort);

        // Read data from the client
        try {
            InputStream clientIn = clientSocket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(clientIn,"UTF-8"));
        
        OutputStream validateOut = this.clientSocket.getOutputStream();
        PrintWriter pwValidate = new PrintWriter(new OutputStreamWriter(validateOut, "UTF-8"), true);
       

        String nameFromClient = "";
        while(true){
            nameFromClient = br.readLine();
            
            if(Server.clientList.containsKey(nameFromClient)){
                String ansMsg = "UsernameExisted";
                pwValidate.println(ansMsg);
            }
            else{
                String ansMsg = "UsernameOK";
                pwValidate.println(ansMsg);
                break;
            }
        }
        this.name = nameFromClient;
        Server.clientList.put(this.name,this);

        System.out.println(this.name + " is connected");
        boardcasting(this.name, this.name + " has joined");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {
      try {
       

        String msgFromClient= "";
        while(true){
            try {
                msgFromClient = br.readLine();

               boardcasting("empty", name + ": " + msgFromClient);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        this.clientSocket.close();
        Server.clientList.remove(this.name);
        boardcasting("empty", name + " is disconnected");
      } catch (IOException e) {
        e.printStackTrace();
        try {
            clientSocket.close();
        } catch (IOException e1) {
            
            e1.printStackTrace();
        }
        
      }
    }

    public void boardcasting(String except,String msgFromClient){
        for(Map.Entry<String,ClientRoom>  other : Server.clientList.entrySet()){
            if(!other.getKey().equals(except)){
                try {
                    OutputStream clientOut = other.getValue().clientSocket.getOutputStream();
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(clientOut, "UTF-8"), true);
                    String ansMsg = msgFromClient;
                    pw.println(ansMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
        }
    }
    
}
