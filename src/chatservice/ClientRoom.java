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
    public int ID;
    private BufferedReader br;
    private PrintWriter pw;



    public ClientRoom(Socket socket) {
        clientSocket = socket;
    }

    @Override
    public void run() {
      try {
       
        System.out.println("Connect request is accepted...");
        String clientHost = clientSocket.getInetAddress().getHostAddress();
        int clientPort = clientSocket.getPort();
        System.out.println("Client host = " + clientHost + " Client port = " + clientPort);

        InputStream clientIn = clientSocket.getInputStream();
        BufferedReader br_getID = new BufferedReader(new InputStreamReader(clientIn,"UTF-8"));
        
        OutputStream validateOut = this.clientSocket.getOutputStream();
        PrintWriter pwValidate = new PrintWriter(new OutputStreamWriter(validateOut, "UTF-8"), true);

        

        String idFromClient = "";
        int idConverted = -1;
        while(true){
            idFromClient = br_getID.readLine();
            idConverted = Integer.parseInt(idFromClient);
            if(Server.clientList.containsKey(idConverted)){
                System.out.println("This ID is already online");
                String ans = "IDEXIST";
                pwValidate.println(ans);
            }
            else{
                String ans = "OK";
                pwValidate.println(ans);
                break;
            }
           
        }
        this.ID = idConverted;
        Server.clientList.put(this.ID,this);


        String msgFromClient= "";
        while(true){
            try {
                msgFromClient = br.readLine();

                //handle message from user
                // - send to other 
                // - disconect
                //- send to group


               //boardcasting("empty", name + ": " + msgFromClient);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        this.clientSocket.close();
        Server.clientList.remove(this.ID);
        //boardcasting("empty", name + " is disconnected");
      } catch (IOException e) {
        e.printStackTrace();
        try {
            clientSocket.close();
        } catch (IOException e1) {
            
            e1.printStackTrace();
        }
        
      }
    }

    // public void boardcasting(String except,String msgFromClient){
    //     for(Map.Entry<String,ClientRoom>  other : Server.clientList.entrySet()){
    //         if(!other.getKey().equals(except)){
    //             try {
    //                 OutputStream clientOut = other.getValue().clientSocket.getOutputStream();
    //                 PrintWriter pw = new PrintWriter(new OutputStreamWriter(clientOut, "UTF-8"), true);
    //                 String ansMsg = msgFromClient;
    //                 pw.println(ansMsg);
    //             } catch (IOException e) {
    //                 e.printStackTrace();
    //             }
    //         }
            
    //     }
    // }
    
}
