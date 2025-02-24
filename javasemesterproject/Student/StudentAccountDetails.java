package javasemesterproject.Student;

//import java.awt.Font;
//import java.awt.HeadlessException;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import javasemesterproject.DBConnection;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//
//public class StudentAccountDetails extends JFrame implements ActionListener{
//    JPanel contentPane;
//    JTextField firstname, lastname, email, username, passwordField, gender;
//    JButton OkButton;
//    public StudentAccountDetails(){
//        super("Student Details");
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setSize(1014,550);
//        setLocation(230,110);
//
//        setResizable(false);
//        contentPane = new JPanel();
//        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//        setContentPane(contentPane);
//        contentPane.setLayout(null);
//        
//        JLabel lblTeacherDetails = new JLabel("Student Details");
//        lblTeacherDetails.setFont(new Font("Times New Roman", Font.PLAIN, 42));
//        lblTeacherDetails.setBounds(362, 52, 325, 50);
//        contentPane.add(lblTeacherDetails);
//
//        JLabel lblName = new JLabel("First name");
//        lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        lblName.setBounds(58, 152, 99, 43);
//        contentPane.add(lblName);
//
//        JLabel lblNewLabel = new JLabel("Last name");
//        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        lblNewLabel.setBounds(58, 243, 110, 29);
//        contentPane.add(lblNewLabel);
//
//        JLabel lblEmailAddress = new JLabel("Email\r\n address");
//        lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        lblEmailAddress.setBounds(58, 324, 124, 36);
//        contentPane.add(lblEmailAddress);
//        
//        String firstName = null,lastName = null, 
//                emailId = null, userName = null, 
//                password = null, userGender = null;
//        try{
//            DBConnection c1 = new DBConnection();
//            String q = "select * from Student where stdID = '"+ StudentLogin.currentStudentID +"'";
//
//            ResultSet rs = c1.s.executeQuery(q);
//            if(rs.next()){
//                firstName = rs.getString("fname");
//                lastName = rs.getString("lname");
//                emailId = rs.getString("Email_ID");
//                userName = rs.getString("username");
//                password = rs.getString("password");
//                userGender = rs.getString("Gender");
//            }else{
//                JOptionPane.showMessageDialog(null, "Not Found");
//            }
//        }catch(HeadlessException | NumberFormatException | SQLException e){
//            e.printStackTrace();
//        }
//        
// 
//        firstname = new JTextField();
//        firstname.setFont(new Font("Tahoma", Font.PLAIN, 32));
//        firstname.setBounds(214, 151, 228, 50);
//        firstname.setEditable(false);
//        firstname.setText(firstName);
//        contentPane.add(firstname);
//
//        lastname = new JTextField();
//        lastname.setFont(new Font("Tahoma", Font.PLAIN, 32));
//        lastname.setBounds(214, 235, 228, 50);
//        lastname.setEditable(false);
//        lastname.setText(lastName);
//        contentPane.add(lastname);
//
//        email = new JTextField();
//        email.setFont(new Font("Tahoma", Font.PLAIN, 32));
//        email.setBounds(214, 320, 320, 50);
//        email.setEditable(false);
//        email.setText(emailId);
//        contentPane.add(email);
//
//        username = new JTextField();
//        username.setFont(new Font("Tahoma", Font.PLAIN, 32));
//        username.setBounds(707, 151, 228, 50);
//        username.setEditable(false);
//        username.setText(userName);
//        contentPane.add(username);
//
//        JLabel lblUsername = new JLabel("Username");
//        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        lblUsername.setBounds(542, 159, 99, 29);
//        contentPane.add(lblUsername);
//
//        JLabel lblPassword = new JLabel("Password");
//        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        lblPassword.setBounds(542, 245, 99, 24);
//        contentPane.add(lblPassword);
//
//        passwordField = new JTextField();
//        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
//        passwordField.setBounds(707, 235, 228, 50);
//        passwordField.setEditable(false);
//        passwordField.setText(password);
//        contentPane.add(passwordField);
//        
//        JLabel lblGender = new JLabel("Gender");
//        lblGender.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        lblGender.setBounds(542, 321, 99, 24);
//        contentPane.add(lblGender);
//
//        gender = new JTextField();
//        gender.setFont(new Font("Tahoma", Font.PLAIN, 32));
//        gender.setBounds(707, 311, 228, 50);
//        gender.setEditable(false);
//        gender.setText(userGender);
//        contentPane.add(gender);
//
//        OkButton = new JButton("Ok");
//        OkButton.setBounds(410, 440, 228, 60);
//        OkButton.addActionListener((ActionListener) this);
//        contentPane.add(OkButton);
//        
//        setVisible(true);
//    }
//    @Override
//    public void actionPerformed(ActionEvent ae){
//        if(ae.getSource() == OkButton){
//            dispose();
//        }
//    }
//    
//    public static void main(String[] args) {
//        new StudentAccountDetails();
//    }
//}


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javasemesterproject.DBConnection;

public class StudentAccountDetails extends JFrame implements ActionListener {
    JPanel contentPane;
    JTextField firstname, lastname, email, username, passwordField, gender;
    JButton okButton;

    public StudentAccountDetails() {
        super("Student Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Set modern theme (FlatLaf)
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        JLabel titleLabel = new JLabel("Student Details");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(new Color(50, 50, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(titleLabel, gbc);

        String firstName = null, lastName = null, emailId = null, userName = null, password = null, userGender = null;
        try {
            DBConnection c1 = new DBConnection();
            String q = "SELECT * FROM Student WHERE stdID = '" + StudentLogin2.currentStudentID + "'";
            ResultSet rs = c1.s.executeQuery(q);
            if (rs.next()) {
                firstName = rs.getString("fname");
                lastName = rs.getString("lname");
                emailId = rs.getString("Email_ID");
                userName = rs.getString("username");
                password = rs.getString("password");
                userGender = rs.getString("Gender");
            } else {
                JOptionPane.showMessageDialog(null, "Not Found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Labels and fields
        createLabelAndField("First Name", firstName, 1, gbc);
        createLabelAndField("Last Name", lastName, 2, gbc);
        createLabelAndField("Email", emailId, 3, gbc);
        createLabelAndField("Username", userName, 4, gbc);
        createLabelAndField("Password", password, 5, gbc);
        createLabelAndField("Gender", userGender, 6, gbc);

        // OK Button
        okButton = new JButton("OK");
        okButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        okButton.setBackground(new Color(50, 150, 250));
        okButton.setForeground(Color.WHITE);
        okButton.setFocusPainted(false);
        okButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        okButton.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(okButton, gbc);

        setVisible(true);
    }

    private void createLabelAndField(String labelText, String fieldText, int yPos, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText + ":");
        label.setFont(new Font("SansSerif", Font.PLAIN, 18));
        label.setForeground(new Color(80, 80, 80));

        JTextField field = new JTextField();
        field.setFont(new Font("SansSerif", Font.PLAIN, 16));
        field.setText(fieldText);
        field.setEditable(false);
        field.setBackground(new Color(245, 245, 245));
        field.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        gbc.gridx = 0;
        gbc.gridy = yPos;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = yPos;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(field, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == okButton) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new StudentAccountDetails();
    }
}
