//package javasemesterproject.Teacher;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import javasemesterproject.DBConnection;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.JTableHeader;
//
//public class ViewMyStudents extends JFrame implements ActionListener{
//    JLabel title, courseCbLbl;
//    JComboBox  coursesCb;
//    JPanel middlePanel;
//    JScrollPane scroll;
//    JTable table;
//    DefaultTableModel model;
//    public ViewMyStudents(){
//        super("View My Students");
//        setLayout(new BorderLayout());
//        
//        title = new JLabel("View My Students", JLabel.CENTER);
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
//        courseCbLbl.setBounds(80, 50, 120, 28);
//        courseCbLbl.setHorizontalAlignment(JLabel.LEFT);
//        middlePanel.add(courseCbLbl);
//        
//
//        coursesCb = new JComboBox(this.getMyCourses());
//        coursesCb.setSelectedIndex(-1);
//        coursesCb.setBounds(200, 50, 140, 28);
//        coursesCb.addActionListener(this);
//        middlePanel.add(coursesCb);
//        
//        
//        model = new DefaultTableModel();
//        table = new JTable(model);
//        JTableHeader header = table.getTableHeader();
//        header.setBackground(Color.LIGHT_GRAY);
//        JScrollPane pane = new JScrollPane(table);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
//        pane.setBounds(20, 150, 550, 200);
//        middlePanel.add(pane);
//       
//        
//        setResizable(false);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setSize(600,450);
//        setLocation(420,260);
//        setVisible(true);
//    }
//    private String[] getMyCourses(){
//        String[] Course_Names = null;
//        try{
//            DBConnection c1 = new DBConnection();
//            String q1  = "Select Name From Courses Where teacherID = '"+ TeacherLogin.currentTeacherID +"'";
//            ResultSet rs = c1.s.executeQuery(q1); 
//            int rowCount = 0;
//            while(rs.next())
//                rowCount++;
//            Course_Names = new String[rowCount];
//            rs.beforeFirst();
//            int i=0;
//            while(rs.next()){
//                Course_Names[i] = rs.getString("Name");
//                i++;
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return Course_Names;
//    }
//    @Override
//    public void actionPerformed(ActionEvent ae){
//        String[] columnNames;
//        String[][] data;
//        if(ae.getSource() == coursesCb){
//            // to Clear all records from JTable
//            model.setRowCount(0);
//            // to Clear all Columns from JTable
//            model.setColumnCount(0);
//            Object selected = coursesCb.getSelectedItem();
//            String courseName = selected.toString();
//            try{
//                DBConnection c1 = new DBConnection();
//                String q = "Select S.fname, S.lname, S.Email_ID, S.Registration_Date, S.Gender"
//                        + " From Student As S"
//                        + " Inner Join Enrollments As E ON E.stdID = S.stdID"
//                        + " Where E.course_ID = (select course_ID From Courses Where Name = '"+ courseName +"')";
//
//                ResultSet rs = c1.s.executeQuery(q); 
//                ResultSetMetaData rsmd = rs.getMetaData();
//                int    columnCount  =  rsmd.getColumnCount();
//                int    rowCount = 0;
//                while (rs.next())
//                    rowCount++;
//                // The column count starts from 1
//                columnNames = new String[columnCount];
//                int CIndex = 1;
//                for(int in = 0 ; in < columnCount; in++) {
//                  columnNames[in]  = rsmd.getColumnName(CIndex);
//                  CIndex++;
//                }
//                data = new String[rowCount][columnCount];
//                int row = 0;
//                rs.beforeFirst();
//                while (rs.next()) {
//                    for (int c = 0 ; c < columnCount; c++) {
//                      data[row][c] = rs.getString(columnNames[c]);
//                    }
//                    row++;
//                }
//                for(int c = 0; c < columnNames.length; c++)
//                    model.addColumn(columnNames[c]);
//                for(int r = 0; r < data.length ; r++){
//                         model.addRow(data[r]);
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//       }
//    }
//    public static void main(String[] args) {
//        new ViewMyStudents();
//    }
//}
//



package javasemesterproject.Teacher;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javasemesterproject.DBConnection;

public class ViewMyStudents extends JFrame implements ActionListener {
    private JComboBox<String> coursesCb;
    private DefaultTableModel model;
    private JTable table;

    public ViewMyStudents() {
        // Apply FlatLaf theme
        FlatLightLaf.setup();

        setTitle("View My Students");
        setLayout(new BorderLayout(10, 10));
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Title
        JLabel title = new JLabel("View My Students", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setOpaque(true);
        title.setBackground(new Color(51, 102, 255));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel(null);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(centerPanel, BorderLayout.CENTER);

        // Course Selection Label
        JLabel courseCbLbl = new JLabel("Select Course:");
        courseCbLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        courseCbLbl.setBounds(30, 30, 150, 30);
        centerPanel.add(courseCbLbl);

        // Course ComboBox
        coursesCb = new JComboBox<>(getMyCourses());
        coursesCb.setBounds(150, 30, 300, 30);
        coursesCb.addActionListener(this);
        centerPanel.add(coursesCb);

        // Table
        model = new DefaultTableModel();
        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(30);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 16));
        header.setBackground(new Color(220, 220, 220));

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(30, 80, 720, 400);
        centerPanel.add(pane);

        // Footer
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private String[] getMyCourses() {
        try {
            DBConnection c1 = new DBConnection();
            String query = "SELECT Name FROM Courses WHERE teacherID = '" + TeacherLogin2.currentTeacherID + "'";
            ResultSet rs = c1.s.executeQuery(query);

            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            String[] courseNames = new String[rowCount];
            int i = 0;
            while (rs.next()) {
                courseNames[i++] = rs.getString("Name");
            }
            return courseNames;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == coursesCb) {
            loadStudentData((String) coursesCb.getSelectedItem());
        }
    }

    private void loadStudentData(String courseName) {
        try {
            DBConnection c1 = new DBConnection();
            String query = "SELECT S.fname AS FirstName, S.lname AS LastName, S.Email_ID AS Email, "
                         + "S.Registration_Date AS RegistrationDate, S.Gender AS Gender "
                         + "FROM Student S "
                         + "INNER JOIN Enrollments E ON E.stdID = S.stdID "
                         + "WHERE E.course_ID = (SELECT course_ID FROM Courses WHERE Name = '" + courseName + "')";

            ResultSet rs = c1.s.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();

            // Clear table model
            model.setRowCount(0);
            model.setColumnCount(0);

            // Set columns
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Populate data
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getString(i);
                }
                model.addRow(row);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ViewMyStudents();
    }
}
