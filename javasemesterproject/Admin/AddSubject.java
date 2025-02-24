package javasemesterproject.Admin;

import com.formdev.flatlaf.FlatLightLaf;
import javasemesterproject.DBConnection;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class AddSubject extends JFrame implements ActionListener {
    private JLabel titleLabel, subjectLabel, imageLabel;
    private JButton addButton;
    private JTextField subjectNameField;

    public AddSubject() {
        super("Add Subject");
        setupFlatLaf();
        initializeComponents();
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
        titleLabel = new JLabel("Add Subject", SwingConstants.CENTER);
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
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/subject.png"));
        
        imageLabel.setIcon(icon); // Provide the path to your image
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        middlePanel.add(imageLabel, gbc);

        // Subject Label
        subjectLabel = new JLabel("Subject Name:");
        subjectLabel.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        middlePanel.add(subjectLabel, gbc);

        // Subject Name Field
        subjectNameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        middlePanel.add(subjectNameField, gbc);

        add(middlePanel, BorderLayout.CENTER);

        // Add Button
        addButton = new JButton("Add");
        addButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        addButton.setForeground(Color.WHITE);
        addButton.setPreferredSize(new Dimension(30, 30));
        addButton.setBackground(new Color(92, 124, 250));
        addButton.setFocusPainted(false);
        addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addButton.setOpaque(true);
        addButton.addActionListener(this);
        add(addButton, BorderLayout.SOUTH);

        // Frame Settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(425, 325);
        setLocationRelativeTo(null); // Center the frame
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addSubject();
        }
    }

    /**
     * Adds a subject to the database.
     */
    private void addSubject() {
        String subjectName = subjectNameField.getText().trim();

        // Validation
        if (subjectName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Subject name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DBConnection dbConnection = new DBConnection();
        try {
            String query = "INSERT INTO Subjects (Name, Adminid) VALUES (?, ?)";
            PreparedStatement preparedStatement = dbConnection.c.prepareStatement(query);
            preparedStatement.setString(1, subjectName);
            preparedStatement.setInt(2, AdminLogin2.currentAdminID);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Subject added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                subjectNameField.setText(""); // Clear the input field
            } else {
                JOptionPane.showMessageDialog(this, "Subject already exists!", "Error", JOptionPane.WARNING_MESSAGE);
            }
            preparedStatement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while adding the subject.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            dbConnection.Close();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddSubject::new);
    }
}
