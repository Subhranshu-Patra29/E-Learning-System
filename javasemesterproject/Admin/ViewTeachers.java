package javasemesterproject.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javasemesterproject.DBConnection;

public class ViewTeachers extends JFrame implements ActionListener {

    private static final int ROW_HEIGHT = 96;
    private static final Dimension FRAME_SIZE = new Dimension(1000, 500);
    private static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 25);
    private static final Font TABLE_FONT = new Font("SansSerif", Font.PLAIN, 12);
    private static final Font HEADER_FONT = new Font("SansSerif", Font.BOLD, 15);

    private JTable table;
    private JButton deleteButton;
    private DefaultTableModel model;
    private String[] columnNames;
    private Object[][] data;

    public ViewTeachers() {
        super("View Teachers");
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Teachers", JLabel.CENTER);
        title.setFont(TITLE_FONT);
        title.setOpaque(true);
        title.setBackground(new Color(51, 102, 255));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // Table
        fetchTeachersData();
        setupTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Delete Button
        setupDeleteButton();
        add(deleteButton, BorderLayout.SOUTH);

        // Frame Settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(FRAME_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void fetchTeachersData() {
        DBConnection connection = new DBConnection();
        try {
            String query = "SELECT * FROM Teacher";
            ResultSet rs = connection.s.executeQuery(query);
            processResultSet(rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            connection.Close();
        }
    }

    private void processResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = metaData.getColumnName(i + 1);
        }

        rs.last();
        int rowCount = rs.getRow();
        rs.beforeFirst();

        data = new Object[rowCount][columnCount];
        int row = 0;
        while (rs.next()) {
            for (int col = 0; col < columnCount; col++) {
                if ("picture".equalsIgnoreCase(columnNames[col])) {
                    data[row][col] = getImageIcon(rs.getBytes(col + 1));
                } else {
                    data[row][col] = rs.getString(col + 1);
                }
            }
            row++;
        }
    }

    private void setupTable() {
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                return "picture".equalsIgnoreCase(columnNames[column]) ? ImageIcon.class : Object.class;
            }
        };

        table = new JTable(model);
        table.setFont(TABLE_FONT);
        table.setRowHeight(ROW_HEIGHT);

        JTableHeader header = table.getTableHeader();
        header.setFont(HEADER_FONT);
        header.setBackground(Color.LIGHT_GRAY);
    }

    private void setupDeleteButton() {
        deleteButton = new JButton("Delete");
        deleteButton.setToolTipText("Click to delete the selected record");
        deleteButton.setPreferredSize(new Dimension(100, 40));
        deleteButton.addActionListener(this);
    }

    private ImageIcon getImageIcon(byte[] imageBytes) {
        try (InputStream is = new ByteArrayInputStream(imageBytes)) {
            BufferedImage bufferedImage = ImageIO.read(is);
            Image scaledImage = bufferedImage.getScaledInstance(ROW_HEIGHT, ROW_HEIGHT, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            Logger.getLogger(ViewTeachers.class.getName()).log(Level.SEVERE, "Error processing image", e);
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            deleteSelectedRow();
        }
    }

    private void deleteSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No row selected.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String teacherID = model.getValueAt(selectedRow, 0).toString();
        DBConnection connection = new DBConnection();
        try {
            String query = "DELETE FROM Teacher WHERE teacherID = '" + teacherID + "'";
            int result = connection.s.executeUpdate(query);

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Row deleted successfully.");
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting row.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } finally {
            connection.Close();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewTeachers::new);
    }
}
