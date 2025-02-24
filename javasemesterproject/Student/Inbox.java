//package javasemesterproject.Student;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.HeadlessException;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import javasemesterproject.DBConnection;
//import javax.swing.DefaultListModel;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.ListSelectionModel;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//
//public class Inbox extends JFrame implements ActionListener, ListSelectionListener{
//    JList messagesList;
//    DefaultListModel listModel;
//    JLabel title, messageLbl;
//    JPanel mainPanel;
//    JTextArea messageTxt;
//    JButton replyBtn, deleteBtn;
//    JScrollPane scroll1, scroll2;
//    String[][] messagesListData;
//    int currentFromUserID, currentMessageID;
//    String currentFromUserName;
//    public Inbox(){
//        super("Inbox");
//        setLayout(new BorderLayout());
//        
//        title = new JLabel("Inbox");
//        title.setHorizontalAlignment(JLabel.CENTER);
//        title.setFont(new Font(Font.SERIF,Font.BOLD, 23));
//        title.setBackground(Color.LIGHT_GRAY);
//        title.setForeground(Color.BLACK);
//        title.setOpaque(true);
//        add(title, BorderLayout.NORTH);
//        
//        mainPanel = new JPanel();
//        mainPanel.setLayout(null);
//        add(mainPanel, BorderLayout.CENTER);
//        
//        
//        listModel = new DefaultListModel();
//        messagesList = new JList(listModel);
//        messagesList.setFixedCellHeight(40);
//        messagesList.setFixedCellWidth(150);
//        messagesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        messagesList.addListSelectionListener(this);
//        scroll1 = new JScrollPane(messagesList);
//        scroll1.setBounds(50, 50, 500, 220);
//        mainPanel.add(scroll1);
//        this.loadMessages();
//        
//        messageLbl = new JLabel("Message");
//        messageLbl.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 16));
//        messageLbl.setBounds(30, 270, 70, 50);
//        mainPanel.add(messageLbl);
//        
//        messageTxt = new JTextArea();
//        messageTxt.setLineWrap(true);
//        messageTxt.setWrapStyleWord(true);
//        messageTxt.setFont(new Font(Font.SERIF,Font.PLAIN, 18));
//        messageTxt.setEditable(false);
//        scroll2 = new JScrollPane(messageTxt);
//        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//        scroll2.setBounds(30, 310, 550, 210);
//        mainPanel.add(scroll2);
//        
//        replyBtn = new JButton("Reply");
//        replyBtn.addActionListener(this);
//        replyBtn.setEnabled(false);
//        replyBtn.setBounds(455, 530, 80, 30);
//        mainPanel.add(replyBtn);
//        
//        deleteBtn = new JButton("Delete");
//        deleteBtn.addActionListener(this);
//        deleteBtn.setEnabled(false);
//        deleteBtn.setBounds(70, 530, 80, 30);
//        mainPanel.add(deleteBtn);
//
//        setResizable(false);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setSize(600,640);
//        setLocation(385,100);
//        setVisible(true);
//    }
//    private void loadMessages(){
//        try{
//            DBConnection c1 = new DBConnection();
//            String q = "Select M.Message_ID, M.time_Stamp, M.User_ID, M.message From Messages As M"
//                        + " Inner Join MessageUsers As MU ON MU.Message_ID = M.Message_ID"
//                        + " Where MU.User_ID = '"+ StudentLogin.currentStudentID+"'"
//                    + " And M.User_ID <> '"+ StudentLogin.currentStudentID+"'";
//            
//            ResultSet rs = c1.s.executeQuery(q);
//            int rowCount = 0;
//            while(rs.next())
//                rowCount++;
//            messagesListData = new String[rowCount][6];
//            rs.beforeFirst();
//            int i=0;
//            DBConnection c = new DBConnection();
//            while(rs.next()){
//                messagesListData[i][0] = rs.getString("Message_ID");
//                messagesListData[i][1] = rs.getString("time_Stamp");
//                messagesListData[i][2] = rs.getString("User_ID");
//                messagesListData[i][3] = rs.getString("message");
//                String q2 = "Select fname, lname From Student"
//                    + " Where stdID = '"+ messagesListData[i][2] +"'";
//            
//                ResultSet rs2 = c.s.executeQuery(q2);
//                rs2.next();
//                String fromUserName = rs2.getString("fname") + " " + rs2.getString("lname");
//                rs2.beforeFirst();
//                messagesListData[i][4] = fromUserName;
//                i++;
//            }
//            int modelIndex = 0;
//            for(int n = messagesListData.length -1 ; n>=0 ; n--){
//                
//                StringBuilder elementStr = new StringBuilder();
//                elementStr.append("<html><pre><b>");
//                elementStr.append(String.format("%s \t\t\t %s", "From: " + messagesListData[n][4] ,"At:  " + messagesListData[n][1]));
//                elementStr.append("</b></pre></html>");
//                listModel.addElement(elementStr);
//                messagesListData[n][5] = String.valueOf(modelIndex);
//                modelIndex++;
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == replyBtn){
//            new Message(StudentLogin.currentStudentID, currentFromUserName, currentFromUserID);
//        }
//        else if(e.getSource() == deleteBtn){
//            try{
//                DBConnection c1 = new DBConnection();
//
//                String q = "Delete From MessageUsers Where User_ID ='" + StudentLogin.currentStudentID + "' And"
//                        + " Message_ID = '"+ currentMessageID +"'";
//
//                int x = c1.s.executeUpdate(q);
//                if(x == 0){
//                    JOptionPane.showMessageDialog(null, "Got some error");
//                }else{
//                    JOptionPane.showMessageDialog(null, "Message Deleted!");
//                    dispose();
//                    new Inbox();
//                }
//                String q2 = "Select * From MessageUsers Where Message_ID ='" + currentMessageID + "'";
//                ResultSet rs = c1.s.executeQuery(q2);
//                if(rs.next() == false){
//                    String q3 = "Delete From Messages Where Message_ID ='" + currentMessageID + "'";
//                    c1.s.executeUpdate(q3);
//                }
//                    
//        }catch(HeadlessException | SQLException exception){
//            exception.printStackTrace();
//            }
//        }
//    }
//    @Override
//    public void valueChanged(ListSelectionEvent e) {
//        int index = messagesList.getSelectedIndex();
//        for(int i=0; i< messagesListData.length; i++){
//            if(index == Integer.parseInt(messagesListData[i][5])){
//                messageTxt.setText(messagesListData[i][3]);
//                currentFromUserID = Integer.parseInt(messagesListData[i][2]);
//                currentFromUserName = messagesListData[i][4];
//                currentMessageID = Integer.parseInt(messagesListData[i][0]);
//                replyBtn.setEnabled(true);
//                deleteBtn.setEnabled(true);
//            }
//        }
//    }
//    public static void main(String[] args) {
//        new Inbox();
//    }
//}
package javasemesterproject.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.formdev.flatlaf.FlatLightLaf;
import javasemesterproject.DBConnection;

public class Inbox extends JFrame implements ActionListener, ListSelectionListener {

    private JList<String> messagesList;
    private DefaultListModel listModel;
    private JLabel title, messageLbl;
    private JPanel mainPanel;
    private JTextArea messageTxt;
    private JButton replyBtn, deleteBtn;
    private JScrollPane scroll1, scroll2;
    private String[][] messagesListData;
    private int currentFromUserID, currentMessageID;
    private String currentFromUserName;

    public Inbox() {
        super("Inbox");

        // Set FlatLaf look and feel
        FlatLightLaf.setup();

        setLayout(new BorderLayout());

        // Title label
        title = new JLabel("Inbox");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setOpaque(true);
        title.setBackground(new Color(51, 102, 255));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(mainPanel, BorderLayout.CENTER);

        // Messages list
        listModel = new DefaultListModel<>();
        messagesList = new JList<>(listModel);
        messagesList.setFixedCellHeight(40);
        messagesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        messagesList.addListSelectionListener(this);
        scroll1 = new JScrollPane(messagesList);
        scroll1.setBorder(BorderFactory.createTitledBorder("Received Messages"));
        mainPanel.add(scroll1, BorderLayout.NORTH);
        loadMessages();

        // Message label
        messageLbl = new JLabel("Message:");
        messageLbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        mainPanel.add(messageLbl, BorderLayout.CENTER);

        // Message text area
        messageTxt = new JTextArea();
        messageTxt.setLineWrap(true);
        messageTxt.setWrapStyleWord(true);
        messageTxt.setFont(new Font("SansSerif", Font.PLAIN, 16));
        messageTxt.setEditable(false);
        scroll2 = new JScrollPane(messageTxt);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll2.setBorder(BorderFactory.createTitledBorder("Message Details"));
        mainPanel.add(scroll2, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        replyBtn = new JButton("Reply");
        replyBtn.setEnabled(false);
        replyBtn.addActionListener(this);
        buttonsPanel.add(replyBtn);

        deleteBtn = new JButton("Delete");
        deleteBtn.setEnabled(false);
        deleteBtn.addActionListener(this);
        buttonsPanel.add(deleteBtn);

        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Frame settings
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 640);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadMessages() {
        try {
            DBConnection c1 = new DBConnection();
            String q = "Select M.Message_ID, M.time_Stamp, M.User_ID, M.message From Messages As M"
                    + " Inner Join MessageUsers As MU ON MU.Message_ID = M.Message_ID"
                    + " Where MU.User_ID = '" + StudentLogin2.currentStudentID + "'"
                    + " And M.User_ID <> '" + StudentLogin2.currentStudentID + "'";

            ResultSet rs = c1.s.executeQuery(q);
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            messagesListData = new String[rowCount][6];
            rs.beforeFirst();
            int i = 0;
            DBConnection c = new DBConnection();
            while (rs.next()) {
                messagesListData[i][0] = rs.getString("Message_ID");
                messagesListData[i][1] = rs.getString("time_Stamp");
                messagesListData[i][2] = rs.getString("User_ID");
                messagesListData[i][3] = rs.getString("message");
                String q2 = "Select fname, lname From Student"
                        + " Where stdID = '" + messagesListData[i][2] + "'";

                ResultSet rs2 = c.s.executeQuery(q2);
                rs2.next();
                String fromUserName = rs2.getString("fname") + " " + rs2.getString("lname");
                rs2.beforeFirst();
                messagesListData[i][4] = fromUserName;
                i++;
            }
            int modelIndex = 0;
            for (int n = messagesListData.length - 1; n >= 0; n--) {

                StringBuilder elementStr = new StringBuilder();
                elementStr.append("<html><pre><b>");
                elementStr.append(String.format("%s \t\t\t %s", "From: " + messagesListData[n][4], "At:  " + messagesListData[n][1]));
                elementStr.append("</b></pre></html>");
                listModel.addElement(elementStr);
                messagesListData[n][5] = String.valueOf(modelIndex);
                modelIndex++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == replyBtn) {
            new Message(StudentLogin2.currentStudentID, currentFromUserName, currentFromUserID);
        } else if (e.getSource() == deleteBtn) {
            try {
                DBConnection c1 = new DBConnection();

                String q = "DELETE FROM MessageUsers WHERE User_ID = '" + StudentLogin2.currentStudentID + "' AND Message_ID = '" + currentMessageID + "'";
                int x = c1.s.executeUpdate(q);

                if (x == 0) {
                    JOptionPane.showMessageDialog(this, "Error while deleting message.");
                } else {
                    JOptionPane.showMessageDialog(this, "Message Deleted.");
                    dispose();
                    new SentBox();
                }

                String q2 = "SELECT * FROM MessageUsers WHERE Message_ID = '" + currentMessageID + "'";
                ResultSet rs = c1.s.executeQuery(q2);

                if (!rs.next()) {
                    String q3 = "DELETE FROM Messages WHERE Message_ID = '" + currentMessageID + "'";
                    c1.s.executeUpdate(q3);
                }

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int index = messagesList.getSelectedIndex();
        for (String[] messageData : messagesListData) {
            if (index == Integer.parseInt(messageData[5])) {
                messageTxt.setText(messageData[3]);
                currentFromUserID = Integer.parseInt(messageData[2]);
                currentFromUserName = messageData[4];
                currentMessageID = Integer.parseInt(messageData[0]);
                replyBtn.setEnabled(true);
                deleteBtn.setEnabled(true);
            }
        }
    }

    public static void main(String[] args) {
        new Inbox();
    }
}
