package javasemesterproject.Admin;

import javasemesterproject.DBConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewStudents extends JFrame implements ActionListener {
    private String[] columnNames;
    private Object[][] data;
    private JTable table;
    private JButton deleteButton;
    private DefaultTableModel tableModel;
    private JLabel titleLabel;

    public ViewStudents() {
        super("View Students");
        setupUI();
        fetchAndDisplayStudents();
    }

    /**
     * Sets up the UI layout and components.
     */
    private void setupUI() {
        setLayout(new BorderLayout());

        // Title Label
        titleLabel = new JLabel("Students", JLabel.CENTER);
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(51, 102, 255));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        // Table and ScrollPane
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel) {
            @Override
            public Class<?> getColumnClass(int column) {
                return (column == 9) ? ImageIcon.class : Object.class;
            }
        };
        setupTableStyle();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Delete Button
        deleteButton = new JButton("Delete");
        deleteButton.setToolTipText("Click to Delete Selected Record.");
        deleteButton.addActionListener(this);
        deleteButton.setPreferredSize(new Dimension(20, 50));
        add(deleteButton, BorderLayout.SOUTH);

        // Frame Settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Configures table style.
     */
    private void setupTableStyle() {
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.LIGHT_GRAY);
        header.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        table.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        table.setRowHeight(96);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * Fetches student data from the database and populates the table.
     */
    private void fetchAndDisplayStudents() {
        DBConnection dbConnection = new DBConnection();
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM Student";
            resultSet = dbConnection.s.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();

            // Extract column names
            int columnCount = metaData.getColumnCount();
            columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }

            // Extract data rows
            resultSet.last();
            int rowCount = resultSet.getRow();
            data = new Object[rowCount][columnCount];
            resultSet.beforeFirst();

            int rowIndex = 0;
            while (resultSet.next()) {
                for (int colIndex = 0; colIndex < columnCount; colIndex++) {
                    String columnName = columnNames[colIndex];
                    if (columnName.equalsIgnoreCase("picture")) {
                        byte[] imageBytes = resultSet.getBytes(columnName);
                        data[rowIndex][colIndex] = createImageIcon(imageBytes);
                    } else {
                        data[rowIndex][colIndex] = resultSet.getString(columnName);
                    }
                }
                rowIndex++;
            }

            // Populate the table
            tableModel.setDataVector(data, columnNames);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching student data.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            dbConnection.Close();
        }
    }

    /**
     * Creates an ImageIcon from byte data.
     *
     * @param imageBytes Byte array representing the image.
     * @return ImageIcon object.
     */
    private ImageIcon createImageIcon(byte[] imageBytes) {
        try (InputStream inputStream = new ByteArrayInputStream(imageBytes)) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            Image scaledImage = bufferedImage.getScaledInstance(96, 96, Image.SCALE_DEFAULT);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            Logger.getLogger(ViewStudents.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    /**
     * Handles delete button click to remove selected student record.
     *
     * @param e ActionEvent triggered by the delete button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            deleteSelectedStudent();
        }
    }

    /**
     * Deletes the selected student from the database and updates the table.
     */
    private void deleteSelectedStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String studentId = tableModel.getValueAt(selectedRow, 0).toString();
        DBConnection dbConnection = new DBConnection();

        try {
            String query = "DELETE FROM Student WHERE stdID = ?";
            PreparedStatement preparedStatement = dbConnection.c.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE);
            preparedStatement.setString(1, studentId);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete the student.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while deleting the student.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            dbConnection.Close();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewStudents::new);
    }
}
