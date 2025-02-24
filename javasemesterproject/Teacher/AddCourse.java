//package javasemesterproject.Teacher;
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
//import javax.swing.JTextField;
//import javax.swing.ScrollPaneConstants;
//
//public class AddCourse extends JFrame implements ActionListener{
//    JLabel title, subjectCbLbl,courseNameLbl, courseDescriptionLbl, courseContentLbl;
//    JComboBox subjectsCb;
//    JTextArea courseDescription, courseContent;
//    JButton addBtn;
//    JPanel middlePanel;
//    JTextField courseName;
//    String SubjectsData[];
//    JScrollPane scroll, scroll2;
//    public AddCourse(){
//        super("Add Course");
//        setLayout(new BorderLayout());
//        
//        title = new JLabel("Add Course", JLabel.CENTER);
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
//        subjectCbLbl.setHorizontalAlignment(JLabel.LEFT);
//        middlePanel.add(subjectCbLbl);
//        
//        this.getSubjects();
//        subjectsCb = new JComboBox(SubjectsData);
//        subjectsCb.setSelectedIndex(-1);
//        subjectsCb.setBounds(200, 30, 140, 28);
//        subjectsCb.addActionListener(this);
//        middlePanel.add(subjectsCb);
//        
//        
//        courseNameLbl = new JLabel("Course Name");
//        courseNameLbl.setFont(new Font(Font.SERIF,Font.BOLD, 16));
//        courseNameLbl.setBounds(80, 75, 140, 28);
//        courseNameLbl.setHorizontalAlignment(JLabel.LEFT);
//        middlePanel.add(courseNameLbl);
//        
//        courseName = new JTextField();
//        courseName.setFont(new Font(Font.SERIF,Font.BOLD, 16));
//        courseName.setBounds(200, 75, 120, 28);
//        courseName.setHorizontalAlignment(JLabel.CENTER);
//        middlePanel.add(courseName);
//        
//        
//        courseDescriptionLbl = new JLabel("Course Description");
//        courseDescriptionLbl.setFont(new Font(Font.SERIF,Font.BOLD, 16));
//        courseDescriptionLbl.setBounds(80, 125, 140, 28);
//        courseDescriptionLbl.setHorizontalAlignment(JLabel.LEFT);
//        middlePanel.add(courseDescriptionLbl);
//        
//        courseDescription = new JTextArea();
//        courseDescription.setLineWrap(true);
//        courseDescription.setWrapStyleWord(true);
//        scroll = new JScrollPane(courseDescription);
//        scroll.setBounds(150, 155, 460, 100);
//        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
//        courseDescription.setEditable(true);
//        middlePanel.add(scroll);
//        
//        
//        courseContentLbl = new JLabel("Course Content");
//        courseContentLbl.setFont(new Font(Font.SERIF,Font.BOLD, 16));
//        courseContentLbl.setBounds(80, 260, 140, 28);
//        courseContentLbl.setHorizontalAlignment(JLabel.LEFT);
//        middlePanel.add(courseContentLbl);
//        
//        courseContent = new JTextArea();
//        courseContent.setLineWrap(true);
//        courseContent.setWrapStyleWord(true);
//        scroll2 = new JScrollPane(courseContent);
//        scroll2.setBounds(10, 290, 685, 150);
//        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
//        courseContent.setEditable(true);
//        middlePanel.add(scroll2);
//       
//
//        addBtn = new JButton("Add");
//        addBtn.setFont(new Font(Font.SERIF,Font.BOLD, 15));
//        addBtn.setHorizontalAlignment(JButton.CENTER);
//        addBtn.addActionListener(this);
//        add(addBtn, BorderLayout.SOUTH);
//        
//
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setResizable(false);
//        setSize(720,570);
//        setLocation(375,145);
//        setVisible(true);
//    }
//    private void getSubjects(){
//        try{
//            DBConnection c1 = new DBConnection();
//
//            String q = "select * from Subjects";
//
//            ResultSet rs = c1.s.executeQuery(q);
//            int rowCount = 0;
//            while(rs.next())
//                rowCount++;
//            SubjectsData = new String[rowCount];
//            rs.beforeFirst();
//            int i=0;
//            while(rs.next()){
//                SubjectsData[i] = rs.getString("Name");
//                i++;
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public void actionPerformed(ActionEvent ae){
//        if(ae.getSource() == addBtn){
//            String subjectStr = subjectsCb.getSelectedItem().toString();
//            String courseNameStr = courseName.getText();
//            String courseDescriptionStr = courseDescription.getText();
//            String courseContentStr = courseContent.getText();
//            try{
//                DBConnection c1 = new DBConnection();
//                String q1  = "Select Subject_ID From Subjects  Where Name = '"+ subjectStr +"'";
//                ResultSet rs = c1.s.executeQuery(q1); 
//                int subject_ID;
//                rs.next();
//                subject_ID = rs.getInt("Subject_ID");
//                
//                String q = "INSERT INTO Courses (Name, Description, Content, teacherID, Subject_ID) "
//                        + "Values ('" + courseNameStr +"', '" + courseDescriptionStr +"', '" + courseContentStr +"', "
//                        + TeacherLogin.currentTeacherID +", " + subject_ID + ")";
//
//                int x = c1.s.executeUpdate(q);
//                if(x == 0){
//                    JOptionPane.showMessageDialog(null, "Some Error Occured");
//                }else{
//                    JOptionPane.showMessageDialog(null, "Course Added!");
//                    dispose();
//                }
//            }catch(Exception e){
//                JOptionPane.showMessageDialog(null, "This Course is Already being Offered By other Teacher!");
//                e.printStackTrace();
//            }
//        }
//    }
//    public static void main(String[] args) {
//        new AddCourse();
//    }
// 
//}


package javasemesterproject.Teacher;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javasemesterproject.DBConnection;

public class AddCourse extends JFrame implements ActionListener {
    private JComboBox<String> subjectsCb;
    private JTextArea courseDescription, courseContent;
    private JTextField courseName;
    private JButton addBtn;
    private String[] subjectsData;

    public AddCourse() {
        // Apply FlatLaf theme
        FlatLightLaf.setup();

        setTitle("Add Course");
        setLayout(new BorderLayout(10, 10));
        setSize(750, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Title
        JLabel title = new JLabel("Add Course", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setOpaque(true);
        title.setBackground(new Color(51, 102, 255));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel(null);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Subject ComboBox
        JLabel subjectCbLbl = new JLabel("Select Subject:");
        subjectCbLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subjectCbLbl.setBounds(50, 20, 150, 30);
        centerPanel.add(subjectCbLbl);

        getSubjects();
        subjectsCb = new JComboBox<>(subjectsData);
        subjectsCb.setBounds(200, 20, 200, 30);
        centerPanel.add(subjectsCb);

        // Course Name
        JLabel courseNameLbl = new JLabel("Course Name:");
        courseNameLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        courseNameLbl.setBounds(50, 70, 150, 30);
        centerPanel.add(courseNameLbl);

        courseName = new JTextField();
        courseName.setBounds(200, 70, 200, 30);
        centerPanel.add(courseName);

        // Course Description
        JLabel courseDescriptionLbl = new JLabel("Course Description:");
        courseDescriptionLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        courseDescriptionLbl.setBounds(50, 120, 150, 30);
        centerPanel.add(courseDescriptionLbl);

        courseDescription = new JTextArea();
        courseDescription.setLineWrap(true);
        courseDescription.setWrapStyleWord(true);
        JScrollPane scroll1 = new JScrollPane(courseDescription);
        scroll1.setBounds(200, 120, 450, 100);
        centerPanel.add(scroll1);

        // Course Content
        JLabel courseContentLbl = new JLabel("Course Content:");
        courseContentLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        courseContentLbl.setBounds(50, 240, 150, 30);
        centerPanel.add(courseContentLbl);

        courseContent = new JTextArea();
        courseContent.setLineWrap(true);
        courseContent.setWrapStyleWord(true);
        JScrollPane scroll2 = new JScrollPane(courseContent);
        scroll2.setBounds(200, 240, 450, 150);
        centerPanel.add(scroll2);

        add(centerPanel, BorderLayout.CENTER);

        // Add Button
        addBtn = new JButton("Add Course");
        addBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        addBtn.setPreferredSize(new Dimension(30, 30));
        addBtn.setForeground(Color.WHITE);
        addBtn.setPreferredSize(new Dimension(30, 30));
        addBtn.setBackground(new Color(92, 124, 250));
        addBtn.setFocusPainted(false);
        addBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addBtn.setOpaque(true);
        addBtn.addActionListener(this);
        add(addBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void getSubjects() {
        try {
            DBConnection c1 = new DBConnection();
            String query = "SELECT Name FROM Subjects";
            ResultSet rs = c1.s.executeQuery(query);

            rs.last();
            int rowCount = rs.getRow();
            subjectsData = new String[rowCount];
            rs.beforeFirst();

            int i = 0;
            while (rs.next()) {
                subjectsData[i++] = rs.getString("Name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            String subject = (String) subjectsCb.getSelectedItem();
            String name = courseName.getText();
            String description = courseDescription.getText();
            String content = courseContent.getText();

            try {
                DBConnection c1 = new DBConnection();
                String query = String.format(
                        "INSERT INTO Courses (Name, Description, Content, teacherID, Subject_ID) " +
                        "VALUES ('%s', '%s', '%s', '%d', (SELECT Subject_ID FROM Subjects WHERE Name = '%s'))",
                        name, description, content, TeacherLogin2.currentTeacherID, subject);

                int result = c1.s.executeUpdate(query);

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Course added successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error adding course!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new AddCourse();
    }
}