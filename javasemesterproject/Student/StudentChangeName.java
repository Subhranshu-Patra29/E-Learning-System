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
//public class StudentChangeName extends JFrame implements ActionListener{
//    JLabel newFNamelbl;
//    JLabel newLNamelbl;
//    JTextField newFName;
//    JTextField newLName;
//    JButton updateNameButton;
//    public StudentChangeName(){
//            newFNamelbl = new JLabel("First Name");
//            newFNamelbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
//            newFNamelbl.setBounds(10, 10, 150, 30);
//            add(newFNamelbl);
//            
//
//            newFName = new JTextField();
//            newFName.setFont(new Font("Tahoma", Font.PLAIN, 22));
//            newFName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//            newFName.setBounds(150, 10, 150, 40);
//            add(newFName);
//            newFName.setColumns(20);
//            
//            newLNamelbl = new JLabel("Last Name");
//            newLNamelbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
//            newLNamelbl.setBounds(10, 80, 150, 30);
//            add(newLNamelbl);
//            
//
//            newLName = new JTextField();
//            newLName.setFont(new Font("Tahoma", Font.PLAIN, 22));
//            newLName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//            newLName.setBounds(150, 80, 150, 40);
//            add(newLName);
//            newLName.setColumns(20);
//            
//            updateNameButton = new JButton("Update");
//            updateNameButton.setBounds(80, 140, 150, 30);
//            updateNameButton.addActionListener((ActionListener) this);
//            add(updateNameButton);
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
//        if(ae.getSource() == updateNameButton){
//            String FirstName = newFName.getText();
//            String LastName = newLName.getText();
//            try{
//                DBConnection c1 = new DBConnection();
//
//                String q = "update Student SET fname = '"+ FirstName +"', lname = '"+ LastName +"'"
//                        + "Where stdID ='" + StudentLogin.currentStudentID + "'";
//
//                int x = c1.s.executeUpdate(q);
//                if(x == 0){
//                    JOptionPane.showMessageDialog(null, "Got some error");
//                }else{
//                    JOptionPane.showMessageDialog(null, "Your Name Updated Successfully");
//                    setVisible(false);
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//                }
//        }
//    }
//    public static void main(String[] args) {
//        new StudentChangeName();
//    }
//}

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javasemesterproject.DBConnection;

public class StudentChangeName extends JFrame implements ActionListener {
    JLabel headingLabel, iconLabel, firstNameLabel, lastNameLabel;
    JTextField firstNameField, lastNameField;
    JButton updateNameButton;

    public StudentChangeName() {
        // Set up frame
        super("Change Name");
        setLayout(null);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Heading Label
        headingLabel = new JLabel("Change Name", JLabel.CENTER);
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        headingLabel.setBounds(-10, 20, 400, 40);
        headingLabel.setForeground(new Color(50, 50, 150));
        add(headingLabel);

        // Icon (Image)
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/rename.png"));
        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        iconLabel = new JLabel(new ImageIcon(img));
        iconLabel.setBounds(160, 80, 80, 80);
        add(iconLabel);

        // First Name Label
        firstNameLabel = new JLabel("      First Name");
        firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        firstNameLabel.setBounds(50, 180, 120, 30);
        add(firstNameLabel);

        // First Name Field
        firstNameField = new JTextField();
        firstNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        firstNameField.setBounds(180, 180, 150, 30);
        firstNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(firstNameField);

        // Last Name Label
        lastNameLabel = new JLabel("      Last Name");
        lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lastNameLabel.setBounds(50, 230, 120, 30);
        add(lastNameLabel);

        // Last Name Field
        lastNameField = new JTextField();
        lastNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lastNameField.setBounds(180, 230, 150, 30);
        lastNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(lastNameField);

        // Update Button with FlatLaf styling
        updateNameButton = new JButton("Update");
        updateNameButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        updateNameButton.setBounds(130, 300, 140, 40);
        updateNameButton.putClientProperty("JButton.buttonType", "roundRect"); // FlatLaf button styling
        updateNameButton.setBackground(new Color(80, 89, 201));
        updateNameButton.setForeground(Color.WHITE);
        updateNameButton.addActionListener(this);
        add(updateNameButton);

        // Finalize frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateNameButton) {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();

            if (firstName.isEmpty() || lastName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                DBConnection dbConnection = new DBConnection();
                String query = "UPDATE Student SET fname = '" + firstName + "', lname = '" + lastName + "' WHERE stdID = '" + StudentLogin2.currentStudentID + "'";
                int rowsUpdated = dbConnection.s.executeUpdate(query);

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Name updated successfully!");
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Error updating name. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error. Please check the connection.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new StudentChangeName();
    }
}