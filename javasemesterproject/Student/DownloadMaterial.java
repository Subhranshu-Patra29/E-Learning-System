package javasemesterproject.Student;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import javasemesterproject.DBConnection;
import javasemesterproject.utility.PDFViewer;

public class DownloadMaterial extends JFrame implements ActionListener {

    private JComboBox<String> coursesCb;
    private JList<String> pdfList;
    private DefaultListModel<String> pdfListModel;
    private JButton openBtn;
    private String[] coursesData;
    private ArrayList<Integer> pdfIds; // To map PDF titles to their IDs

    public DownloadMaterial() {
        // Apply FlatLaf theme
        FlatLightLaf.setup();

        setTitle("Download Materials");
        setLayout(new BorderLayout(10, 10));
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Title
        JLabel title = new JLabel("Download Materials", JLabel.CENTER);
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
        coursesCb.setBounds(200, 20, 300, 30);
        coursesCb.addActionListener(this);
        centerPanel.add(coursesCb);

        // PDF List
        JLabel pdfListLbl = new JLabel("Available Documents:");
        pdfListLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        pdfListLbl.setBounds(35, 70, 150, 30);
        centerPanel.add(pdfListLbl);

        pdfListModel = new DefaultListModel<>();
        pdfList = new JList<>(pdfListModel);
        JScrollPane scroll = new JScrollPane(pdfList);
        scroll.setBounds(200, 70, 450, 300);
        centerPanel.add(scroll);

        add(centerPanel, BorderLayout.CENTER);

        // Open Button
        openBtn = new JButton("Open this document");
        openBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        openBtn.setPreferredSize(new Dimension(30, 30));
        openBtn.setForeground(Color.WHITE);
        openBtn.setBackground(new Color(92, 124, 250));
        openBtn.setFocusPainted(false);
        openBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        openBtn.setOpaque(true);
        openBtn.addActionListener(this);
        add(openBtn, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void getCourses() {
        try {
            DBConnection c1 = new DBConnection();
            String query = "SELECT Name FROM Courses";
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

    private void loadPDFs(String courseName) {
        try {
            DBConnection c1 = new DBConnection();
            String query = String.format(
                    "SELECT PDF_ID, Title FROM PDFs WHERE course_ID = "
                    + "(SELECT course_ID FROM Courses WHERE Name = '%s')",
                    courseName);
            ResultSet rs = c1.s.executeQuery(query);

            pdfListModel.clear();
            pdfIds = new ArrayList<>();
            while (rs.next()) {
                pdfIds.add(rs.getInt("PDF_ID"));
                pdfListModel.addElement(rs.getString("Title"));
            }

            if (pdfListModel.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No PDFs available for this course.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openPDF(int pdfId) {
        try {
            DBConnection c1 = new DBConnection();
            String query = String.format("SELECT Uploaded_File FROM PDFs WHERE PDF_ID = %d", pdfId);
            ResultSet rs = c1.s.executeQuery(query);

            if (rs.next()) {
                String base64EncodedData = rs.getString("Uploaded_File");

                // Decode the Base64-encoded data
                byte[] pdfData = Base64.getDecoder().decode(base64EncodedData);

                // Create a temporary PDF file
                File tempFile = File.createTempFile("uploaded_pdf", ".pdf");
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    fos.write(pdfData);
                }

                // Open the PDF file with the system's default PDF viewer
                
                // Desktop.getDesktop().open(tempFile);
                
                new PDFViewer(tempFile.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(this, "PDF not found in the database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error opening PDF.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == coursesCb) {
            String selectedCourse = (String) coursesCb.getSelectedItem();
            loadPDFs(selectedCourse);
        } else if (e.getSource() == openBtn) {
            int selectedIndex = pdfList.getSelectedIndex();
            if (selectedIndex != -1) {
                int pdfId = pdfIds.get(selectedIndex);
                openPDF(pdfId);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a PDF to open.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new DownloadMaterial();
    }
}
