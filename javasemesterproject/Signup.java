package javasemesterproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javasemesterproject.Student.StudentSignup;
import javasemesterproject.Teacher.TeacherSignup;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Signup extends JFrame implements ActionListener{
    JPanel panel;
    JButton studentSignUpButton, teacherSignUpButton;
    JLabel title;
    ImageIcon backgroundImg;
    
    public Signup(){
        super("Sign Up");
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

        
        backgroundImg = new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/background3.png"));
        panel = new Signup.BackgroundPanel(backgroundImg); // Custom panel with background image
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        
        // Load icons for buttons
        ImageIcon studentIcon = new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/student.png"));       
        ImageIcon teacherIcon = new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/teacher.png"));       
        
        // Create and configure buttons
        studentSignUpButton = createButton("Student Sign Up", studentIcon);
        teacherSignUpButton = createButton("Teacher Sign Up", teacherIcon);

        panel.add(studentSignUpButton);
        panel.add(teacherSignUpButton);
        
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
        if(ae.getSource() == studentSignUpButton){
            setVisible(false);
            new StudentSignup();
        }
        else if(ae.getSource() == teacherSignUpButton){
            setVisible(false);
            new TeacherSignup();
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
        new Signup();
    }
}
