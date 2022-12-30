package chatservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;

import database.DatabaseManagment;
import datastructure.Message;
import datastructure.UserAccount;
import uichatcomponent.ChatBoxUser;

public class ClientRoom extends Thread {

    public Socket clientSocket;
    public int ID;
    public String username;
    private DatabaseManagment database;
   



    public ClientRoom(Socket socket) {
        clientSocket = socket;
        database = DatabaseManagment.getInstance();
    }

    @Override
    public void run() {
      try {
       
        System.out.println("Connect request is accepted...");
        String clientHost = clientSocket.getInetAddress().getHostAddress();
        int clientPort = clientSocket.getPort();
        System.out.println("Client host = " + clientHost + " Client port = " + clientPort);

        InputStream clientIn = clientSocket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(clientIn,"UTF-8"));
        
        OutputStream validateOut = this.clientSocket.getOutputStream();
        PrintWriter pwValidate = new PrintWriter(new OutputStreamWriter(validateOut, "UTF-8"), true);

        

        String idFromClient = "";
        int idConverted = -1;
        while(true){
            idFromClient = br.readLine();
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
       
        this.username = database.getUsername(this.ID);

        String msgFromClient= "";
        while(true){
            try {
                msgFromClient = br.readLine();

                //handle message from user
                // - send to other 
                // - disconect
                //- send to group
                handleMessage(msgFromClient);


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

    public void handleMessage(String message){
        System.out.println("received from " + String.valueOf(ID) +" : " + message );
        String[] allMessage = ChatService.packetAnalysis(message);


        System.out.println("messafe[0] : " + allMessage[0]);
        try {
            if(allMessage[0].equals(ChatService.CHAT)){ // chat#ID#time#message
                int IDtoSend = Integer.parseInt(allMessage[1]);
                Message messageUser = new Message();
                messageUser.setChatboxID(ChatBoxUser.createChatBoxUserID(this.ID, IDtoSend));
                messageUser.setDateSend(allMessage[2]);
                messageUser.setContent(allMessage[3]);
                database.saveMessageUser(messageUser, this.ID, IDtoSend);

                if(Server.clientList.containsKey(IDtoSend)){
                    ClientRoom friendRoom = Server.clientList.get(IDtoSend);
                    OutputStream clientOut = friendRoom.clientSocket.getOutputStream();
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(clientOut, "UTF-8"), true);
                    String packetSend = ChatService.createPacket(ChatService.CHAT, ID, messageUser.getContent(), messageUser.getDateSend());
                    System.out.println(packetSend);
                    pw.println(packetSend);
                }
                
            }
            else if(allMessage[0].equals(ChatService.CHANGES)){
                OutputStream clientOut = this.clientSocket.getOutputStream();
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(clientOut, "UTF-8"), true);
                String timeSend = allMessage[2];
                String messageSend = allMessage[3];
                String packetSend = ChatService.createPacket(ChatService.CHANGES, ID, messageSend, timeSend);
                System.out.println(packetSend);
                pw.println(packetSend);


                int IDtoSend = Integer.parseInt(allMessage[1]);
                if(Server.clientList.containsKey(IDtoSend) && IDtoSend != this.ID){
                    ClientRoom friendRoom = Server.clientList.get(IDtoSend);
                    OutputStream otherStream = friendRoom.clientSocket.getOutputStream();
                    PrintWriter otherPw = new PrintWriter(new OutputStreamWriter(otherStream, "UTF-8"), true);
                    otherPw.println(packetSend);
                }
                
            }
            else if(allMessage[0].equals(ChatService.CHATGROUP)){
                int groupID = Integer.parseInt(allMessage[1]);
                Message messageGroup = new Message();
                messageGroup.setGroupID(groupID);
                messageGroup.setDateSend(allMessage[2]);
                messageGroup.setContent(allMessage[3]);
                database.saveMessageGroup(messageGroup,this.ID);

                ArrayList<UserAccount> userInGroup = database.getAllGroupMembers(groupID);
                for(UserAccount user : userInGroup){
                    if(Server.clientList.containsKey(user.getID()) && user.getID() != this.ID){
                        ClientRoom friendRoom = Server.clientList.get(user.getID());
                        OutputStream clientOut = friendRoom.clientSocket.getOutputStream();
                        PrintWriter pw = new PrintWriter(new OutputStreamWriter(clientOut, "UTF-8"), true);
                        String packetSend = ChatService.createPacket(ChatService.GROUP_RECEIVED, groupID, this.username, messageGroup.getContent(), messageGroup.getDateSend());
                        System.out.println(packetSend);
                        pw.println(packetSend);
                    }
                }

            }
            else{
                System.out.println("error message");
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            try {
                this.clientSocket.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Server.clientList.remove(this.ID);
            e.printStackTrace();
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
