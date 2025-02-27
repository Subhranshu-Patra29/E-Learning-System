//package javasemesterproject.Teacher;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import static java.awt.Component.CENTER_ALIGNMENT;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.Frame;
//import java.awt.Graphics2D;
//import java.awt.HeadlessException;
//import java.awt.Image;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowEvent;
//import java.awt.event.WindowStateListener;
//import java.awt.geom.Ellipse2D;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javasemesterproject.Admin.Admin;
//import javasemesterproject.DBConnection;
//import javasemesterproject.Main;
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//public class Teacher extends JFrame implements ActionListener, WindowStateListener{
//    JPanel sidePanel, rightPanel, buttonsPanel;
//    JLabel usericon, lblUsername;
//    JButton viewProfileBtn, logoutBtn;
//    JPanel panel;
//    JButton b1,b2, b3, b4, b5, b6, b7;
//    BufferedImage bufferedImage = null;
//    public Teacher(){
//        super("Teacher Module");
//        setLayout(new BorderLayout());
//        setSize(1280,720);
//        setLocation(35,30);
//        
//        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/systemIcon.png"));
//        setIconImage(icon.getImage());
//      
//        sidePanel = new JPanel();
//        sidePanel.setLayout(null);
//        sidePanel.setBackground(new Color(0, 26, 195));
//        Dimension sidePanelSize = new Dimension(180, 720);
//        sidePanel.setPreferredSize(sidePanelSize);
//        add(sidePanel, BorderLayout.WEST);
//        
//        //
//        String firstName = null,lastName = null, gender = "";
//        byte[] bytImage = null;
//        try{
//            DBConnection c1 = new DBConnection();
//            PreparedStatement ps = c1.c.prepareStatement("select * from Teacher where teacherID = '"+ TeacherLogin.currentTeacherID +"'");
//            ResultSet rs = ps.executeQuery();
//            
//            if(rs.next()){
//                firstName = rs.getString("fname");
//                lastName = rs.getString("lname");
//                gender = rs.getString("Gender");
//                //get image as byte
//                bytImage = rs.getBytes("picture");
//            }else{
//                JOptionPane.showMessageDialog(null, "Not Found");
//            }
//        }catch(HeadlessException | NumberFormatException | SQLException e){
//            e.printStackTrace();
//        }
//        
//        //sidePanel Code
//        InputStream is = new ByteArrayInputStream(bytImage);
//        try {
//            bufferedImage = ImageIO.read(is);
//        } catch (IOException ex) {
//            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        usericon = new JLabel(resizeImage(bufferedImage));
//        usericon.setBounds(38, 5, 96, 96);
//        usericon.setHorizontalAlignment((int) CENTER_ALIGNMENT);
//        sidePanel.add(usericon);
//
//        lblUsername = new JLabel();
//        lblUsername.setFont(new Font(Font.SERIF,Font.BOLD, 20));
//        lblUsername.setForeground(new Color(45,255,3));
//        lblUsername.setBounds(20, 98, 150, 40);
//        lblUsername.setHorizontalAlignment((int) CENTER_ALIGNMENT);
//        lblUsername.setText(firstName + " " + lastName);
//        sidePanel.add(lblUsername);
//        
//        viewProfileBtn = new JButton("View Profile");
//        viewProfileBtn.setFont(new Font(Font.SERIF,Font.BOLD, 13));
//        viewProfileBtn.setBackground(Color.BLACK);
//        viewProfileBtn.setForeground(Color.WHITE);
//        viewProfileBtn.setBounds(30, 150, 120, 28);
//        viewProfileBtn.setHorizontalAlignment((int) CENTER_ALIGNMENT);
//        viewProfileBtn.addActionListener((ActionListener) this);
//        sidePanel.add(viewProfileBtn);
//        
//        
//        logoutBtn = new JButton("Logout");
//        logoutBtn.setFont(new Font(Font.SERIF,Font.BOLD, 13));
//        logoutBtn.setBackground(Color.BLACK);
//        logoutBtn.setForeground(Color.WHITE);
//        logoutBtn.setBounds(30, 600, 120, 28);
//        logoutBtn.setHorizontalAlignment((int) CENTER_ALIGNMENT);
//        logoutBtn.addActionListener((ActionListener) this);
//        sidePanel.add(logoutBtn);
//        
//        //rightPanel Code
//        rightPanel = new JPanel(new BorderLayout());
//        add(rightPanel, BorderLayout.CENTER);
//        
//        JLabel mainTitle = new JLabel("Teacher Module");
//        mainTitle.setHorizontalAlignment(JLabel.CENTER);
//        mainTitle.setFont(new Font(Font.SERIF,Font.BOLD, 50));
//        mainTitle.setBackground(Color.BLACK);
//        mainTitle.setForeground(Color.WHITE);
//        mainTitle.setOpaque(true);
//        rightPanel.add(mainTitle, BorderLayout.NORTH);
//        
//        buttonsPanel = new JPanel();
//        buttonsPanel.setLayout(null);
//        rightPanel.add(buttonsPanel, BorderLayout.CENTER);
//        
//        JLabel buttonSectionTitle = new JLabel("My Account");
//        buttonSectionTitle.setFont(new Font(Font.SERIF,Font.BOLD, 25));
//        buttonSectionTitle.setForeground(Color.BLACK);
//        buttonSectionTitle.setHorizontalAlignment(JLabel.LEFT);
//        buttonSectionTitle.setBounds(6,6,150,50);
//        buttonsPanel.add(buttonSectionTitle);
//       
//        // 64 is the size of Button Icon https://icons8.com/
//        
//        b1 = new JButton("Manage Account");
//        b1.setIcon(new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/ManageAccount.png")));
//        b1.setBounds(250,60,130,90);
//        b1.setHorizontalTextPosition(JButton.CENTER);
//        b1.setVerticalTextPosition(JButton.BOTTOM);
//        b1.addActionListener((ActionListener) this);
//        buttonsPanel.add(b1);
//        
//        b2 = new JButton("Delete Account");
//        b2.setIcon(new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/DeleteAccount.png")));
//        b2.setBounds(410,60,130,90);
//        b2.setHorizontalTextPosition(JButton.CENTER);
//        b2.setVerticalTextPosition(JButton.BOTTOM);
//        b2.addActionListener((ActionListener) this);
//        buttonsPanel.add(b2);
//        
//        // gap to 160 Horizontally
//        b3 = new JButton("View Account");
//        b3.setIcon(new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/viewAccount.png")));
//        b3.setBounds(570,60,130,90);
//        b3.setHorizontalTextPosition(JButton.CENTER);
//        b3.setVerticalTextPosition(JButton.BOTTOM);
//        b3.addActionListener((ActionListener) this);
//        buttonsPanel.add(b3);
//        
//        
//        // second Row of Buttons
//        JLabel buttonSectionTitle2 = new JLabel("Teacher Operations");
//        buttonSectionTitle2.setFont(new Font(Font.SERIF,Font.BOLD, 25));
//        buttonSectionTitle2.setForeground(Color.BLACK);
//        buttonSectionTitle2.setHorizontalAlignment(JLabel.LEFT);
//        buttonSectionTitle2.setBounds(6,150,225,50);
//        buttonsPanel.add(buttonSectionTitle2);
//       
//        // 64 is the size of Button Icon https://icons8.com/
//        b4 = new JButton("Add Course");
//        b4.setIcon(new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/addSubject.png")));
//        b4.setBounds(250,210,130,90);
//        b4.setHorizontalTextPosition(JButton.CENTER);
//        b4.setVerticalTextPosition(JButton.BOTTOM);
//        b4.addActionListener((ActionListener) this);
//        buttonsPanel.add(b4);
//        
//        b5 = new JButton("View My Students");
//        b5.setIcon(new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/viewStudents.png")));
//        b5.setBounds(410,210,145,90);
//        b5.setHorizontalTextPosition(JButton.CENTER);
//        b5.setVerticalTextPosition(JButton.BOTTOM);
//        b5.addActionListener((ActionListener) this);
//        buttonsPanel.add(b5);
//        
//        b6 = new JButton("View My Courses");
//        b6.setIcon(new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/myCourses.png")));
//        b6.setBounds(570,210,140,90);
//        b6.setHorizontalTextPosition(JButton.CENTER);
//        b6.setVerticalTextPosition(JButton.BOTTOM);
//        b6.addActionListener((ActionListener) this);
//        buttonsPanel.add(b6);
//        
//        b7 = new JButton("Update Courses");
//        b7.setIcon(new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/updateCourse.png")));
//        b7.setBounds(250,320,130,90);
//        b7.setHorizontalTextPosition(JButton.CENTER);
//        b7.setVerticalTextPosition(JButton.BOTTOM);
//        b7.addActionListener((ActionListener) this);
//        buttonsPanel.add(b7);
//
//        setResizable(true);
//        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//        setVisible(true);
//    }
//        // This code use to resize image to fit lable
//    public ImageIcon resizeImage(BufferedImage bufferedImage){
//        int width = bufferedImage.getWidth();
//        BufferedImage circleBuffer = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2 = circleBuffer.createGraphics();
//        g2.setClip(new Ellipse2D.Float(0, 0, width, width));
//        g2.drawImage(bufferedImage, 0, 0, width, width, null);
//        ImageIcon icon = new ImageIcon(circleBuffer);
//        Image i2 = icon.getImage().getScaledInstance(96 ,96 ,Image.SCALE_DEFAULT);
//        ImageIcon i3 =  new ImageIcon(i2);
//        return i3;
//    }
//    @Override
//    public void actionPerformed(ActionEvent ae){
//        if(ae.getSource() == b1){
//            new TeacherManageAccount();
//        }
//        else if(ae.getSource() == b2){
//            TeacherDeleteAccount delete = new TeacherDeleteAccount();
//            if(delete.input == 0){
//                dispose();
//                new Main();
//            }
//        }
//        else if(ae.getSource() == b3){
//            new TeacherAccountDetails();
//        }
//        else if(ae.getSource() == b4){
//            new AddCourse();
//        }
//        else if(ae.getSource() == b5){
//            new ViewMyStudents();
//        }
//        else if(ae.getSource() == b6){
//            new ViewMyCourses();
//        }
//        else if(ae.getSource() == b7){
//            new UpdateCourses();
//        }
//        else if(ae.getSource() == viewProfileBtn){
//            JOptionPane.showMessageDialog(this, new ImageIcon(bufferedImage), "Profile Picture", JOptionPane.PLAIN_MESSAGE);
//        }
//        else if(ae.getSource() == logoutBtn){
//            java.util.Date dt = new java.util.Date();
//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            String last_Login = dateFormat.format(dt);
//            try{
//                DBConnection c1 = new DBConnection();
//
//                String q = "update Teacher "
//                        + "Set Last_Login = '"+ last_Login +"'"
//                        + "Where teacherID = '"+ TeacherLogin.currentTeacherID +"'";
//                int x = c1.s.executeUpdate(q);
//                if(x == 0){
//                JOptionPane.showMessageDialog(null, "Got an Error");
//                }else{
//                    JOptionPane.showMessageDialog(null, "Loggin Out...");
//                     new Main();
//                     dispose();
//                }
//            }catch(Exception ex){
//                ex.printStackTrace();
//            }
//        }
//    }
//    @Override
//    public void windowStateChanged(WindowEvent e) {
//        // normal state
//        if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.NORMAL){
//           logoutBtn.setLocation(30, 600);
//        }
//        // maximized
//        else if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH){
//            logoutBtn.setLocation(30, this.getHeight() - 120);
//        }
//    }
//}


