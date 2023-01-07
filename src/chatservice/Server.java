package chatservice;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Server {
    ServerSocket server;
    final int PORT = 500;

    public static HashMap<Integer,ClientRoom> clientList = new HashMap<>();
    JFrame serverFrame;
    public static JTextArea loggingArea;
    JButton turnOff;


    public Server(){
        

        try {
            server = new ServerSocket(PORT);
        } catch (IOException ie) {
            System.out.println("Cannot open socket." + ie);
            //loggingArea.append("Cannot open socket." + ie.getMessage() +  "\n");
            System.exit(1);
        }
        System.out.println("ServerSocket is created " + server);
        
        listening();
    }

    public void listening(){
        SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
                UIforServer();
			}
        });
       

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
            loggingArea.append(e.getMessage() + "\n");
            e.printStackTrace();
        }
    }


    public void UIforServer() {
        serverFrame = new JFrame();
        serverFrame.setTitle("Server");
        serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        serverFrame.setSize(500,300);
        serverFrame.setLocationRelativeTo(null);
        serverFrame.setVisible(true);

        loggingArea = new JTextArea();
        loggingArea.setLineWrap(true);
        loggingArea.setWrapStyleWord(true);
        loggingArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(loggingArea);
        serverFrame.add(scrollPane,BorderLayout.CENTER);
        turnOff = new JButton("Shut down server");
        turnOff.setPreferredSize(new Dimension(0,50));
        serverFrame.add(turnOff,BorderLayout.SOUTH);

        serverFrame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeAllSocket();
                super.windowClosing(e);
            }
        });

        turnOff.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeAllSocket();
				serverFrame.dispose();
			} 
        });

        loggingArea.append("ServerSocket is created\n");
        loggingArea.append("Waiting for connect request...\n");

    }

    public void closeAllSocket(){
        
        try {
			server.close();
		} catch (IOException e1) {
			loggingArea.append(e1.getMessage() + "\n");
			e1.printStackTrace();
		}


        for (Map.Entry<Integer, ClientRoom> room : clientList.entrySet()){
            try {
                if(room.getValue().clientSocket != null){
                    room.getValue().clientSocket.close();
                }
			} catch (IOException e) {
                loggingArea.append(e.getMessage() + "\n");
				e.printStackTrace();
			}
        }

    }
    

    public static void main(String[] args) {
        new Server();
    }


}
