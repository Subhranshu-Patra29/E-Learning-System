package javasemesterproject.Student;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javasemesterproject.DBConnection;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class WatchVideo extends JFrame implements ActionListener {
    
    private JComboBox<String> coursesCb;
    private JList<String> videoList;
    private String link;
    private JTextField videoLink;
    private String desc;
    private JTextArea videoDescription;
    private DefaultListModel<String> videoListModel;
    private JButton watchBtn;
    private String[] coursesData;
    private ArrayList<Integer> videoIds;
    
    public WatchVideo() {
        // Apply FlatLaf theme
        FlatLightLaf.setup();

        setTitle("Watch Course Related Videos");
        setLayout(new BorderLayout(10, 10));
        setSize(750, 525);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Title
        JLabel title = new JLabel("Watch Course Related Videos", JLabel.CENTER);
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

        // Video List
        JLabel videoListLbl = new JLabel("Available Videos:");
        videoListLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        videoListLbl.setBounds(35, 70, 150, 30);
        centerPanel.add(videoListLbl);

        videoListModel = new DefaultListModel<>();
        videoList = new JList<>(videoListModel);
        JScrollPane scroll = new JScrollPane(videoList);
        scroll.setBounds(200, 70, 450, 130);
        centerPanel.add(scroll);

        // Add ListSelectionListener
        videoList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = videoList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        int videoId = videoIds.get(selectedIndex);
                        loadVideoDetails(videoId);
                    }
                }
            }
        });
        
        add(centerPanel, BorderLayout.CENTER);

        // Video Link
        JLabel videoLinkLbl = new JLabel("Video Link:");
        videoLinkLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        videoLinkLbl.setBounds(35, 220, 150, 30);
        centerPanel.add(videoLinkLbl);

        videoLink = new JTextField();
        videoLink.setEditable(false);
        videoLink.setBounds(200, 220, 450, 30);
        centerPanel.add(videoLink);

        // Video Description
        JLabel videoDescriptionLbl = new JLabel("Video Description:");
        videoDescriptionLbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        videoDescriptionLbl.setBounds(35, 270, 150, 30);
        centerPanel.add(videoDescriptionLbl);

        videoDescription = new JTextArea();
        videoDescription.setLineWrap(true);
        videoDescription.setWrapStyleWord(true);
        JScrollPane scroll1 = new JScrollPane(videoDescription);
        scroll1.setBounds(200, 270, 450, 130);
        centerPanel.add(scroll1);
        
        // Open Button
        watchBtn = new JButton("Watch this video");
        watchBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        watchBtn.setPreferredSize(new Dimension(30, 30));
        watchBtn.setForeground(Color.WHITE);
        watchBtn.setBackground(new Color(92, 124, 250));
        watchBtn.setFocusPainted(false);
        watchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        watchBtn.setOpaque(true);
        watchBtn.addActionListener(this);
        add(watchBtn, BorderLayout.SOUTH);

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

    private void loadVideos(String courseName) {
        try {
            DBConnection c1 = new DBConnection();
            String query = String.format(
                    "SELECT Video_ID, Title FROM Videos WHERE course_ID = "
                    + "(SELECT course_ID FROM Courses WHERE Name = '%s')",
                    courseName);
            ResultSet rs = c1.s.executeQuery(query);

            videoListModel.clear();
            videoIds = new ArrayList<>();
            while (rs.next()) {
                videoIds.add(rs.getInt("Video_ID"));
                videoListModel.addElement(rs.getString("Title"));
            }

            if (videoListModel.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No videos are available for this course.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadVideoDetails(int videoId) {
        try {
            DBConnection c1 = new DBConnection();
            String query = String.format("SELECT Link, Video_Description FROM Videos WHERE Video_ID = %d", videoId);
            ResultSet rs = c1.s.executeQuery(query);

            if (rs.next()) {
                link = rs.getString("Link");
                desc = rs.getString("Video_Description");
                videoLink.setText(link);
                videoDescription.setText(desc);
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
            loadVideos(selectedCourse);
        } else if (e.getSource() == watchBtn) {
            int selectedIndex = videoList.getSelectedIndex();
            if (selectedIndex != -1) {
                int videoId = videoIds.get(selectedIndex);
                String videoUrl = link;
                openVideo(videoUrl);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a video to open.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new WatchVideo();
    }

    private void openVideo(String videoUrl) {
        if (!videoUrl.isEmpty()) {
                try {
                    java.awt.Desktop.getDesktop().browse(new java.net.URI(videoUrl));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error opening video link.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No video link available for the selected video.", "Error", JOptionPane.WARNING_MESSAGE);
            }
    }
}
