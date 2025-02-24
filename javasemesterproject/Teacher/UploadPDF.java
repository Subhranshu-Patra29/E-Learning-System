package javasemesterproject.Teacher;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import javasemesterproject.DBConnection;

public class UploadPDF extends JFrame implements ActionListener {

    private JComboBox<String> coursesCb;
    private JTextField pdfPath;
    private JTextArea documentDescription;
    private JButton uploadBtn, chooseFileBtn;
    private String[] coursesData;

    public UploadPDF() {
        // Apply FlatLaf theme
        FlatLightLaf.setup();

        setTitle("Upload PDF");
        setLayout(new BorderLayout(10, 10));
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Title
        JLabel title = new JLabel("Upload PDF", JLabel.CENTER);
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
        coursesCbLbl.setBounds(35, 20, 150, 30);
        centerPanel.add(coursesCbLbl);

        getCourses();
        coursesCb = new JComboBox<>(coursesData);
        coursesCb.setBounds(200, 20, 200, 30);
        centerPanel.add(coursesCb);

        // PDF File Chooser
        JLabel pdfPathLbl = new JLabel("Choose PDF:");
        pdfPathLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        pdfPathLbl.setBounds(35, 70, 150, 30);
        centerPanel.add(pdfPathLbl);

        pdfPath = new JTextField();
        pdfPath.setBounds(200, 70, 350, 30);
        pdfPath.setEditable(false);
        centerPanel.add(pdfPath);

        chooseFileBtn = new JButton("Browse");
        chooseFileBtn.setBounds(570, 70, 80, 30);
        chooseFileBtn.addActionListener(this);
        centerPanel.add(chooseFileBtn);

        // Document Description
        JLabel documentDescriptionLbl = new JLabel("Document Description:");
        documentDescriptionLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        documentDescriptionLbl.setBounds(35, 120, 200, 30);
        centerPanel.add(documentDescriptionLbl);

        documentDescription = new JTextArea();
        documentDescription.setLineWrap(true);
        documentDescription.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(documentDescription);
        scroll.setBounds(200, 120, 450, 150);
        centerPanel.add(scroll);

        add(centerPanel, BorderLayout.CENTER);

        // Upload Button
        uploadBtn = new JButton("Upload PDF");
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
        if (e.getSource() == chooseFileBtn) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PDF Documents", "pdf"));
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                pdfPath.setText(selectedFile.getAbsolutePath());
            }
        } else if (e.getSource() == uploadBtn) {
            String course = (String) coursesCb.getSelectedItem();
            String filePath = pdfPath.getText();
            String description = documentDescription.getText();

            if (filePath.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            File pdfFile = new File(filePath);

            if (!pdfFile.exists() || !pdfFile.canRead()) {
                JOptionPane.showMessageDialog(this, "Invalid file. Please select a valid PDF file!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Read the PDF file into a byte array
                byte[] pdfData = java.nio.file.Files.readAllBytes(pdfFile.toPath());

                // Encode the byte array into a Base64 string
                String encodedFile = java.util.Base64.getEncoder().encodeToString(pdfData);

                // Establish the database connection
                DBConnection c1 = new DBConnection();

                // Construct the SQL query using String.format
                String query = String.format(
                        "INSERT INTO PDFs (Title, Uploaded_File, Upload_Date, course_ID, teacherID) "
                        + "VALUES ('%s', '%s', CURDATE(), (SELECT course_ID FROM Courses WHERE Name = '%s'), %d)",
                        pdfFile.getName(),
                        encodedFile,
                        course,
                        TeacherLogin2.currentTeacherID
                );

                // Execute the query
                int result = c1.s.executeUpdate(query);

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "PDF uploaded successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error uploading PDF!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new UploadPDF();
    }
}
