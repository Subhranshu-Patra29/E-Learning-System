package javasemesterproject.Teacher;

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
//import javax.swing.JPasswordField;
//
//public class TeacherChangePassword extends JFrame implements ActionListener{
//    JLabel newPasswordlbl;
//    JPasswordField newPassword;
//    JButton updatePassbtn;
//    public TeacherChangePassword(){
//            newPasswordlbl = new JLabel("New Password");
//            newPasswordlbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
//            newPasswordlbl.setBounds(10, 10, 150, 30);
//            add(newPasswordlbl);
//            
//
//            newPassword = new JPasswordField();
//            newPassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
//            newPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//            newPassword.setBounds(150, 10, 150, 40);
//            add(newPassword);
//            newPassword.setColumns(20);
//            
//            updatePassbtn = new JButton("Update");
//            updatePassbtn.setBounds(80, 90, 150, 30);
//            updatePassbtn.addActionListener((ActionListener) this);
//            add(updatePassbtn);
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
//        if(ae.getSource() == updatePassbtn){
//            String password = String.valueOf(newPassword.getPassword());
//            try{
//                DBConnection c1 = new DBConnection();
//
//                String q = "update Teacher SET password = '"+ password +"'"
//                        + "Where teacherID ='" + TeacherLogin.currentTeacherID + "'";
//
//                int x = c1.s.executeUpdate(q);
//                if(x == 0){
//                    JOptionPane.showMessageDialog(null, "Got some error");
//                }else{
//                    JOptionPane.showMessageDialog(null, "Your Password Updated Successfully");
//                    setVisible(false);
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//                }
//        }
//    }
//    public static void main(String[] args) {
//        new TeacherChangePassword();
//    }
//}


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javasemesterproject.DBConnection;

public class TeacherChangePassword extends JFrame implements ActionListener {
    JLabel headingLabel, newPasswordLabel, iconLabel;
    JPasswordField newPasswordField;
    JButton updatePasswordButton;

    public TeacherChangePassword() {
        // Set up frame
        super("Change Password");
        setLayout(null);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Heading Label
        headingLabel = new JLabel("Change Password", JLabel.CENTER);
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        headingLabel.setBounds(0, 20, 400, 40);
        headingLabel.setForeground(new Color(50, 50, 150));
        add(headingLabel);

        // Icon (Image)
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/change_password.png"));
        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        iconLabel = new JLabel(new ImageIcon(img));
        iconLabel.setBounds(160, 80, 80, 80);
        add(iconLabel);

        // New Password Label
        newPasswordLabel = new JLabel("New Password");
        newPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        newPasswordLabel.setBounds(50, 180, 120, 30);
        add(newPasswordLabel);

        // New Password Field
        newPasswordField = new JPasswordField();
        newPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        newPasswordField.setBounds(180, 180, 150, 30);
        newPasswordField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(newPasswordField);

        // Update Button with FlatLaf styling
        updatePasswordButton = new JButton("Update");
        updatePasswordButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        updatePasswordButton.setBounds(130, 260, 140, 40);
        updatePasswordButton.putClientProperty("JButton.buttonType", "roundRect"); // FlatLaf button styling
        updatePasswordButton.setBackground(new Color(80, 89, 201));
        updatePasswordButton.setForeground(Color.WHITE);
        updatePasswordButton.addActionListener(this);
        add(updatePasswordButton);

        // Finalize frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updatePasswordButton) {
            String newPassword = String.valueOf(newPasswordField.getPassword());
            if (newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                DBConnection dbConnection = new DBConnection();
                String query = "UPDATE Teacher SET password = '" + newPassword + "' WHERE teacherID = '" + TeacherLogin2.currentTeacherID + "'";
                int rowsUpdated = dbConnection.s.executeUpdate(query);

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Password updated successfully!");
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Error updating password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error. Please check the connection.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new TeacherChangePassword();
    }
}

