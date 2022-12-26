package chatservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ChatService {
    public static final String CHAT = "chat";
    public static final String CHANGES = "change";
    public static final String DELIMITER = "#";
    public static final String MENUCHAT = "menuchat";

    public static String createPacket(String signal,int to,String content,String time){
        String toWho = String.valueOf(to);
        return signal + DELIMITER + toWho + DELIMITER + time + DELIMITER+ content;
    }
    
    public static String[] packetAnalysis(String packet){
        ArrayList<String> result = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(packet,DELIMITER);
        while(tokenizer.hasMoreTokens()){
            result.add(tokenizer.nextToken());
        }
        return result.toArray(new String[0]);
    }

}
