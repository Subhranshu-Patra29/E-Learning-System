package javasemesterproject.Teacher;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javasemesterproject.DBConnection;

public class UploadVideo extends JFrame implements ActionListener {

    private JComboBox<String> coursesCb;
    private JTextField videoTitle, videoLink;
    private JTextArea videoDescription;
    private JButton uploadBtn;
    private String[] coursesData;

    public UploadVideo() {
        // Apply FlatLaf theme
        FlatLightLaf.setup();

        setTitle("Upload Video");
        setLayout(new BorderLayout(10, 10));
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Title
        JLabel title = new JLabel("Upload Video", JLabel.CENTER);
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
        coursesCb.setBounds(200, 20, 200, 30);
        centerPanel.add(coursesCb);

        // Video Title
        JLabel videoTitleLbl = new JLabel("Video Title:");
        videoTitleLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        videoTitleLbl.setBounds(50, 70, 150, 30);
        centerPanel.add(videoTitleLbl);

        videoTitle = new JTextField();
        videoTitle.setBounds(200, 70, 450, 30);
        centerPanel.add(videoTitle);

        // Video Link
        JLabel videoLinkLbl = new JLabel("Video Link:");
        videoLinkLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        videoLinkLbl.setBounds(50, 120, 150, 30);
        centerPanel.add(videoLinkLbl);

        videoLink = new JTextField();
        videoLink.setBounds(200, 120, 450, 30);
        centerPanel.add(videoLink);

        // Video Description
        JLabel videoDescriptionLbl = new JLabel("Video Description:");
        videoDescriptionLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        videoDescriptionLbl.setBounds(50, 170, 150, 30);
        centerPanel.add(videoDescriptionLbl);

        videoDescription = new JTextArea();
        videoDescription.setLineWrap(true);
        videoDescription.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(videoDescription);
        scroll.setBounds(200, 170, 450, 150);
        centerPanel.add(scroll);

        add(centerPanel, BorderLayout.CENTER);

        // Upload Button
        uploadBtn = new JButton("Upload Video");
        uploadBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        uploadBtn.setPreferredSize(new Dimension(30, 30));
        uploadBtn.setForeground(Color.WHITE);
        uploadBtn.setBackground(new Color(92, 124, 250));
        uploadBtn.setFocusPainted(false);
        uploadBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        uploadBtn.setOpaque(true);
        uploadBtn.addActionListener(this);
        add(uploadBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void getCourses() {
        try {
            DBConnection c1 = new DBConnection();
            String query = String.format(
                    "SELECT Name FROM Courses WHERE teacherID = %d", TeacherLogin2.currentTeacherID);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == uploadBtn) {
            String course = (String) coursesCb.getSelectedItem();
            String title = videoTitle.getText();
            String link = videoLink.getText();
            String description = videoDescription.getText();

            if (title.isEmpty() || link.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Establish the database connection
                DBConnection c1 = new DBConnection();

                // Construct the SQL query using String.format
                String query = String.format(
                        "INSERT INTO Videos (Title, Link, Video_Description, Upload_Date, course_ID, teacherID) "
                        + "VALUES ('%s', '%s', '%s', CURDATE(), (SELECT course_ID FROM Courses WHERE Name = '%s'), %d)", 
                        title, link, description, course, TeacherLogin2.currentTeacherID);

                // Execute the query
                int result = c1.s.executeUpdate(query);

                // Handle the result
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Video uploaded successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error uploading video!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage());
            }

        }
    }

    public static void main(String[] args) {
        new UploadVideo();
    }
}
