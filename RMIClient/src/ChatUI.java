import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ChatUI extends JFrame {
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    Remote chat;
    String pseudo;
    public ChatUI(ChatRemote chat) {
        setTitle("Chat App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.chat = chat;
        // Create components
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        pseudo = JOptionPane.showInputDialog(null, "Donner votre pseudo:");
        messageField = new JTextField(30);
        sendButton = new JButton("Send");
        new Thread(() -> {
            while (true) {
                try {
                    ArrayList<Message> messages = chat.getAllMsgs();
                    chatArea.setText("");
                    for (Message message : messages) {
                        chatArea.append(message.toString());
                    }
                    Thread.sleep(1000);
                } catch (RemoteException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);


        sendButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    String psd = pseudo;
                    chat.addMessage(new Message(psd, messageField.getText()));
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


    }
}
