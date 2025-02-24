package javasemesterproject.Student;

import java.sql.*;
import javasemesterproject.DBConnection;
import javasemesterproject.Main;
import javax.swing.*;

/**
 *
 * @author subha
 */
public class StudentLogin2 extends javax.swing.JFrame {

    public static int currentStudentID;
    
    public StudentLogin2() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Right = new javax.swing.JPanel();
        StudentImage = new javax.swing.JLabel();
        StudentLoginText = new javax.swing.JLabel();
        Left = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        usernameInput = new javax.swing.JTextField();
        password = new javax.swing.JLabel();
        passwordInput = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        Right.setBackground(new java.awt.Color(5, 5, 51));
        Right.setPreferredSize(new java.awt.Dimension(400, 500));

        StudentImage.setBackground(new java.awt.Color(0, 0, 51));
        StudentImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javasemesterproject/icons/student.png"))); // NOI18N
        StudentImage.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 51), new java.awt.Color(0, 0, 102), new java.awt.Color(51, 51, 255), new java.awt.Color(51, 51, 255)));

        StudentLoginText.setFont(new java.awt.Font("Segoe UI", 2, 25)); // NOI18N
        StudentLoginText.setForeground(new java.awt.Color(20, 227, 61));
        StudentLoginText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StudentLoginText.setText("Student Login");

        javax.swing.GroupLayout RightLayout = new javax.swing.GroupLayout(Right);
        Right.setLayout(RightLayout);
        RightLayout.setHorizontalGroup(
            RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightLayout.createSequentialGroup()
                .addGroup(RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(StudentImage))
                    .addGroup(RightLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(StudentLoginText, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        RightLayout.setVerticalGroup(
            RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(StudentImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StudentLoginText, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        jPanel1.add(Right);
        Right.setBounds(0, 0, 270, 370);

        Left.setBackground(new java.awt.Color(153, 153, 255));
        Left.setMinimumSize(new java.awt.Dimension(400, 500));

        username.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        username.setForeground(new java.awt.Color(5, 5, 51));
        username.setText("Username");

        usernameInput.setBackground(new java.awt.Color(204, 204, 204));
        usernameInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N


        password.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        password.setForeground(new java.awt.Color(5, 5, 51));
        password.setText("Password");

        passwordInput.setBackground(new java.awt.Color(204, 204, 204));
        passwordInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N


        loginBtn.setBackground(new java.awt.Color(5, 5, 51));
        loginBtn.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        loginBtn.setForeground(new java.awt.Color(204, 204, 204));
        loginBtn.setText("Login");
        loginBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        cancelBtn.setBackground(new java.awt.Color(5, 5, 51));
        cancelBtn.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        cancelBtn.setForeground(new java.awt.Color(204, 204, 204));
        cancelBtn.setText("Cancel");
        cancelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LeftLayout = new javax.swing.GroupLayout(Left);
        Left.setLayout(LeftLayout);
        LeftLayout.setHorizontalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(LeftLayout.createSequentialGroup()
                            .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(passwordInput, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                        .addComponent(usernameInput, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(100, 100, 100))
        );
        LeftLayout.setVerticalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(208, 208, 208))
        );

        jPanel1.add(Left);
        Left.setBounds(263, 0, 360, 370);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        System.out.println("Login Button Clicked!");
        String name = "", psswrd = "", psswrdEntered = "";
        if ("".equals(usernameInput.getText())) {
            JOptionPane.showMessageDialog(new JFrame(), "Mandatory Field! Email is Required", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if ((passwordInput.getPassword()).length == 0) {
            JOptionPane.showMessageDialog(new JFrame(), "Mandatory Field! Password is Required", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            name = usernameInput.getText();
            psswrdEntered = new String(passwordInput.getPassword());
            System.out.println(name + " " + psswrd);
        }

        try {
            DBConnection c1 = new DBConnection();
            String q = "select * from Student where username='" + name + "' and password='" + psswrdEntered + "'";
            ResultSet rs = c1.s.executeQuery(q);
            if (rs.next()) {
                JOptionPane.showMessageDialog(new JFrame(), "Login Successfull!", "Success", JOptionPane.INFORMATION_MESSAGE);
                currentStudentID = Integer.parseInt(rs.getString("stdID"));
                setVisible(false);
                new Student();
                Main.main.dispose();
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Incorrect Username or Password Entered!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        usernameInput.setText("");
        passwordInput.setText("");
    }//GEN-LAST:event_loginBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Left;
    private javax.swing.JPanel Right;
    private javax.swing.JLabel StudentImage;
    private javax.swing.JLabel StudentLoginText;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginBtn;
    private javax.swing.JLabel password;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JLabel username;
    private javax.swing.JTextField usernameInput;
    // End of variables declaration//GEN-END:variables
}
