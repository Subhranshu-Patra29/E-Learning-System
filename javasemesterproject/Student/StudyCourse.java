package javasemesterproject.Student;

//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.ResultSet;
//import javasemesterproject.DBConnection;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.ScrollPaneConstants;
//
//public class StudyCourse extends JFrame implements ActionListener{
//    JLabel title, subjectCbLbl, courseCbLbl, courseContentLbl;
//    JTextArea courseContent;
//    JComboBox coursesCb;
//    JPanel middlePanel;
//    JScrollPane scroll;
//    public StudyCourse(){
//        super("Study Course");
//        setLayout(new BorderLayout());
//        
//        title = new JLabel("Study Course", JLabel.CENTER);
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
//        
//        courseCbLbl = new JLabel("Select Course");
//        courseCbLbl.setFont(new Font(Font.SERIF,Font.BOLD, 16));
//        courseCbLbl.setBounds(80, 80, 120, 28);
//        courseCbLbl.setHorizontalAlignment(JLabel.LEFT);
//        middlePanel.add(courseCbLbl);
//        
//
//        coursesCb = new JComboBox(this.getCourses());
//        coursesCb.setSelectedIndex(-1);
//        coursesCb.setBounds(200, 80, 140, 28);
//        coursesCb.addActionListener(this);
//        middlePanel.add(coursesCb);
//        
//        
//        courseContentLbl = new JLabel("Course Content");
//        courseContentLbl.setFont(new Font(Font.SERIF,Font.BOLD, 16));
//        courseContentLbl.setBounds(80, 160, 120, 28);
//        courseContentLbl.setHorizontalAlignment(JLabel.LEFT);
//        middlePanel.add(courseContentLbl);
//        
//        courseContent = new JTextArea();
//        courseContent.setLineWrap(true);
//        courseContent.setWrapStyleWord(true);
//        scroll = new JScrollPane(courseContent);
//        scroll.setBounds(10, 190, 570, 180);
//        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
//        courseContent.setEditable(false);
//        middlePanel.add(scroll);
//       
//        
//
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setResizable(false);
//        setSize(600,450);
//        setLocation(420,260);
//        setVisible(true);
//    }
//        private String[] getCourses(){
//            String[] coursesData = null;
//            try{
//                DBConnection c1 = new DBConnection();
//                String q = "Select C.Name From Courses As C"
//                        + " Inner Join Enrollments As E ON E.course_ID = C.course_ID"
//                        + " Where E.stdID = '"+ StudentLogin.currentStudentID+"'";
//
//                ResultSet rs = c1.s.executeQuery(q);
//                int rowCount = 0;
//                while(rs.next())
//                    rowCount++;
//                coursesData = new String[rowCount];
//                rs.beforeFirst();
//                int i=0;
//                while(rs.next()){
//                    coursesData[i] = rs.getString("Name");
//                    i++;
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        return coursesData;
//    }
//    @Override
//    public void actionPerformed(ActionEvent ae){
//        if(ae.getSource() == coursesCb){
//            Object selected = coursesCb.getSelectedItem();
//            String courseName = selected.toString();
//            try{
//                DBConnection c1 = new DBConnection();
//                String q1  = "Select Content From Courses Where Name = '"+ courseName +"'";
//                ResultSet rs = c1.s.executeQuery(q1); 
//                rs.next();
//                String course_Content;
//                course_Content = rs.getString("Content");
//                courseContent.setText(course_Content);
//                
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//        }
//        public static void main(String[] args) {
//            new StudyCourse();
//        }
//
//    }


import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javasemesterproject.DBConnection;

public class StudyCourse extends JFrame implements ActionListener {

    private JLabel title, courseCbLbl, courseContentLbl;
    private JTextArea courseContent;
    private JComboBox<String> coursesCb;
    private JScrollPane scroll;

    public StudyCourse() {
        super("Study Course");
        setLayout(new BorderLayout());
        FlatLightLaf.setup();

        // Title Label
        title = new JLabel("Study Course", JLabel.CENTER);
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
        courseCbLbl = new JLabel("     Select Course:");
        courseCbLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        courseCbLbl.setBounds(35, 30, 140, 28);
        middlePanel.add(courseCbLbl);

        // Courses ComboBox
        coursesCb = new JComboBox<>(getCourses());
        coursesCb.setSelectedIndex(-1);
        coursesCb.setBounds(180, 30, 240, 28);
        coursesCb.addActionListener(this);
        middlePanel.add(coursesCb);

        // Content Label
        courseContentLbl = new JLabel("     Course Content:");
        courseContentLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        courseContentLbl.setBounds(35, 60, 150, 30);
        middlePanel.add(courseContentLbl);

        // Content Area
        courseContent = new JTextArea();
        courseContent.setLineWrap(true);
        courseContent.setWrapStyleWord(true);
        courseContent.setEditable(false);
        courseContent.setFont(new Font("SansSerif", Font.PLAIN, 14));

        // Scroll Pane for Content
        scroll = new JScrollPane(courseContent);
        scroll.setBounds(32, 100, 525, 205);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        middlePanel.add(scroll);

        // Frame Properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(600, 400);
        setLocationRelativeTo(null); // Centers the frame on the screen
        setVisible(true);
    }

    private String[] getCourses() {
        String[] coursesData = null;
        try {
            DBConnection c1 = new DBConnection();
            String q = "Select C.Name From Courses As C"
                    + " Inner Join Enrollments As E ON E.course_ID = C.course_ID"
                    + " Where E.stdID = '" + StudentLogin2.currentStudentID + "'";

            ResultSet rs = c1.s.executeQuery(q);
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            coursesData = new String[rowCount];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                coursesData[i] = rs.getString("Name");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coursesData;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == coursesCb) {
            Object selected = coursesCb.getSelectedItem();
            if (selected != null) {
                String courseName = selected.toString();
                try {
                    DBConnection c1 = new DBConnection();
                    String q = "SELECT Content FROM Courses WHERE Name = '" + courseName + "'";
                    ResultSet rs = c1.s.executeQuery(q);
                    if (rs.next()) {
                        courseContent.setText(rs.getString("Content"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new StudyCourse();
    }
}
