//package javasemesterproject.Teacher;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.HeadlessException;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import javasemesterproject.DBConnection;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.JTableHeader;
//
//public class ViewMyCourses extends JFrame implements ActionListener{
//    JLabel title;
//    JScrollPane scroll;
//    JTable table;
//    DefaultTableModel model;
//    String[][] data;
//    String[] columnNames;
//    JButton deleteButton;
//    public ViewMyCourses(){
//        super("View My Courses");
//        setLayout(new BorderLayout());
//        
//        title = new JLabel("View My Courses", JLabel.CENTER);
//        title.setFont(title.getFont().deriveFont (22.0f));
//        title.setBackground(Color.LIGHT_GRAY);
//        title.setForeground(Color.BLACK);
//        title.setOpaque(true);
//        add(title, BorderLayout.NORTH);
//        
//        
//        this.getMyCourses();
//        model = new DefaultTableModel(data, columnNames);
//        table = new JTable(model);
//        JTableHeader header = table.getTableHeader();
//        header.setBackground(Color.LIGHT_GRAY);
//        JScrollPane pane = new JScrollPane(table);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
//        table.setPreferredScrollableViewportSize(table.getPreferredSize());
//        table.setRowHeight(30);
//        pane.setBounds(20, 150, 550, 200);
//        add(pane, BorderLayout.CENTER);
//        
//        deleteButton = new JButton("Delete");
//        deleteButton.setHorizontalAlignment(JButton.CENTER);
//        deleteButton.addActionListener(this);
//        deleteButton.setToolTipText( "Click to Delete Selected Course." );
//        deleteButton.setPreferredSize(new Dimension(20, 50));
//        add(deleteButton, BorderLayout.SOUTH);
//       
//        
//        setResizable(false);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setSize(500,360);
//        setLocation(420,260);
//        setVisible(true);
//    }
//    private void getMyCourses(){
//        try{
//            DBConnection c1 = new DBConnection();
//            String q1  = "Select Course_ID, Name From Courses Where teacherID = '"+ TeacherLogin.currentTeacherID +"'";
//            ResultSet rs = c1.s.executeQuery(q1); 
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int    columnCount  =  rsmd.getColumnCount();
//            int    rowCount = 0;
//            while (rs.next())
//                rowCount++;
//            // The column count starts from 1
//            columnNames = new String[columnCount];
//            int CIndex = 1;
//            for(int in = 0 ; in < columnCount; in++) {
//              columnNames[in]  = rsmd.getColumnName(CIndex);
//              CIndex++;
//            }
//            data = new String[rowCount][columnCount];
//            int row = 0;
//            rs.beforeFirst();
//            while (rs.next()) {
//                for (int c = 0 ; c < columnCount; c++) {
//                  data[row][c] = rs.getString(columnNames[c]);
//                }
//                row++;
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//    
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == deleteButton){
//            // check for selected row first
//            if(table.getSelectedRow() != -1) {
//                int rowNum = table.getSelectedRow();
//                // 0 is the Column index which is ID
//                String course_ID = model.getValueAt(rowNum,0).toString();
//                
//                try{
//                    DBConnection c1 = new DBConnection();
//                    String q = "Delete From Courses Where course_ID ='"+ course_ID +"'";
//                    int x = c1.s.executeUpdate(q); 
//                    if(x == 0){
//                        JOptionPane.showMessageDialog(null, "Error Occured");
//                    }else{
//                         JOptionPane.showMessageDialog(null, "Selected Course deleted successfully");
//                         dispose();
//                         new ViewMyCourses();
//                    }
//                }
//                catch(HeadlessException | SQLException exception){
//                    exception.printStackTrace();
//                }
//            }
//        }
//    }
//    public static void main(String[] args) {
//        new ViewMyCourses();
//    }
//}


package javasemesterproject.Teacher;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import javasemesterproject.DBConnection;

public class ViewMyCourses extends JFrame {
    private JTable coursesTable;
    private JScrollPane scrollPane;
    private String[] columnNames = {"Course Name", "Description", "Content"};
    private String[][] data;

    public ViewMyCourses() {
        // Apply FlatLaf theme
        FlatLightLaf.setup();

        setTitle("My Courses");
        setLayout(new BorderLayout(10, 10));
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Title
        JLabel title = new JLabel("My Courses", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setOpaque(true);
        title.setBackground(new Color(51, 102, 255));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // Load course data
        loadCourses();

        // Courses table
        coursesTable = new JTable(data, columnNames);
        coursesTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
        coursesTable.setRowHeight(30);
        coursesTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        scrollPane = new JScrollPane(coursesTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Courses Details"));
        add(scrollPane, BorderLayout.CENTER);

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadCourses() {
        try {
            DBConnection c1 = new DBConnection();
            String query = "SELECT Name, Description, Content FROM Courses WHERE teacherID = " + TeacherLogin2.currentTeacherID;
            ResultSet rs = c1.s.executeQuery(query);

            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            data = new String[rowCount][3];
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString("Name");
                data[i][1] = rs.getString("Description");
                data[i][2] = rs.getString("Content");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            data = new String[0][3];
        }
    }

    public static void main(String[] args) {
        new ViewMyCourses();
    }
}
