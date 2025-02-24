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
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.ScrollPaneConstants;
//
//public class EnrollCourse extends JFrame implements ActionListener{
//    JLabel title, subjectCbLbl, courseCbLbl, courseDescriptionLbl;
//    JTextArea courseDescription;
//    JComboBox subjectsCb, coursesCb;
//    JButton enrollBtn;
//    JPanel middlePanel;
//    JScrollPane scroll;
//    public EnrollCourse(){
//        super("Enroll Course");
//        setLayout(new BorderLayout());
//        
//        title = new JLabel("Enroll Course", JLabel.CENTER);
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
//        subjectCbLbl = new JLabel("Select Subject");
//        subjectCbLbl.setFont(new Font(Font.SERIF,Font.BOLD, 16));
//        subjectCbLbl.setBounds(80, 30, 120, 28);
//        subjectCbLbl.setHorizontalAlignment(JLabel.CENTER);
//        middlePanel.add(subjectCbLbl);
//
//        subjectsCb = new JComboBox(this.getSubjects());
//        subjectsCb.setSelectedIndex(-1);
//        subjectsCb.setBounds(200, 30, 140, 28);
//        subjectsCb.addActionListener(this);
//        middlePanel.add(subjectsCb);
//        
//        courseCbLbl = new JLabel("Select Course");
//        courseCbLbl.setFont(new Font(Font.SERIF,Font.BOLD, 16));
//        courseCbLbl.setBounds(80, 80, 120, 28);
//        courseCbLbl.setHorizontalAlignment(JLabel.CENTER);
//        middlePanel.add(courseCbLbl);
//        
//        
//        coursesCb = new JComboBox();
//        coursesCb.setSelectedIndex(-1);
//        coursesCb.setBounds(200, 80, 140, 28);
//        coursesCb.addActionListener(this);
//        middlePanel.add(coursesCb);
//        
//        
//        courseDescriptionLbl = new JLabel("Course Description");
//        courseDescriptionLbl.setFont(new Font(Font.SERIF,Font.BOLD, 16));
//        courseDescriptionLbl.setBounds(80, 130, 140, 28);
//        courseDescriptionLbl.setHorizontalAlignment(JLabel.CENTER);
//        middlePanel.add(courseDescriptionLbl);
//        
//        courseDescription = new JTextArea();
//        courseDescription.setLineWrap(true);
//        courseDescription.setWrapStyleWord(true);
//        scroll = new JScrollPane(courseDescription);
//        scroll.setBounds(150, 160, 230, 100);
//        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
//        courseDescription.setEditable(false);
//        middlePanel.add(scroll);
//       
//
//        enrollBtn = new JButton("Enroll");
//        enrollBtn.setFont(new Font(Font.SERIF,Font.BOLD, 15));
//        enrollBtn.setHorizontalAlignment(JButton.CENTER);
//        enrollBtn.addActionListener(this);
//        add(enrollBtn, BorderLayout.SOUTH);
//        
//
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setResizable(false);
//        setSize(500,400);
//        setLocation(420,320);
//        setVisible(true);
//    }
//    private String[] getSubjects(){
//        String[] subjectsData = null;
//        try{
//            DBConnection c1 = new DBConnection();
//
//            String q = "select * from Subjects";
//
//            ResultSet rs = c1.s.executeQuery(q);
//            int rowCount = 0;
//            while(rs.next())
//                rowCount++;
//            subjectsData = new String[rowCount];
//            rs.beforeFirst();
//            int i=0;
//            while(rs.next()){
//                subjectsData[i] = rs.getString("Name");
//                i++;
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return subjectsData;
//    }
//        private void getSetCourses(int subjectID){
//            String[] coursesData;
//        try{
//            DBConnection c1 = new DBConnection();
//            String q = "select * from Courses Where Subject_ID = '"+ subjectID +"'";
//
//            ResultSet rs = c1.s.executeQuery(q);
//            int rowCount = 0;
//            while(rs.next())
//                rowCount++;
//            coursesData = new String[rowCount];
//            rs.beforeFirst();
//            int i=0;
//            while(rs.next()){
//                coursesData[i] = rs.getString("Name");
//                i++;
//            }
//            for(int j=0; j< coursesData.length ; j++)
//                coursesCb.addItem(coursesData[j]);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public void actionPerformed(ActionEvent ae){
//        if(ae.getSource() == subjectsCb){
//            coursesCb.removeAllItems();
//            courseDescription.setText(null);
//            Object selected = subjectsCb.getSelectedItem();
//            String subjectName = selected.toString();
//            try{
//                DBConnection c1 = new DBConnection();
//                String q1  = "Select Subject_ID From Subjects Where Name = '"+ subjectName +"'";
//                ResultSet rs = c1.s.executeQuery(q1); 
//                int subjectID;
//                rs.next();
//                subjectID = rs.getInt("Subject_ID");
//                this.getSetCourses(subjectID);
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//        else if(ae.getSource() == coursesCb){
//            Object selectedMain = coursesCb.getSelectedItem();
//            if(selectedMain == null){
//                System.out.println("Null Value");
//            }
//            else{
//                Object selected = coursesCb.getSelectedItem();
//                String courseName = selected.toString();
//                try{
//                    DBConnection c1 = new DBConnection();
//                    String q1  = "Select Description From Courses Where Name = '"+ courseName +"'";
//                    ResultSet rs = c1.s.executeQuery(q1);
//                    String course_Description;
//                    rs.next();
//                    course_Description = rs.getString("Description");
//    //                courseDescriptionLbl.setVisible(true);
//    //                scroll.setVisible(true);
//                    courseDescription.setText(course_Description);
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }
//        else if(ae.getSource() == enrollBtn){
//                String courseNameStr = coursesCb.getSelectedItem().toString();
//                java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
//                try{
//                    DBConnection c1 = new DBConnection();
//                    String q1  = "Select course_ID From Courses Where Name = '"+ courseNameStr +"'";
//                    ResultSet rs = c1.s.executeQuery(q1); 
//                    int course_ID;
//                    rs.next();
//                    course_ID = rs.getInt("course_ID");
//
//                    String q = "INSERT INTO Enrollments (Enrollment_Date, course_ID, stdID) "
//                            + "Values ('" + sqlDate +"', '" + course_ID +"', '" + StudentLogin.currentStudentID +"')";
//
//                    int x = c1.s.executeUpdate(q);
//                    if(x == 0){
//                        JOptionPane.showMessageDialog(null, "Some Error Occured");
//                    }else{
//                        JOptionPane.showMessageDialog(null, "Course Enrollement Successfull!");
//                        dispose();
//                    }
//                }catch(Exception e){
//                    JOptionPane.showMessageDialog(null, "You are Already Enrolled in this Course!");
//                    e.printStackTrace();
//                }
//        }
//        }
//        public static void main(String[] args) {
//            new EnrollCourse();
//        }
//
//    }

