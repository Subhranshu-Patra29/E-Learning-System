package javasemesterproject.Admin;

import com.formdev.flatlaf.FlatLightLaf;
import javasemesterproject.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeleteSubject extends JFrame implements ActionListener {
    private JLabel titleLabel, subjectLabel, imageLabel;
    private JComboBox<String> subjectsComboBox;
    private JButton deleteButton;

    public DeleteSubject() {
        super("Delete Subject");
        setupFlatLaf();
        initializeComponents();
        populateSubjects();
    }

    /**
     * Sets up the FlatLaf look and feel.
     */
    private void setupFlatLaf() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("Failed to initialize FlatLaf");
        }
    }

    /**
     * Initializes UI components and layout.
     */
    private void initializeComponents() {
        setLayout(new BorderLayout(10, 10));

        // Title Label
        titleLabel = new JLabel("Delete Subject", SwingConstants.CENTER);
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(51, 102, 255));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        // Middle Panel
        JPanel middlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
           
        // Image Label
        imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/remove_subject.png"));
        
        imageLabel.setIcon(icon); // Provide the path to your image
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        middlePanel.add(imageLabel, gbc);
        
        subjectLabel = new JLabel("     Select Subject:");
        subjectLabel.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        middlePanel.add(subjectLabel, gbc);

        subjectsComboBox = new JComboBox<>();
        subjectsComboBox.setPreferredSize(new Dimension(150, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        middlePanel.add(subjectsComboBox, gbc);

        add(middlePanel, BorderLayout.CENTER);

        // Add Button
        deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setPreferredSize(new Dimension(30, 30));
        deleteButton.setBackground(new Color(92, 124, 250));
        deleteButton.setFocusPainted(false);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.setOpaque(true);
        deleteButton.addActionListener(this);
        add(deleteButton, BorderLayout.SOUTH);

        // Frame Settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(425, 325);
        setLocationRelativeTo(null); // Center the frame
        setResizable(false);
        setVisible(true);
    }

    /**
     * Populates the subjects dropdown from the database.
     */
    private void populateSubjects() {
        DBConnection dbConnection = null;
        ResultSet resultSet = null;

        try {
            dbConnection = new DBConnection();
            resultSet = dbConnection.s.executeQuery("SELECT Name FROM Subjects");

            while (resultSet.next()) {
                subjectsComboBox.addItem(resultSet.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load subjects.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (dbConnection != null) dbConnection.c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            deleteSelectedSubject();
        }
    }

    /**
     * Deletes the selected subject from the database.
     */
    private void deleteSelectedSubject() {
        String selectedSubject = (String) subjectsComboBox.getSelectedItem();

        if (selectedSubject == null) {
            JOptionPane.showMessageDialog(this, "Please select a subject to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(this,
                "Deleting this subject will also delete all associated courses. Do you want to proceed?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirmation == JOptionPane.YES_OPTION) {
            DBConnection dbConnection = null;
            PreparedStatement preparedStatement = null;

            try {
                dbConnection = new DBConnection();
                preparedStatement = dbConnection.c.prepareStatement("DELETE FROM Subjects WHERE Name = ?");
                preparedStatement.setString(1, selectedSubject);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Subject deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    subjectsComboBox.removeItem(selectedSubject); // Remove from dropdown
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete the subject.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred while deleting the subject.", "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    if (preparedStatement != null) preparedStatement.close();
                    if (dbConnection != null) dbConnection.c.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DeleteSubject::new);
    }
}
