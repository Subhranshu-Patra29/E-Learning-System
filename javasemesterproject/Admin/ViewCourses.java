//package javasemesterproject.Admin;
//
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.JTableHeader;
//import javasemesterproject.DBConnection;
//
//public class ViewCourses extends JFrame implements ActionListener {
//    String columnNames[];
//    String[][] data;
//    JTable table;
//    DefaultTableModel model;
//    JLabel title, cbLbl;
//    JComboBox<String> filterBySubjectCb;
//    JPanel middlePanel, titlePanel;
//    JScrollPane pane;
//
//    public ViewCourses() {
//        super("View Courses");
//        setLayout(new BorderLayout());
//
//        // Title panel
//        titlePanel = new JPanel();
//        titlePanel.setBackground(Color.LIGHT_GRAY);
//        title = new JLabel("Courses", JLabel.CENTER);
//        title.setFont(new Font("Arial", Font.BOLD, 30));
//        title.setForeground(Color.BLACK);
//        titlePanel.add(title);
//        add(titlePanel, BorderLayout.NORTH);
//
//        // Center panel with filtering and table
//        middlePanel = new JPanel(new BorderLayout());
//        middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//
//        // Filter Panel
//        JPanel filterPanel = new JPanel();
//        filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        cbLbl = new JLabel("Filter By Subject: ");
//        cbLbl.setFont(new Font("Arial", Font.PLAIN, 16));
//        filterPanel.add(cbLbl);
//
//        filterBySubjectCb = new JComboBox<>(this.getSubjects());
//        filterBySubjectCb.addActionListener(this);
//        filterPanel.add(filterBySubjectCb);
//
//        middlePanel.add(filterPanel, BorderLayout.NORTH);
//
//        // Table Panel
//        model = new DefaultTableModel();
//        table = new JTable(model);
//        JTableHeader header = table.getTableHeader();
//        header.setBackground(Color.LIGHT_GRAY);
//        header.setFont(new Font("Arial", Font.BOLD, 16));
//        table.setFont(new Font("Arial", Font.PLAIN, 14));
//        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        table.setRowHeight(40);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//
//        // Adding table to scroll pane
//        pane = new JScrollPane(table);
//        middlePanel.add(pane, BorderLayout.CENTER);
//
//        add(middlePanel, BorderLayout.CENTER);
//
//        // Window settings
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setResizable(false);
//        setSize(600, 450);
//        setLocation(420, 260);
//        setVisible(true);
//
//        // Load initial data
//        this.getCourses("");
//    }
//
//    private String[] getSubjects() {
//        String[] subjects = null;
//        DBConnection c1 = null;
//        ResultSet rs = null;
//        try {
//            c1 = new DBConnection();
//            String q = "SELECT Name FROM Subjects";
//            rs = c1.s.executeQuery(q);
//
//            int rowCount = 0;
//            while (rs.next()) {
//                rowCount++;
//            }
//
//            subjects = new String[rowCount + 1];
//            subjects[0] = "All Subjects"; // Default option for no filter
//            int row = 1;
//            rs.beforeFirst();
//            while (rs.next()) {
//                subjects[row] = rs.getString("Name");
//                row++;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (c1 != null) c1.Close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return subjects;
//    }
//
//    private void getCourses(String subjectName) {
//        DBConnection c1 = null;
//        ResultSet rs = null;
//        try {
//            c1 = new DBConnection();
//            String query = subjectName.isEmpty() ? 
//                "SELECT C.Name AS Course, CONCAT(T.fname, ' ', T.lname) AS Teacher_Name, T.Email_ID AS Teacher_Email " +
//                "FROM Courses AS C " +
//                "INNER JOIN Teacher AS T ON T.teacherID = C.teacherID" :
//                "SELECT C.Name AS Course, CONCAT(T.fname, ' ', T.lname) AS Teacher_Name, T.Email_ID AS Teacher_Email " +
//                "FROM Courses AS C " +
//                "INNER JOIN Teacher AS T ON T.teacherID = C.teacherID " +
//                "WHERE C.Subject_ID = (SELECT Subject_ID FROM Subjects WHERE Name = '" + subjectName + "')";
//
//            rs = c1.s.executeQuery(query);
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int columnCount = rsmd.getColumnCount();
//            int rowCount = 0;
//            while (rs.next()) rowCount++;
//
//            columnNames = new String[columnCount];
//            int CIndex = 1;
//            for (int i = 0; i < columnCount; i++) {
//                columnNames[i] = rsmd.getColumnLabel(CIndex++);
//            }
//
//            data = new String[rowCount][columnCount];
//            int row = 0;
//            rs.beforeFirst();
//            while (rs.next()) {
//                int col = 0;
//                for (int c = 1; c <= columnCount; c++) {
//                    data[row][col++] = rs.getString(c);
//                }
//                row++;
//            }
//
//            // Update table model
//            model.setRowCount(0); // Clear previous rows
//            model.setColumnCount(0); // Clear previous columns
//            for (String columnName : columnNames) {
//                model.addColumn(columnName);
//            }
//            for (String[] rowData : data) {
//                model.addRow(rowData);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (c1 != null) c1.Close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == filterBySubjectCb) {
//            String selectedSubject = filterBySubjectCb.getSelectedItem().toString();
//            this.getCourses(selectedSubject.equals("All Subjects") ? "" : selectedSubject);
//        }
//    }
//
//    public static void main(String[] args) {
//        new ViewCourses();
//    }
//}

