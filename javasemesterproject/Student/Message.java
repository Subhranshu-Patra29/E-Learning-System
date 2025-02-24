//package javasemesterproject.Student;
//
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.ResultSet;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import javasemesterproject.DBConnection;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.ScrollPaneConstants;
//
//public class Message extends JFrame implements ActionListener{
//    JTextArea messageTextArea;
//    JButton sendBtn;
//    JLabel receiverNameLabel, writeMsgLbl;
//    JScrollPane scroll;
//    JPanel mainPanel;
//    int to_ID;
//    public Message(int from_ID, String toName, int to_ID){
//        super("Message");
//        setLayout(new BorderLayout());
//        this.to_ID = to_ID;
//        receiverNameLabel = new JLabel(toName);
//        receiverNameLabel.setHorizontalAlignment(JLabel.CENTER);
//        receiverNameLabel.setFont(new Font(Font.SERIF,Font.BOLD, 23));
//        add(receiverNameLabel, BorderLayout.NORTH);
//        
//        mainPanel = new JPanel();
//        mainPanel.setLayout(new BorderLayout());
//        add(mainPanel, BorderLayout.CENTER);
//        
//        writeMsgLbl = new JLabel("Write Message");
//        writeMsgLbl.setHorizontalAlignment(JLabel.LEFT);
//        writeMsgLbl.setFont(new Font(Font.DIALOG,Font.PLAIN, 12));
//        mainPanel.add(writeMsgLbl, BorderLayout.NORTH);
//        
//        messageTextArea = new JTextArea();
//        messageTextArea.setLineWrap(true);
//        messageTextArea.setWrapStyleWord(true);
//        messageTextArea.setFont(new Font(Font.SERIF,Font.PLAIN, 18));
//        messageTextArea.setToolTipText( "Write Message Here." );
//        scroll = new JScrollPane(messageTextArea);
//        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
//        mainPanel.add(scroll, BorderLayout.CENTER);
//        
//        sendBtn = new JButton("Send");
//        sendBtn.setPreferredSize(new Dimension(0, 50));
//        sendBtn.addActionListener(this);
//        add(sendBtn, BorderLayout.SOUTH);
//        
//        setResizable(false);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setSize(400,320);
//        setLocation(515,300);
//        setVisible(true);
//    }
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == sendBtn){
//            String messageStr = messageTextArea.getText();
//            java.util.Date dt = new java.util.Date();
//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            String time_Stamp = dateFormat.format(dt);
//            try{
//                DBConnection c1 = new DBConnection();
//
//                String q = "INSERT INTO Messages (message, time_Stamp, User_ID, toUser_ID) "
//                        + "Values ('" + messageStr +"', '" + time_Stamp +"', '" + StudentLogin.currentStudentID +"',"
//                        + " '"+ to_ID +"')";
//                int x = c1.s.executeUpdate(q);
//               String q1 = "select Max(Message_ID) As Message_ID From Messages";          
//                ResultSet rs1 = c1.s.executeQuery(q1);
//                rs1.next();
//                int Message_ID = rs1.getInt("Message_ID");
//                String q2 = "INSERT INTO MessageUsers (User_ID, Message_ID) "
//                        + "Values ('" + StudentLogin.currentStudentID +"', '"+ Message_ID +"' )"; 
//                int x2 = c1.s.executeUpdate(q2);
//                String q3 = "INSERT INTO MessageUsers (User_ID, Message_ID) "
//                        + "Values ('" + to_ID +"', '"+ Message_ID +"' )"; 
//                int x3 = c1.s.executeUpdate(q3);
//                
//                if(x == 0 || x2 == 0 || x3 == 0){
//                    JOptionPane.showMessageDialog(null, "Some Error Occured!");
//                }else{
//                    JOptionPane.showMessageDialog(null, "Message Sent");
//                    dispose();
//                }
//        }
//            catch(Exception exception){
//                exception.printStackTrace();
//            }
//        }
//    }
//}

package javasemesterproject.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf;
import javasemesterproject.DBConnection;

public class Message extends JFrame implements ActionListener {
    private JTextArea messageTextArea;
    private JButton sendBtn;
    private JLabel receiverNameLabel, writeMsgLbl;
    private JScrollPane scroll;
    private int to_ID;

    public Message(int from_ID, String toName, int to_ID) {
        // Set FlatLaf look and feel
        FlatLightLaf.setup();

        setTitle("Message");
        setLayout(new BorderLayout());
        this.to_ID = to_ID;

        // Receiver Name Label
        receiverNameLabel = new JLabel(toName);
        receiverNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        receiverNameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        receiverNameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(receiverNameLabel, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Write Message Label
        writeMsgLbl = new JLabel("Write Message:");
        writeMsgLbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
        writeMsgLbl.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        mainPanel.add(writeMsgLbl, BorderLayout.NORTH);

        // Message Text Area
        messageTextArea = new JTextArea();
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        messageTextArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        messageTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        scroll = new JScrollPane(messageTextArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        mainPanel.add(scroll, BorderLayout.CENTER);

        // Send Button
        sendBtn = new JButton("Send");
        sendBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        sendBtn.setBackground(new Color(0, 122, 255));
        sendBtn.setForeground(Color.WHITE);
        sendBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        sendBtn.setFocusPainted(false);
        sendBtn.addActionListener(this);
        sendBtn.setPreferredSize(new Dimension(0, 30));
        add(sendBtn, BorderLayout.SOUTH);

        // Frame Settings
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 320);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendBtn) {
            String messageStr = messageTextArea.getText();
            java.util.Date dt = new java.util.Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String time_Stamp = dateFormat.format(dt);
            try {
                DBConnection c1 = new DBConnection();

                String q = "INSERT INTO Messages (message, time_Stamp, User_ID, toUser_ID) "
                        + "Values ('" + messageStr + "', '" + time_Stamp + "', '" + StudentLogin2.currentStudentID + "',"
                        + " '" + to_ID + "')";
                int x = c1.s.executeUpdate(q);

                String q1 = "SELECT MAX(Message_ID) AS Message_ID FROM Messages";
                ResultSet rs1 = c1.s.executeQuery(q1);
                rs1.next();
                int Message_ID = rs1.getInt("Message_ID");

                String q2 = "INSERT INTO MessageUsers (User_ID, Message_ID) "
                        + "VALUES ('" + StudentLogin2.currentStudentID + "', '" + Message_ID + "')";
                int x2 = c1.s.executeUpdate(q2);

                String q3 = "INSERT INTO MessageUsers (User_ID, Message_ID) "
                        + "VALUES ('" + to_ID + "', '" + Message_ID + "')";
                int x3 = c1.s.executeUpdate(q3);

                if (x == 0 || x2 == 0 || x3 == 0) {
                    JOptionPane.showMessageDialog(this, "Some Error Occurred!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Message Sent", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred while sending the message.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        // Test the redesigned UI
        new Message(1, "John Doe", 2);
    }
}
