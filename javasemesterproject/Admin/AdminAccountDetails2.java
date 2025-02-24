package javasemesterproject.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javasemesterproject.DBConnection;

public class AdminAccountDetails2 extends JFrame implements ActionListener {
    JPanel contentPane;
    JTextField firstname, lastname, email, username, passwordField, gender;
    JButton okButton;

    public AdminAccountDetails2() {
        super("Admin Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Set modern theme (FlatLaf)
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        JLabel titleLabel = new JLabel("Admin Details");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(new Color(50, 50, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 20, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(titleLabel, gbc);

        String firstName = null, lastName = null, emailId = null, userName = null, password = null, userGender = null;
        try {
            DBConnection c1 = new DBConnection();
            String q = "SELECT * FROM Admin WHERE Adminid = '" + AdminLogin2.currentAdminID + "'";
            ResultSet rs = c1.s.executeQuery(q);
            if (rs.next()) {
                firstName = rs.getString("fname");
                lastName = rs.getString("lname");
                emailId = rs.getString("Email_ID");
                userName = rs.getString("username");
                password = rs.getString("password");
                userGender = rs.getString("Gender");
            } else {
                JOptionPane.showMessageDialog(null, "Not Found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Labels and fields
        createLabelAndField("First Name", firstName, 1, gbc);
        createLabelAndField("Last Name", lastName, 2, gbc);
        createLabelAndField("Email", emailId, 3, gbc);
        createLabelAndField("Username", userName, 4, gbc);
        createLabelAndField("Password", password, 5, gbc);
        createLabelAndField("Gender", userGender, 6, gbc);

        // OK Button
        okButton = new JButton("OK");
        okButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        okButton.setBackground(new Color(50, 150, 250));
        okButton.setForeground(Color.WHITE);
        okButton.setFocusPainted(false);
        okButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        okButton.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(okButton, gbc);

        setVisible(true);
    }

    private void createLabelAndField(String labelText, String fieldText, int yPos, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText + ":");
        label.setFont(new Font("SansSerif", Font.PLAIN, 18));
        label.setForeground(new Color(80, 80, 80));

        JTextField field = new JTextField();
        field.setFont(new Font("SansSerif", Font.PLAIN, 16));
        field.setText(fieldText);
        field.setEditable(false);
        field.setBackground(new Color(245, 245, 245));
        field.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        gbc.gridx = 0;
        gbc.gridy = yPos;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = yPos;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(field, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == okButton) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new AdminAccountDetails2();
    }
}

