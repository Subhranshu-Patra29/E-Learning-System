package javasemesterproject.Student;

//
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.sql.PreparedStatement;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javasemesterproject.DBConnection;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFileChooser;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//
//public class StudentManageAccount extends JFrame implements ActionListener{
//    JPanel panel;
//    JLabel title;
//    JButton b1, b2, b3, b4, b5;
//    FileInputStream fis = null;
//    File f = null;
//    public StudentManageAccount(){
//        super("Manage Student Account");
//        
//        panel = new JPanel();
//        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
//        panel.setLayout(new GridBagLayout());
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridwidth = GridBagConstraints.REMAINDER;
//        gbc.anchor = GridBagConstraints.NORTH;
//
//        title = new JLabel("<html><h1><strong>Manage Account</strong></h1></html>");
//        panel.add(title, gbc);
//
//        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//
//        JPanel buttons = new JPanel(new GridBagLayout());
//        b1 = new JButton("Change Password");
//        b1.addActionListener((ActionListener) this);
//        b2 = new JButton("Change Name");
//        b2.addActionListener((ActionListener) this);
//        b3 = new JButton("Change Email");
//        b3.addActionListener((ActionListener) this);
//        b4 = new JButton("Change Profile");
//        b4.addActionListener((ActionListener) this);
//        b5 = new JButton("Exit"); 
//        b5.addActionListener((ActionListener) this);
//        buttons.add(b1, gbc);
//        buttons.add(b2, gbc);
//        buttons.add(b3, gbc);
//        buttons.add(b4, gbc);
//        buttons.add(b5, gbc);
//
//        gbc.weighty = 1;
//        panel.add(buttons, gbc);
//        
//        add(panel);
//        
//        setSize(420,320);
//        setLocation(460,280);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setResizable(false);
//        setVisible(true);
//    }
//    @Override
//    public void actionPerformed(ActionEvent ae){
//        if(ae.getSource() == b1){
//            new StudentChangePassword();
//        }
//        else if(ae.getSource() == b2){
//            new StudentChangeName();
//        }
//        else if(ae.getSource() == b3){
//            new StudentChangeEmail();
//        }
//        else if(ae.getSource() == b4){
//            String fname = null;
//            JFileChooser fchoser=new JFileChooser();
//            fchoser.showOpenDialog(null);
//            f = fchoser.getSelectedFile();
//            fname = f.getAbsolutePath();
//            ImageIcon micon=new ImageIcon(fname);
//            File image=new File(fname);
//            try {
//                fis = new FileInputStream(image);
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(StudentManageAccount.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            try {
//                DBConnection c1 = new DBConnection();
//
//                PreparedStatement ps = c1.c.prepareStatement("update Student SET picture =? Where stdID =?");
//                ps.setBinaryStream(1,(InputStream)fis,(int)f.length());
//                ps.setInt(2, StudentLogin.currentStudentID);
//                int x =  ps.executeUpdate();
//                if(x == 0){
//                   JOptionPane.showMessageDialog(null, "Got Some Error");
//               }else{
//                   JOptionPane.showMessageDialog(null, "Profile updated Successfully!");
//                    dispose();
//               }
//                ps.close();
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        }
//        else if(ae.getSource() == b5){
//            dispose();
//        }
//    }
//    public static void main(String[] args) {
//        new StudentManageAccount();
//    }
//}


import com.formdev.flatlaf.FlatLightLaf;
import javasemesterproject.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EmptyBorder;

public class StudentManageAccount extends JFrame implements ActionListener {
    private JPanel panel;
    private JButton b1, b2, b3, b4, b5;
    private FileInputStream fis = null;
    private File file = null;

    public StudentManageAccount() {
        super("Manage Student Account");
        setupFlatLaf();
        initializeComponents();
    }

    /**
     * Sets up the FlatLaf look and feel.
     */
    private void setupFlatLaf() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
    }

    /**
     * Initializes UI components and layout.
     */
    private void initializeComponents() {
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        JLabel title = new JLabel("<html><h1>Manage Account</h1></html>");
        panel.add(title, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());
        b1 = createButton("Change Password", buttons, gbc);
        b2 = createButton("Change Name", buttons, gbc);
        b3 = createButton("Change Email", buttons, gbc);
        b4 = createButton("Change Profile", buttons, gbc);
        b5 = createButton("Exit", buttons, gbc);

        gbc.weighty = 1;
        panel.add(buttons, gbc);

        add(panel);

        setSize(420, 320);
        setLocationRelativeTo(null); // Centers the frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Creates a button, adds it to the panel, and assigns an ActionListener.
     */
    private JButton createButton(String text, JPanel panel, GridBagConstraints gbc) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        panel.add(button, gbc);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            new StudentChangePassword();
        } else if (ae.getSource() == b2) {
            new StudentChangeName();
        } else if (ae.getSource() == b3) {
            new StudentChangeEmail();
        } else if (ae.getSource() == b4) {
            updateProfilePicture();
        } else if (ae.getSource() == b5) {
            dispose();
        }
    }

    /**
     * Handles updating the admin profile picture.
     */
    private void updateProfilePicture() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            try {
                fis = new FileInputStream(file);

                DBConnection dbConnection = new DBConnection();
                try {
                    String query = "UPDATE Student SET picture = ? WHERE stdID = ?";
                    PreparedStatement ps = dbConnection.c.prepareStatement(query);
                    ps.setBinaryStream(1, fis, (int) file.length());
                    ps.setInt(2, StudentLogin2.currentStudentID);

                    int rowsUpdated = ps.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(this, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update profile. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    ps.close();
                } finally {
                    dbConnection.Close();
                }
            } catch (Exception e) {
                Logger.getLogger(StudentManageAccount.class.getName()).log(Level.SEVERE, null, e);
                JOptionPane.showMessageDialog(this, "An error occurred while updating the profile picture.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManageAccount::new);
    }
}
