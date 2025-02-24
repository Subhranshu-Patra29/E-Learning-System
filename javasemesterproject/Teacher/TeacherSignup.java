package javasemesterproject.Teacher;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javasemesterproject.DBConnection;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TeacherSignup extends JFrame implements ActionListener, FocusListener {
    JPanel contentPane;
    JTextField firstname, lastname, email, username;
    JPasswordField passwordField;
    JButton registerButton, uploadPicBtn;
    JRadioButton maleRB, femaleRB;
    ButtonGroup radioBtns;
    JLabel fnameValidation, LnameValidation, emailValidation, userNameValidation, passwordValidation, profilePicLbl;
    FileInputStream fis = null;
    File f = null;

    public TeacherSignup() {
        super("Teacher SignUp");
        // Apply FlatLaf theme
        FlatLightLaf.setup();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Profile Picture
        profilePicLbl = new JLabel();
        profilePicLbl.setBounds(50, 150, 100, 100);
        profilePicLbl.setIcon(new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/uploadPicIcon.png")));
        profilePicLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        contentPane.add(profilePicLbl);

        uploadPicBtn = new JButton("Upload");
        uploadPicBtn.setBounds(65, 260, 75, 25);
        uploadPicBtn.addActionListener(this);
        contentPane.add(uploadPicBtn);

        // First Name
        JLabel lblFirstName = new JLabel("First Name");
        lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFirstName.setBounds(200, 100, 100, 30);
        contentPane.add(lblFirstName);

        fnameValidation = new JLabel();
        fnameValidation.setForeground(Color.RED);
        fnameValidation.setBounds(350, 135, 200, 15);
        contentPane.add(fnameValidation);

        firstname = new JTextField();
        firstname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        firstname.setBounds(350, 100, 200, 30);
        firstname.addFocusListener(this);
        contentPane.add(firstname);

        // Last Name
        JLabel lblLastName = new JLabel("Last Name");
        lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblLastName.setBounds(200, 150, 100, 30);
        contentPane.add(lblLastName);

        LnameValidation = new JLabel();
        LnameValidation.setForeground(Color.RED);
        LnameValidation.setBounds(350, 185, 200, 15);
        contentPane.add(LnameValidation);

        lastname = new JTextField();
        lastname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lastname.setBounds(350, 150, 200, 30);
        lastname.addFocusListener(this);
        contentPane.add(lastname);

        // Email
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmail.setBounds(200, 200, 100, 30);
        contentPane.add(lblEmail);

        emailValidation = new JLabel();
        emailValidation.setForeground(Color.RED);
        emailValidation.setBounds(350, 235, 200, 15);
        contentPane.add(emailValidation);

        email = new JTextField();
        email.setFont(new Font("Tahoma", Font.PLAIN, 14));
        email.setBounds(350, 200, 200, 30);
        email.addFocusListener(this);
        contentPane.add(email);

        // Username
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblUsername.setBounds(200, 250, 100, 30);
        contentPane.add(lblUsername);

        userNameValidation = new JLabel();
        userNameValidation.setForeground(Color.RED);
        userNameValidation.setBounds(350, 285, 200, 15);
        contentPane.add(userNameValidation);

        username = new JTextField();
        username.setFont(new Font("Tahoma", Font.PLAIN, 14));
        username.setBounds(350, 250, 200, 30);
        username.addFocusListener(this);
        contentPane.add(username);

        // Password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPassword.setBounds(200, 300, 100, 30);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        passwordField.setBounds(350, 300, 200, 30);
        passwordField.addFocusListener(this);
        contentPane.add(passwordField);

        // Gender
        JLabel genderLbl = new JLabel("Gender");
        genderLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        genderLbl.setBounds(200, 350, 100, 30);
        contentPane.add(genderLbl);

        maleRB = new JRadioButton("Male");
        maleRB.setFont(new Font("Tahoma", Font.PLAIN, 14));
        maleRB.setBounds(350, 350, 70, 30);
        maleRB.setActionCommand("Male");
        contentPane.add(maleRB);

        femaleRB = new JRadioButton("Female");
        femaleRB.setFont(new Font("Tahoma", Font.PLAIN, 14));
        femaleRB.setBounds(430, 350, 90, 30);
        femaleRB.setActionCommand("Female");
        contentPane.add(femaleRB);

        radioBtns = new ButtonGroup();
        radioBtns.add(maleRB);
        radioBtns.add(femaleRB);

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        registerButton.setBounds(300, 450, 150, 40);
        registerButton.addActionListener(this);
        registerButton.putClientProperty("JButton.buttonType", "roundRect"); // FlatLaf style
        contentPane.add(registerButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == uploadPicBtn){
            String fname = null;
            JFileChooser fchoser = new JFileChooser();
            fchoser.showOpenDialog(null);
            f = fchoser.getSelectedFile();
            fname = f.getAbsolutePath();
            ImageIcon micon=new ImageIcon(fname);
            
            try {
                File image=new File(fname);
                fis = new FileInputStream(image);
                profilePicLbl.setIcon(resizeImage(fname));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else{
            String firstName = firstname.getText();
            String lastName = lastname.getText();
            String emailId = email.getText();
            String userName = username.getText();
            String password = String.valueOf(passwordField.getPassword());
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
            String genderStr = "";
            if(radioBtns.getSelection() != null)
                genderStr = radioBtns.getSelection().getActionCommand();
            String msg = "" + firstName;
            if(fnameValidation.getText().isEmpty() && 
                    LnameValidation.getText().isEmpty() && 
                    emailValidation.getText().isEmpty() && 
                    userNameValidation.getText().isEmpty()){
                if(firstName.isEmpty() || lastName.isEmpty() || emailId.isEmpty() || userName.isEmpty() || password.isEmpty()
                    || genderStr.isEmpty() || this.f == null || this.fis == null){
                JOptionPane.showMessageDialog(null, "Please Fill All Fields !");
                }
                else{
                    DBConnection c1 = new DBConnection();
                    try{
                        PreparedStatement ps = c1.c.prepareStatement("Insert into Teacher (fname, lname, Email_ID, username, password, Registration_Date, Gender, picture) " + "values(?,?,?,?,?,?,?,?)");
                        ps.setString(1, firstName);
                        ps.setString(2, lastName);
                        ps.setString(3, emailId);
                        ps.setString(4, userName);
                        ps.setString(5, password);
                        ps.setDate(6, sqlDate);
                        ps.setString(7, genderStr);
                        ps.setBinaryStream(8,(InputStream)fis,(int)f.length());
                        int x =  ps.executeUpdate();
                        ps.close();
                        if(x == 0){
                            JOptionPane.showMessageDialog(null, "This User already exist");
                        }else{
                            JOptionPane.showMessageDialog(null, "Welcome, "+ msg + " Your account is successfully created."
                                    + "You can Now Log into your Account.");
                            setVisible(false);
                            new TeacherLogin2();
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    finally{
                        c1.Close();
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Please Fill accurate Info !");
            }
        }
    }
    
    // This code use to resize image to fit lable
    public ImageIcon resizeImage(String imagePath){
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            Logger.getLogger(TeacherSignup.class.getName()).log(Level.SEVERE, null, ex);
        }
        int width = bufferedImage.getWidth();
        BufferedImage circleBuffer = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleBuffer.createGraphics();
        g2.setClip(new Ellipse2D.Float(0, 0, width, width));
        g2.drawImage(bufferedImage, 0, 0, width, width, null);
        ImageIcon icon = new ImageIcon(circleBuffer);
        Image i2 = icon.getImage().getScaledInstance(96 ,96 ,Image.SCALE_DEFAULT);
        ImageIcon i3 =  new ImageIcon(i2);
        return i3;
    }

    public static void main(String[] args) {
        new TeacherSignup();
    }

    @Override
    public void focusGained(FocusEvent e) {
         if(e.getSource() == firstname)
            fnameValidation.setText("");
        else if(e.getSource() == lastname)
            LnameValidation.setText("");
        else if(e.getSource() == email)
            emailValidation.setText("");
        else if(e.getSource() == username)
            userNameValidation.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() == firstname){
            String fName = firstname.getText();
            if(fName.isEmpty()){
                fnameValidation.setText("Enter First Name");
            }
            else{
                boolean valid = fName.matches("[A-Z][a-z]*");
                if(!valid)
                    fnameValidation.setText("Invalid First Name");
                else
                    fnameValidation.setText("");
            }
        }
        else if(e.getSource() == lastname){
            String LName = lastname.getText();
            if(LName.isEmpty()){
                LnameValidation.setText("Enter Last Name");
            }
            else{
                boolean valid = LName.matches("[A-Z][a-z]*");
                if(!valid)
                    LnameValidation.setText("Invalid Last Name");
                else
                    LnameValidation.setText("");
            }
        }
        else if(e.getSource() == email){
            String emailTxt = email.getText();
            if(emailTxt.isEmpty()){
                emailValidation.setText("Enter Email");
            }
            else{
                boolean valid = emailTxt.matches("[\\w]+@[\\w]+\\.[a-zA-Z]{2,3}");
                if(!valid)
                    emailValidation.setText("Invalid Email");
                else
                    emailValidation.setText("");
            }
        }
        else if(e.getSource() == username){
            String usernameTxt = username.getText();
            if(usernameTxt.isEmpty()){
                userNameValidation.setText("Enter UserName");
            }
            else{
                boolean valid = usernameTxt.matches("\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,}\\b");
                if(!valid)
                    userNameValidation.setText("Invalid UserName");
                else
                    userNameValidation.setText("");
            }
        }
    }
}