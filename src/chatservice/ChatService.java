package chatservice;


import java.util.ArrayList;
import java.util.StringTokenizer;

public class ChatService {
    public static final String CHAT = "c";
    public static final String CHATGROUP ="cg";
    public static final String GROUP_RECEIVED ="gr";
    public static final String CHANGES = "cg";
    public static final String DELIMITER = "#";
    public static final String MENUCHAT = "mc";
    public static final String CONNECT = "i";
    public static final String DISCONNECT = "o";
    public static final String GROUP_CHANGES = "gc";
    

    // FOR ADMIN
    public static final String ACCOUNT_MANAGER_CHANGES = "am";
    public static final String LOGIN_HISTORY_CHANGES = "lh";
    public static final String GROUP_MANAGER_CHANGES = "gm";

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