package javasemesterproject.Student;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;
import javasemesterproject.DBConnection;

public class EnrollCourse extends JFrame implements ActionListener {

    private JLabel title, subjectCbLbl, courseCbLbl, courseDescriptionLbl;
    private JTextArea courseDescription;
    private JComboBox<String> subjectsCb, coursesCb;
    private JButton enrollBtn;
    private JScrollPane scroll;

    public EnrollCourse() {
        super("Enroll Course");

        // Set FlatLaf theme
        FlatLightLaf.setup();

        // Configure the JFrame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centers the window

        // Create a border layout
        setLayout(new BorderLayout(10, 10));

        // Header
        title = new JLabel("Enroll in a Course", JLabel.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        title.setOpaque(true);
        title.setBackground(new Color(51, 102, 255));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // Middle panel for form
        JPanel middlePanel = new JPanel(new GridBagLayout());
        add(middlePanel, BorderLayout.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Subject selection
        subjectCbLbl = new JLabel("Select Subject:");
        subjectCbLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        middlePanel.add(subjectCbLbl, gbc);

        subjectsCb = new JComboBox<>(getSubjects());
        subjectsCb.setSelectedIndex(-1);
        subjectsCb.addActionListener(this);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        middlePanel.add(subjectsCb, gbc);

        // Course selection
        courseCbLbl = new JLabel("Select Course:");
        courseCbLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        middlePanel.add(courseCbLbl, gbc);

        coursesCb = new JComboBox<>();
        coursesCb.setSelectedIndex(-1);
        coursesCb.addActionListener(this);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        middlePanel.add(coursesCb, gbc);

        // Course description
        courseDescriptionLbl = new JLabel("Course Description:");
        courseDescriptionLbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        middlePanel.add(courseDescriptionLbl, gbc);

        courseDescription = new JTextArea(10, 30);
        courseDescription.setLineWrap(true);
        courseDescription.setWrapStyleWord(true);
        courseDescription.setEditable(false);
        courseDescription.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        scroll = new JScrollPane(courseDescription);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        middlePanel.add(scroll, gbc);

        // Enroll button
        enrollBtn = new JButton("Enroll");
        enrollBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        enrollBtn.setForeground(Color.WHITE);
        enrollBtn.setPreferredSize(new Dimension(30, 30));
        enrollBtn.setBackground(new Color(92, 124, 250));
        enrollBtn.setFocusPainted(false);
        enrollBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        enrollBtn.setOpaque(true);
        enrollBtn.addActionListener(this);
        add(enrollBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    private String[] getSubjects() {
        String[] subjectsData = null;
        try {
            DBConnection c1 = new DBConnection();
            String q = "SELECT * FROM Subjects";
            ResultSet rs = c1.s.executeQuery(q);

            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            subjectsData = new String[rowCount];
            int i = 0;
            while (rs.next()) {
                subjectsData[i] = rs.getString("Name");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjectsData;
    }

    private void getSetCourses(int subjectID) {
        coursesCb.removeAllItems();
        try {
            DBConnection c1 = new DBConnection();
            String q = "SELECT * FROM Courses WHERE Subject_ID = '" + subjectID + "'";
            ResultSet rs = c1.s.executeQuery(q);

            while (rs.next()) {
                coursesCb.addItem(rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == subjectsCb) {
            Object selected = subjectsCb.getSelectedItem();
            if (selected != null) {
                String subjectName = selected.toString();
                try {
                    DBConnection c1 = new DBConnection();
                    String q1 = "SELECT Subject_ID FROM Subjects WHERE Name = '" + subjectName + "'";
                    ResultSet rs = c1.s.executeQuery(q1);

                    if (rs.next()) {
                        int subjectID = rs.getInt("Subject_ID");
                        getSetCourses(subjectID);
                        courseDescription.setText(""); // Clear description
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource() == coursesCb) {
            Object selected = coursesCb.getSelectedItem();
            if (selected != null) {
                String courseName = selected.toString();
                try {
                    DBConnection c1 = new DBConnection();
                    String q1 = "SELECT Description FROM Courses WHERE Name = '" + courseName + "'";
                    ResultSet rs = c1.s.executeQuery(q1);

                    if (rs.next()) {
                        courseDescription.setText(rs.getString("Description"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource() == enrollBtn) {
            String courseNameStr = (String) coursesCb.getSelectedItem();
            if (courseNameStr != null) {
                java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
                try {
                    DBConnection c1 = new DBConnection();
                    String q1 = "SELECT course_ID FROM Courses WHERE Name = '" + courseNameStr + "'";
                    ResultSet rs = c1.s.executeQuery(q1);

                    if (rs.next()) {
                        int courseID = rs.getInt("course_ID");
                        String q = "INSERT INTO Enrollments (Enrollment_Date, course_ID, stdID) " +
                                "VALUES ('" + sqlDate + "', '" + courseID + "', '" + StudentLogin2.currentStudentID + "')";
                        int x = c1.s.executeUpdate(q);

                        if (x > 0) {
                            JOptionPane.showMessageDialog(this, "Course Enrollment Successful!");
                            dispose();
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "You are already enrolled in this course!");
                }
            }
        }
    }

    public static void main(String[] args) {
        new EnrollCourse();
    }
}
