import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

import java.io.*;

import java.net.*;



public class ChatClientGUI extends JFrame {



    private JTextArea chatArea;

    private JTextField inputField;

    private PrintWriter output;



    public ChatClientGUI() {

      setTitle("Client Chat");

        setSize(400, 450);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        chatArea = new JTextArea();

        chatArea.setEditable(false);
    
        add(new JScrollPane(chatArea), BorderLayout.CENTER);



        inputField = new JTextField();
    
    add(inputField, BorderLayout.SOUTH);



        inputField.addActionListener(e -> {

          String message = inputField.getText();

          chatArea.append("You: " + message + "\n");

            output.println(message);
    
          inputField.setText("");



            if (message.equalsIgnoreCase("exit")) {

                System.exit(0);

          }
    
    });


    
        setVisible(true);

        connectToServer();
  

  }



          private void connectToServer() {
    
                          try {
  
                      Socket socket = new Socket("localhost", 8080);

                      chatArea.append("Connected to Server!\n");


    
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

              output = new PrintWriter(socket.getOutputStream(), true);



              String fromServer;
  
            while ((fromServer = input.readLine()) != null) {

                chatArea.append("Server: " + fromServer + "\n");

            }

      
              } catch (Exception e) {

                  chatArea.append("Error: " + e + "\n");

        }
  
  }


              public static void main(String[] args) {
  
              new ChatClientGUI();
  
      }

}