//package javasemesterproject.Student;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.ResultSet;
//import javasemesterproject.DBConnection;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//public class WithdrawCourse extends JFrame implements ActionListener{
//    JLabel title, courseCbLbl;
//    JComboBox courseCb;
//    JButton withdrawBtn;
//    JPanel middlePanel;
//    public WithdrawCourse(){
//        super("Withdraw Course");
//        setLayout(new BorderLayout());
//        
//        title = new JLabel("Withdraw Course", JLabel.CENTER);
//        title.setFont(title.getFont().deriveFont (22.0f));
//        title.setBackground(Color.LIGHT_GRAY);
//        title.setForeground(Color.BLACK);
//        title.setOpaque(true);
//        add(title, BorderLayout.NORTH);
//        
//        middlePanel = new JPanel();
//        middlePanel.setLayout(null);
//        add(middlePanel, BorderLayout.CENTER);
//        
//        courseCbLbl = new JLabel("Select Course");
//        courseCbLbl.setFont(new Font(Font.SERIF,Font.BOLD, 16));
//        courseCbLbl.setBounds(80, 30, 120, 28);
//        courseCbLbl.setHorizontalAlignment(JLabel.CENTER);
//        middlePanel.add(courseCbLbl);
//
//        courseCb = new JComboBox(this.getCourses());
//        courseCb.setSelectedIndex(-1);
//        courseCb.setBounds(200, 30, 140, 28);
//        courseCb.addActionListener(this);
//        middlePanel.add(courseCb);
//        
//        withdrawBtn = new JButton("Withdraw");
//        withdrawBtn.setFont(new Font(Font.SERIF,Font.BOLD, 15));
//        withdrawBtn.setHorizontalAlignment(JButton.CENTER);
//        withdrawBtn.addActionListener(this);
//        add(withdrawBtn, BorderLayout.SOUTH);
//        
//
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setResizable(false);
//        setSize(500,200);
//        setLocation(420,320);
//        setVisible(true);
//    }
//    private String[] getCourses(){
//        String[] courseData = null;
//        try{
//            DBConnection c1 = new DBConnection();
//
//            String q = "select C.Name from Courses As C"
//                    + " Inner Join Enrollments As E ON E.course_ID = C.course_ID"
//                    + " Where E.stdID = '"+ StudentLogin.currentStudentID +"'";
//
//            ResultSet rs = c1.s.executeQuery(q);
//            int rowCount = 0;
//            while(rs.next())
//                rowCount++;
//            courseData = new String[rowCount];
//            rs.beforeFirst();
//            int i=0;
//            while(rs.next()){
//                courseData[i] = rs.getString("Name");
//                i++;
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return courseData;
//    }
//    @Override
//    public void actionPerformed(ActionEvent ae){
//        if(ae.getSource() == withdrawBtn){
//            Object selected = courseCb.getSelectedItem();
//            String course_Name = selected.toString();
//            int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to Withdraw this Course?", "Select an Option...",
//            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
//            
//            if(input == 0){
//                try{
//                    DBConnection c1 = new DBConnection();
//
//                    String q = "Delete From Enrollments Where course_ID = (Select course_ID From Courses Where Name = '"+ course_Name +"')"
//                            + " And stdID = '"+ StudentLogin.currentStudentID +"'";
//                    int x = c1.s.executeUpdate(q);
//                    if(x == 0){
//                        JOptionPane.showMessageDialog(null, "Got some error");
//                    }else{
//                        JOptionPane.showMessageDialog(null, "Course Withdrawn Successfully");
//                        dispose();
//                    }
//            }catch(Exception e){
//                e.printStackTrace();
//                }
//            }
//        }
//        }
//        public static void main(String[] args) {
//            new WithdrawCourse();
//        }
//
//}
package javasemesterproject.Student;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javasemesterproject.DBConnection;
import javax.swing.*;

public class WithdrawCourse extends JFrame implements ActionListener {

    private JLabel title, courseCbLbl;
    private JComboBox<String> courseCb;
    private JButton withdrawBtn;

    public WithdrawCourse() {
        super("Withdraw Course");
        setLayout(new BorderLayout());
        FlatLightLaf.setup();

        // Title Label
        title = new JLabel("Withdraw Course", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setOpaque(true);
        title.setBackground(new Color(51, 102, 255));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // Middle Panel
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(null);
        add(middlePanel, BorderLayout.CENTER);

        // Course Label
        courseCbLbl = new JLabel("Select Course:");
        courseCbLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        courseCbLbl.setBounds(75, 85, 120, 28);
        middlePanel.add(courseCbLbl);

        // Courses ComboBox
        courseCb = new JComboBox<>(getCourses());
        courseCb.setSelectedIndex(-1);
        courseCb.setBounds(205, 85, 270, 30);
        courseCb.addActionListener(this);
        middlePanel.add(courseCb);

        // Withdraw Button
        withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        withdrawBtn.setForeground(Color.WHITE);
        withdrawBtn.setPreferredSize(new Dimension(30, 30));
        withdrawBtn.setBackground(new Color(92, 124, 250));
        withdrawBtn.setFocusPainted(false);
        withdrawBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        withdrawBtn.setOpaque(true);
        withdrawBtn.addActionListener(this);
        add(withdrawBtn, BorderLayout.SOUTH);

        // Frame Properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(600, 350);
        setLocationRelativeTo(null); // Centers the frame on the screen
        setVisible(true);
    }

    private String[] getCourses() {
        String[] courseData = null;
        try {
            DBConnection c1 = new DBConnection();
            String q = "select C.Name from Courses As C"
                    + " Inner Join Enrollments As E ON E.course_ID = C.course_ID"
                    + " Where E.stdID = '" + StudentLogin2.currentStudentID + "'";

            ResultSet rs = c1.s.executeQuery(q);

            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }

            courseData = new String[rowCount];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                courseData[i] = rs.getString("Name");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseData;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == withdrawBtn) {
            Object selected = courseCb.getSelectedItem();

            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Please select a course to withdraw.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String course_Name = selected.toString();
            int input = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to withdraw from this course?",
                    "Confirm Withdrawal", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (input == JOptionPane.YES_OPTION) {
                try {
                    DBConnection c1 = new DBConnection();
                    String q = "Delete From Enrollments Where course_ID = (Select course_ID From Courses Where Name = '"+ course_Name +"')" + " And stdID = '"+ StudentLogin2.currentStudentID +"'";
                    int result = c1.s.executeUpdate(q);

                    if (result > 0) {
                        JOptionPane.showMessageDialog(this, "Course withdrawn successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error while withdrawing course. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "An unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        new WithdrawCourse();
    }
}
