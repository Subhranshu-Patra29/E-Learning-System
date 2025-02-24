package javasemesterproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javasemesterproject.Admin.AdminLogin2;
import javasemesterproject.Student.StudentLogin2;
import javasemesterproject.Teacher.TeacherLogin2;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Login extends JFrame implements ActionListener{
     JPanel panel;
     JButton studentLoginButton, teacherLoginButton, adminLoginButton;
     JLabel title;
     ImageIcon backgroundImg;
     
    public Login(){
        super("Login");
        setSize(500, 475);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/systemIcon.png"));
        setIconImage(icon.getImage());

        title = new JLabel("Login Select", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 21));
        title.setBackground(Color.LIGHT_GRAY);
        title.setForeground(Color.BLACK);
        title.setOpaque(true);
        title.setPreferredSize(new Dimension(500, 60)); // Setting a preferred size for title
        add(title, BorderLayout.NORTH);

        
        backgroundImg = new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/background.jpg"));
        panel = new BackgroundPanel(backgroundImg); // Custom panel with background image
        panel.setLayout(new GridBagLayout()); // Use GridBagLayout for flexible positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Load icons for buttons
        ImageIcon studentIcon = new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/student.png"));       
        ImageIcon teacherIcon = new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/teacher.png"));       
        ImageIcon adminIcon = new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/admin.png"));
        
        // Create and configure buttons
        studentLoginButton = createButton("Student Login", studentIcon);
        teacherLoginButton = createButton("Teacher Login", teacherIcon);
        adminLoginButton = createButton("Admin Login", adminIcon);

        // Position components in the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(studentLoginButton, gbc);

        gbc.gridx = 1;
        panel.add(Box.createHorizontalStrut(50), gbc);
        
        gbc.gridx = 2;
        panel.add(teacherLoginButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(adminLoginButton, gbc);

        // Add panel to frame
        add(panel, BorderLayout.CENTER);

        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    private JButton createButton(String text, ImageIcon icon) {
        JButton button = new JButton(text, icon);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false); // Make button transparent
        button.setOpaque(false);            // Remove button background
        button.addActionListener(this);
        return button;
    }
    
     @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == studentLoginButton){
            setVisible(false);
            StudentLogin2 obj = new StudentLogin2();
            obj.setVisible(true);
            obj.setLocationRelativeTo(null);
        }
        else if(ae.getSource() == teacherLoginButton){
            setVisible(false);
            TeacherLogin2 obj = new TeacherLogin2();
            obj.setVisible(true);
            obj.setLocationRelativeTo(null);
        }
        else if(ae.getSource() == adminLoginButton){
            setVisible(false);
            AdminLogin2 obj = new AdminLogin2();
            obj.setVisible(true);
            obj.setLocationRelativeTo(null);
        }
    }
    
    // Custom JPanel to display background image
    class BackgroundPanel extends JPanel {
        private ImageIcon background;

        public BackgroundPanel(ImageIcon background) {
            this.background = background;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    public static void main(String[] args) {
        new Login();
    }
    
}