package javasemesterproject.Teacher;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javasemesterproject.DBConnection;
import javasemesterproject.Main;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Teacher extends JFrame implements ActionListener, WindowStateListener {
    private JPanel sidePanel, rightPanel, buttonsPanel;
    private JButton logoutBtn, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private JLabel userIcon, lblUsername;
    private BufferedImage bufferedImage = null;

    public Teacher() {
        super("Teacher Module");

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
            PreparedStatement ps = c1.c.prepareStatement("select * from Teacher where teacherID = '" + TeacherLogin2.currentTeacherID + "'");
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

        JLabel mainTitle = new JLabel("Teacher Module", SwingConstants.CENTER);
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
        createButton("Add Course Course", "icons/addSubject.png", 250, 210, buttonsPanel, b4 = new JButton());
        createButton("View My Students", "icons/viewStudents.png", 410, 210, buttonsPanel, b5 = new JButton());
        createButton("View My Courses", "icons/myCourses.png", 570, 210, buttonsPanel, b6 = new JButton());
        createButton("UpdateCourses", "icons/updateCourse.png", 250, 360, buttonsPanel, b7 = new JButton());
        createButton("Upload Video", "icons/uploadVideo.png", 410, 360, buttonsPanel, b8 = new JButton());
        createButton("Upload PDFs", "icons/uploadPDF.png", 570, 360, buttonsPanel, b9 = new JButton());
        
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
        if (ae.getSource() == b1) new TeacherManageAccount();
        else if (ae.getSource() == b2) new TeacherDeleteAccount();
        else if (ae.getSource() == b3) new TeacherAccountDetails();
        else if (ae.getSource() == b4) new AddCourse();
        else if (ae.getSource() == b5) new ViewMyStudents();
        else if (ae.getSource() == b6) new ViewMyCourses();
        else if (ae.getSource() == b7) new UpdateCourses();
        else if (ae.getSource() == b8) new UploadVideo();
        else if (ae.getSource() == b9) new UploadPDF();
        else if (ae.getSource() == logoutBtn) {
            java.util.Date dt = new java.util.Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String last_Login = dateFormat.format(dt);
            try{
                DBConnection c1 = new DBConnection();

                String q = "update Teacher "
                        + "Set Last_Login = '"+ last_Login +"'"
                        + "Where teacherID = '"+ TeacherLogin2.currentTeacherID +"'";

                int x = c1.s.executeUpdate(q);
                if(x == 0){
                JOptionPane.showMessageDialog(null, "Got an Error");
                }else{
                    JOptionPane.showMessageDialog(null, "Logging Out...");
                     new Main();
                     dispose();
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
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

