package javasemesterproject.Student;

//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javasemesterproject.DBConnection;
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JTextField;
//
//public class StudentChangeEmail extends JFrame implements ActionListener{
//    JLabel newEmaillbl;
//    JTextField newEmail;
//    JButton updateEmailbtn;
//    public StudentChangeEmail(){
//            newEmaillbl = new JLabel("New Email");
//            newEmaillbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
//            newEmaillbl.setBounds(10, 10, 150, 30);
//            add(newEmaillbl);
//            
//
//            newEmail = new JTextField();
//            newEmail.setFont(new Font("Tahoma", Font.PLAIN, 22));
//            newEmail.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//            newEmail.setBounds(150, 10, 150, 40);
//            add(newEmail);
//            newEmail.setColumns(20);
//            
//            updateEmailbtn = new JButton("Update");
//            updateEmailbtn.setBounds(80, 90, 150, 30);
//            updateEmailbtn.addActionListener((ActionListener) this);
//            add(updateEmailbtn);
//            
////            ChangePassFrame.setResizable(false);
//            setLayout(null);
//            setSize(320,220);
//            setLocation(500,320);
//            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//            setVisible(true);
//    }
//    @Override
//    public void actionPerformed(ActionEvent ae){
//        if(ae.getSource() == updateEmailbtn){
//            String EmailID = newEmail.getText();
//            try{
//                DBConnection c1 = new DBConnection();
//
//                String q = "update Student SET Email_ID = '"+ EmailID +"'"
//                        + "Where stdID ='" + StudentLogin.currentStudentID + "'";
//
//                int x = c1.s.executeUpdate(q);
//                if(x == 0){
//                    JOptionPane.showMessageDialog(null, "Got some error");
//                }else{
//                    JOptionPane.showMessageDialog(null, "Your Email Updated Successfully");
//                    setVisible(false);
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//                }
//        }
//    }
//    public static void main(String[] args) {
//        new StudentChangeEmail();
//    }
//}


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javasemesterproject.DBConnection;

public class StudentChangeEmail extends JFrame implements ActionListener {
    JLabel headingLabel, newEmailLabel, iconLabel;
    JTextField newEmailField;
    JButton updateEmailButton;

    public StudentChangeEmail() {
        // Set up frame
        super("Change Email");
        setLayout(null);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Heading Label
        headingLabel = new JLabel("Change Email", JLabel.CENTER);
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        headingLabel.setBounds(-10, 20, 400, 40);
        headingLabel.setForeground(new Color(50, 50, 150));
        add(headingLabel);

        // Icon (Image)
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/email.png"));
        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        iconLabel = new JLabel(new ImageIcon(img));
        iconLabel.setBounds(160, 80, 80, 80);
        add(iconLabel);

        // New Email Label
        newEmailLabel = new JLabel("      New Email");
        newEmailLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        newEmailLabel.setBounds(50, 180, 120, 30);
        add(newEmailLabel);

        // New Email Field
        newEmailField = new JTextField();
        newEmailField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        newEmailField.setBounds(180, 180, 150, 30);
        newEmailField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(newEmailField);

        // Update Button with FlatLaf styling
        updateEmailButton = new JButton("Update");
        updateEmailButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        updateEmailButton.setBounds(130, 260, 140, 40);
        updateEmailButton.putClientProperty("JButton.buttonType", "roundRect"); // FlatLaf button styling
        updateEmailButton.setBackground(new Color(80, 89, 201));
        updateEmailButton.setForeground(Color.WHITE);
        updateEmailButton.addActionListener(this);
        add(updateEmailButton);

        // Finalize frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateEmailButton) {
            String newEmail = newEmailField.getText().trim();
            if (newEmail.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                DBConnection dbConnection = new DBConnection();
                String query = "UPDATE Student SET Email_ID = '" + newEmail + "' WHERE stdID = '" + StudentLogin2.currentStudentID + "'";
                int rowsUpdated = dbConnection.s.executeUpdate(query);

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Email updated successfully!");
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Error updating email. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error. Please check the connection.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new StudentChangeEmail();
    }
}