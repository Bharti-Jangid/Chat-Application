import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatServerGUI extends JFrame{

    private JTextArea chatArea;
    private JTextField inputField;
    private PrintWriter output;

    public ChatServerGUI() {
        setTitle("Server Chat");
        setSize(400,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatArea= new JTextArea(); 
        chatArea.setEditable(false);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        inputField = new JTextField();
        add(inputField, BorderLayout.SOUTH);

        inputField.addActionListener(e -> {
            String message = inputField.getText();
            chatArea.append("Server:" + message + "\n");
            output.println(message);
            inputField.setText("");

                if(message.equalsIgnoreCase("exit")){
                System.exit(0);
                }
        });

            setVisible(true);
            startServer();
        }

        private void startServer(){
                            try{
                                ServerSocket server = new ServerSocket(8080);
                                chatArea.append("Server Started. Waiting for Client...\n");

                                Socket socket = server.accept();
                                chatArea.append("Client Connected!\n");

                                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                output = new PrintWriter(socket.getOutputStream(), true);

                                String fromClient;
                            
                                while((fromClient= input.readLine())  !=null) {
                                        chatArea.append("Client:" + fromClient + "\n");
                                } 
                                
                                } catch(Exception e) {
                                chatArea.append("Error:" + e + "\n");
                                }
                        }

                    public static void main(String[] args){
                        new ChatServerGUI();
                        }
                    }