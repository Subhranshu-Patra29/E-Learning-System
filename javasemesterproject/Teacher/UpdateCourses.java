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
//import javax.swing.ScrollPaneConstants;
//
//
//public class UpdateCourses extends JFrame implements ActionListener{
//    JLabel title;
//    JPanel middlePanel;
//    JComboBox courseCb;
//    JTextArea courseDescription, courseContent;
//    JScrollPane scroll, scroll2;
//    JButton updateBtn;
//    public UpdateCourses() {
//        super("Update Course");
//        setLayout(new BorderLayout());
//        
//        title = new JLabel("Update Course", JLabel.CENTER);
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
//        courseCb = new JComboBox(this.getMyCourses());
//        courseCb.setSelectedIndex(-1);
//        courseCb.setBounds(200, 30, 140, 28);
//        courseCb.addActionListener(this);
//        middlePanel.add(courseCb);
//        
//        JLabel courseDescriptionLbl = new JLabel("Course Description");
//        courseDescriptionLbl.setFont(new Font(Font.SERIF,Font.BOLD, 16));
//        courseDescriptionLbl.setBounds(80, 100, 140, 28);
//        courseDescriptionLbl.setHorizontalAlignment(JLabel.LEFT);
//        middlePanel.add(courseDescriptionLbl);
//        
//        courseDescription = new JTextArea();
//        courseDescription.setLineWrap(true);
//        courseDescription.setWrapStyleWord(true);
//        scroll = new JScrollPane(courseDescription);
//        scroll.setBounds(150, 130, 460, 100);
//        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
//        courseDescription.setEditable(true);
//        middlePanel.add(scroll);
//        
//        
//        JLabel courseContentLbl = new JLabel("Course Content");
//        courseContentLbl.setFont(new Font(Font.SERIF,Font.BOLD, 16));
//        courseContentLbl.setBounds(80, 235, 140, 28);
//        courseContentLbl.setHorizontalAlignment(JLabel.LEFT);
//        middlePanel.add(courseContentLbl);
//        
//        courseContent = new JTextArea();
//        courseContent.setLineWrap(true);
//        courseContent.setWrapStyleWord(true);
//        scroll2 = new JScrollPane(courseContent);
//        scroll2.setBounds(10, 270, 685, 150);
//        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
//        courseContent.setEditable(true);
//        middlePanel.add(scroll2);
//        
//        updateBtn = new JButton("Update");
//        updateBtn.setFont(new Font(Font.SERIF,Font.BOLD, 15));
//        updateBtn.setHorizontalAlignment(JButton.CENTER);
//        updateBtn.addActionListener(this);
//        add(updateBtn, BorderLayout.SOUTH);
//        
//        
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setResizable(false);
//        setSize(720,520);
//        setLocation(375,145);
//        setVisible(true);
//    }
//
//    private String[] getMyCourses(){
//        String data[] = null;
//    try{
//        DBConnection c1 = new DBConnection();
//        String q1  = "Select Course_ID, Name From Courses Where teacherID = '"+ TeacherLogin.currentTeacherID +"'";
//        ResultSet rs = c1.s.executeQuery(q1);
//        int rowCount = 0;
//        while (rs.next())
//            rowCount++;
//        data = new String[rowCount];
//        int row = 0;
//        rs.beforeFirst();
//        while (rs.next()) {
//            data[row] = rs.getString("Name");
//            row++;
//        }
//    }catch(Exception e){
//        e.printStackTrace();
//        }
//        return data;
//    }
//    
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == courseCb){
//            Object selected = courseCb.getSelectedItem();
//            String courseName = selected.toString();
//            try{
//                DBConnection c1 = new DBConnection();
//                String q1  = "Select Description, Content From Courses As C"
//                        + " Where C.Name = '"+ courseName +"' And C.teacherID = '"+ TeacherLogin.currentTeacherID +"'";
//                ResultSet rs = c1.s.executeQuery(q1); 
//                rs.next();
//                courseDescription.setText(rs.getString("Description"));
//                courseContent.setText(rs.getString("Content"));
//                
//            }catch(Exception exception){
//                exception.printStackTrace();
//            }
//        }
//        else if(e.getSource() == updateBtn){
//            Object selected = courseCb.getSelectedItem();
//            String courseName = selected.toString();
//            String courseDescriptionStr = courseDescription.getText();
//            String courseContentStr = courseContent.getText();
//            try{
//                DBConnection c1 = new DBConnection();
//                String q1  = "Update Courses Set Description = '"+ courseDescriptionStr +"', Content = '"+ courseContentStr +"'"
//                        + " Where Name = '"+ courseName +"'";
//                
//                int x = c1.s.executeUpdate(q1);
//                if(x == 0){
//                    JOptionPane.showMessageDialog(null, "Got some error");
//                }else{
//                    JOptionPane.showMessageDialog(null, "Course Updated Successfully");
//                    setVisible(false);
//                }
//            }catch(Exception exc){
//                JOptionPane.showMessageDialog(null, "This Course is Already being Offered By other Teacher!");
//                exc.printStackTrace();
//            }
//        }
//    }
//    public static void main(String[] args) {
//        new UpdateCourses();
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

public class UpdateCourses extends JFrame implements ActionListener {
    private JComboBox<String> coursesCb;
    private JTextArea courseDescription, courseContent;
    private JTextField courseName;
    private JButton updateBtn;
    private String[] coursesData;

    public UpdateCourses() {
        // Apply FlatLaf theme
        FlatLightLaf.setup();

        setTitle("Update Courses");
        setLayout(new BorderLayout(10, 10));
        setSize(750, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Title
        JLabel title = new JLabel("Update Courses", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setOpaque(true);
        title.setBackground(new Color(51, 102, 255));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel(null);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Courses ComboBox
        JLabel coursesCbLbl = new JLabel("Select Course:");
        coursesCbLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        coursesCbLbl.setBounds(50, 20, 150, 30);
        centerPanel.add(coursesCbLbl);

        getCourses();
        coursesCb = new JComboBox<>(coursesData);
        coursesCb.setBounds(200, 20, 300, 30);
        coursesCb.addActionListener(this);
        centerPanel.add(coursesCb);

        // Course Name
        JLabel courseNameLbl = new JLabel("Course Name:");
        courseNameLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        courseNameLbl.setBounds(50, 70, 150, 30);
        centerPanel.add(courseNameLbl);

        courseName = new JTextField();
        courseName.setBounds(200, 70, 300, 30);
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

        // Update Button
        updateBtn = new JButton("Update Course");
        updateBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        updateBtn.setPreferredSize(new Dimension(30, 30));
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setPreferredSize(new Dimension(30, 30));
        updateBtn.setBackground(new Color(92, 124, 250));
        updateBtn.setFocusPainted(false);
        updateBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateBtn.setOpaque(true);
        updateBtn.addActionListener(this);
        add(updateBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void getCourses() {
        try {
            DBConnection c1 = new DBConnection();
            String query = "SELECT Course_ID, Name FROM Courses WHERE teacherID = " + TeacherLogin2.currentTeacherID;
            ResultSet rs = c1.s.executeQuery(query);

            rs.last();
            int rowCount = rs.getRow();
            coursesData = new String[rowCount];
            rs.beforeFirst();

            int i = 0;
            while (rs.next()) {
                coursesData[i++] = rs.getString("Name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCourseDetails(String courseName) {
        try {
            DBConnection c1 = new DBConnection();
            String query = "SELECT Description, Content FROM Courses WHERE Name = '" + courseName + "'";
            ResultSet rs = c1.s.executeQuery(query);

            if (rs.next()) {
                this.courseName.setText(courseName);
                courseDescription.setText(rs.getString("Description"));
                courseContent.setText(rs.getString("Content"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == coursesCb) {
            String selectedCourse = (String) coursesCb.getSelectedItem();
            if (selectedCourse != null) {
                loadCourseDetails(selectedCourse);
            }
        } else if (e.getSource() == updateBtn) {
            try {
                DBConnection c1 = new DBConnection();
                String query = String.format(
                        "UPDATE Courses SET Name = '%s', Description = '%s', Content = '%s' WHERE Name = '%s' AND teacherID = %d",
                        courseName.getText(), courseDescription.getText(), courseContent.getText(),
                        (String) coursesCb.getSelectedItem(), TeacherLogin2.currentTeacherID);

                int result = c1.s.executeUpdate(query);

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Course updated successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error updating course!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new UpdateCourses();
    }
}