package javasemesterproject.Admin;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javasemesterproject.DBConnection;

public class ViewCourses extends JFrame implements ActionListener {
    private String[] columnNames;
    private String[][] data;
    private JTable table;
    private DefaultTableModel model;
    private JLabel title, cbLbl;
    private JComboBox<String> filterBySubjectCb;
    private JPanel middlePanel, titlePanel;
    private JScrollPane pane;

    public ViewCourses() {
        super("View Courses");
        FlatLightLaf.setup(); // Apply FlatLaf theme
        setLayout(new BorderLayout());

        // Title panel
        titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.decode("#F5F5F5")); // Light gray background
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        title = new JLabel("Courses", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setOpaque(true);
        title.setBackground(new Color(51, 102, 255));
        title.setForeground(Color.WHITE);;
        titlePanel.add(title, BorderLayout.CENTER);

        add(titlePanel, BorderLayout.NORTH);

        // Center panel with filtering and table
        middlePanel = new JPanel(new BorderLayout());
        middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Filter Panel
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cbLbl = new JLabel("Filter By Subject: ");
        cbLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        filterPanel.add(cbLbl);

        filterBySubjectCb = new JComboBox<>(this.getSubjects());
        filterBySubjectCb.setFont(new Font("SansSerif", Font.PLAIN, 14));
        filterBySubjectCb.addActionListener(this);
        filterPanel.add(filterBySubjectCb);

        middlePanel.add(filterPanel, BorderLayout.NORTH);

        // Table Panel
        model = new DefaultTableModel();
        table = new JTable(model);
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#E0E0E0"));
        header.setFont(new Font("SansSerif", Font.BOLD, 16));
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(30);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Adding table to scroll pane
        pane = new JScrollPane(table);
        middlePanel.add(pane, BorderLayout.CENTER);

        add(middlePanel, BorderLayout.CENTER);

        // Window settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setVisible(true);

        // Load initial data
        this.getCourses("");
    }

    private String[] getSubjects() {
        String[] subjects = null;
        DBConnection c1 = new DBConnection();
        ResultSet rs = null;
        try {
            String q = "SELECT Name FROM Subjects";
            rs = c1.s.executeQuery(q);

            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }

            subjects = new String[rowCount + 1];
            subjects[0] = "All Subjects"; // Default option for no filter
            int row = 1;
            rs.beforeFirst();
            while (rs.next()) {
                subjects[row] = rs.getString("Name");
                row++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                c1.Close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return subjects;
    }

    private void getCourses(String subjectName) {
        DBConnection c1 = new DBConnection();
        ResultSet rs = null;
        try {
            String query = subjectName.isEmpty() ? 
                "SELECT C.Name AS Course, CONCAT(T.fname, ' ', T.lname) AS Teacher_Name, T.Email_ID AS Teacher_Email " +
                "FROM Courses AS C " +
                "INNER JOIN Teacher AS T ON T.teacherID = C.teacherID" :
                "SELECT C.Name AS Course, CONCAT(T.fname, ' ', T.lname) AS Teacher_Name, T.Email_ID AS Teacher_Email " +
                "FROM Courses AS C " +
                "INNER JOIN Teacher AS T ON T.teacherID = C.teacherID " +
                "WHERE C.Subject_ID = (SELECT Subject_ID FROM Subjects WHERE Name = '" + subjectName + "')";

            rs = c1.s.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            int rowCount = 0;
            while (rs.next()) rowCount++;

            columnNames = new String[columnCount];
            int CIndex = 1;
            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = rsmd.getColumnLabel(CIndex++);
            }

            data = new String[rowCount][columnCount];
            int row = 0;
            rs.beforeFirst();
            while (rs.next()) {
                int col = 0;
                for (int c = 1; c <= columnCount; c++) {
                    data[row][col++] = rs.getString(c);
                }
                row++;
            }

            // Update table model
            model.setRowCount(0); // Clear previous rows
            model.setColumnCount(0); // Clear previous columns
            for (String columnName : columnNames) {
                model.addColumn(columnName);
            }
            for (String[] rowData : data) {
                model.addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                c1.Close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == filterBySubjectCb) {
            String selectedSubject = filterBySubjectCb.getSelectedItem().toString();
            this.getCourses(selectedSubject.equals("All Subjects") ? "" : selectedSubject);
        }
    }

    public static void main(String[] args) {
        new ViewCourses();
    }
}
