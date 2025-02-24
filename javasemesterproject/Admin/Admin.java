package javasemesterproject.Admin;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javasemesterproject.DBConnection;
import javasemesterproject.Main;

public class Admin extends JFrame implements ActionListener, WindowStateListener {
    private JPanel sidePanel, rightPanel, buttonsPanel;
    private JButton manageAccount, logoutBtn, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private JLabel userIcon, lblUsername;
    private BufferedImage bufferedImage = null;

    public Admin() {
        super("Admin Module");

        // Apply FlatLaf Theme
        FlatLightLaf.setup();

        // Frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/systemIcon.png")).getImage());

        // Side Panel
        sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBackground(new Color(245, 245, 245)); // Light gray
        sidePanel.setPreferredSize(new Dimension(200, getHeight()));
        add(sidePanel, BorderLayout.WEST);

        // Fetch user details from database
        String firstName = null, lastName = null;
        byte[] bytImage = null;
        try {
            DBConnection c1 = new DBConnection();
            PreparedStatement ps = c1.c.prepareStatement("select * from Admin where Adminid = '" + AdminLogin2.currentAdminID + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                firstName = rs.getString("fname");
                lastName = rs.getString("lname");
                bytImage = rs.getBytes("picture");
            } else {
                JOptionPane.showMessageDialog(null, "Not Found");
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Display profile picture
        if (bytImage != null) {
            try (InputStream is = new ByteArrayInputStream(bytImage)) {
                bufferedImage = ImageIO.read(is);
                userIcon = new JLabel(resizeImage(bufferedImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            userIcon = new JLabel(new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/uploadPicIcon.png")));
        }
        userIcon.setBounds(52, 50, 96, 96);
        sidePanel.add(userIcon);

        // Display username
        lblUsername = new JLabel(firstName != null ? firstName : "Admin");
        lblUsername.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblUsername.setForeground(new Color(51, 102, 255)); // Blue
        lblUsername.setBounds(20, 160, 160, 30);
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        sidePanel.add(lblUsername);

        // Logout Button
        logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        logoutBtn.setBounds(40, 550, 120, 40);
        logoutBtn.addActionListener(this);
        sidePanel.add(logoutBtn);

        // Right Panel
        rightPanel = new JPanel(new BorderLayout());
        add(rightPanel, BorderLayout.CENTER);

        JLabel mainTitle = new JLabel("Admin Module", SwingConstants.CENTER);
        mainTitle.setFont(new Font("SansSerif", Font.BOLD, 36));
        mainTitle.setOpaque(true);
        mainTitle.setBackground(new Color(51, 102, 255));
        mainTitle.setForeground(Color.WHITE);
        rightPanel.add(mainTitle, BorderLayout.NORTH);

        // Buttons Panel
        buttonsPanel = new JPanel(null);
        rightPanel.add(buttonsPanel, BorderLayout.CENTER);

        // Add buttons
        createButton("Manage Account", "icons/ManageAccount.png", 250, 60, buttonsPanel, b1 = new JButton());
        createButton("Delete Account", "icons/DeleteAccount.png", 410, 60, buttonsPanel, b2 = new JButton());
        createButton("View Account", "icons/viewAccount.png", 570, 60, buttonsPanel, b3 = new JButton());
        createButton("Add Subject", "icons/addSubject.png", 250, 210, buttonsPanel, b4 = new JButton());
        createButton("Delete Subject", "icons/deleteSubject.png", 410, 210, buttonsPanel, b5 = new JButton());
        createButton("View Students", "icons/viewStudents.png", 570, 210, buttonsPanel, b6 = new JButton());
        createButton("View Teachers", "icons/viewTeachers.png", 250, 360, buttonsPanel, b7 = new JButton());
        createButton("View Courses", "icons/viewCourses.png", 410, 360, buttonsPanel, b8 = new JButton());
        createButton("Add New Admin", "icons/addNewAdmin.png", 570, 360, buttonsPanel, b9 = new JButton());

        setVisible(true);
    }

    private void createButton(String text, String iconPath, int x, int y, JPanel panel, JButton button) {
        button.setText(text);
        button.setIcon(new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/" + iconPath)));
        button.setBounds(x, y, 130, 90);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.addActionListener(this);
        panel.add(button);
    }

    private ImageIcon resizeImage(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        BufferedImage circleBuffer = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleBuffer.createGraphics();
        g2.setClip(new Ellipse2D.Float(0, 0, width, width));
        g2.drawImage(bufferedImage, 0, 0, width, width, null);
        g2.dispose();
        ImageIcon icon = new ImageIcon(circleBuffer);
        return new ImageIcon(icon.getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) new AdminManageAccount();
        else if (ae.getSource() == b2) new AdminDeleteAccount();
        else if (ae.getSource() == b3) new AdminAccountDetails2();
        else if (ae.getSource() == b4) new AddSubject();
        else if (ae.getSource() == b5) new DeleteSubject();
        else if (ae.getSource() == b6) new ViewStudents();
        else if (ae.getSource() == b7) new ViewTeachers();
        else if (ae.getSource() == b8) new ViewCourses();
        else if (ae.getSource() == b9) new AdminSignup();
        else if (ae.getSource() == logoutBtn) {
            JOptionPane.showMessageDialog(this, "Logging out...");
            new Main();
            dispose();
        }
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        // normal state
        if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.NORMAL){
           logoutBtn.setLocation(30, 600);
        }
        // maximized
        else if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH){
            logoutBtn.setLocation(30, this.getHeight() - 120);
        }
    }
}
