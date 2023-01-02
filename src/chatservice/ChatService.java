package chatservice;


import java.util.ArrayList;
import java.util.StringTokenizer;

public class ChatService {
    public static final String CHAT = "chat";
    public static final String CHATGROUP ="group";
    public static final String GROUP_RECEIVED ="groupchat";
    public static final String CHANGES = "change";
    public static final String DELIMITER = "#";
    public static final String MENUCHAT = "menuchat";
    public static final String CONNECT = "login";
    public static final String DISCONNECT = "logout";
    public static final String GROUP_CHANGES = "groupchange";
    

    public static String createPacket(String signal,int to,String content,String time){
        String toWho = String.valueOf(to);
        return signal + DELIMITER + toWho + DELIMITER + time + DELIMITER+ content;
    }

    public static String createPacket(String signal,int to,String username,String content,String time){
        String toWho = String.valueOf(to);
        return signal + DELIMITER + toWho + DELIMITER + username +DELIMITER + time + DELIMITER+ content;
    }
    
    public static String[] packetAnalysis(String packet){
        if(packet == null){
            return null;
        }
        ArrayList<String> result = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(packet,DELIMITER);
        while(tokenizer.hasMoreTokens()){
            result.add(tokenizer.nextToken());
        }
        return result.toArray(new String[0]);
    }

}
