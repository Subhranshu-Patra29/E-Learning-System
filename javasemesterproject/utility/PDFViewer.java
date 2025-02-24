package javasemesterproject.utility;

import com.formdev.flatlaf.FlatLightLaf;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFViewer extends JFrame implements ActionListener, WindowStateListener {

    private PDDocument document;
    private PDFRenderer pdfRenderer;
    private JScrollPane scrollPane;
    private JPanel pagePanel;
    private JButton prevBtn, nextBtn, readOutBtn, chatbotBtn;
    private int currentPage = 0;
    private float zoomFactor = 0.5f;

    private boolean isReading = false;
    private Process pythonProcess;

    public PDFViewer(String pdfPath) {
        // Apply FlatLaf theme
        FlatLightLaf.setup();

        setTitle("PDF Viewer");
        setLayout(new BorderLayout(10, 10));
        setSize(1200, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Title
        JLabel title = new JLabel("PDF Viewer", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setOpaque(true);
        title.setBackground(new Color(51, 102, 255));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // Center panel with scrollable PDF
        pagePanel = new JPanel();
        pagePanel.setLayout(new BoxLayout(pagePanel, BoxLayout.Y_AXIS));
        pagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        try {
            // Load PDF document
            document = Loader.loadPDF(new File(pdfPath));
            pdfRenderer = new PDFRenderer(document);

            // Set initial zoom level
            loadPage(currentPage);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Scroll pane for PDF rendering
        scrollPane = new JScrollPane(pagePanel);
        add(scrollPane, BorderLayout.CENTER);

        // Control buttons
        JPanel controlPanel = new JPanel();
        scrollPane.getVerticalScrollBar().setUnitIncrement(40);
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        prevBtn = new JButton("Previous Page");
        nextBtn = new JButton("Next Page");
        readOutBtn = new JButton("Read Out Contents");
        chatbotBtn = new JButton("Connect to Chatbot");

        prevBtn.addActionListener(this);
        nextBtn.addActionListener(this);
        readOutBtn.addActionListener(this);
        chatbotBtn.addActionListener(this);

        controlPanel.add(prevBtn);
        controlPanel.add(nextBtn);
        controlPanel.add(readOutBtn);
        controlPanel.add(chatbotBtn);

        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadPage(int pageNumber) throws IOException {
        pagePanel.removeAll(); // Clear current page
        PDPage page = document.getPage(pageNumber);
        Image image = pdfRenderer.renderImageWithDPI(pageNumber, 300 * zoomFactor); // Render page at the correct zoom
        JLabel pageLabel = new JLabel(new ImageIcon(image));
        pagePanel.add(pageLabel);
        pagePanel.revalidate();
        pagePanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == prevBtn) {
                if (currentPage > 0) {
                    currentPage--;
                    loadPage(currentPage);
                }
            } else if (e.getSource() == nextBtn) {
                if (currentPage < document.getNumberOfPages() - 1) {
                    currentPage++;
                    loadPage(currentPage);
                }
            } else if (e.getSource() == readOutBtn) {
                handleReadOut();
            } else if (e.getSource() == chatbotBtn) {
                new ChatbotPopup();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PDFViewer("D:\\Projects\\E-Learning-System-Java-GUI-App\\src\\javasemesterproject\\resources\\Cloud Computing Module 2.pdf");
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        // normal state
        if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.NORMAL) {
            zoomFactor = 0.5f;
        } // maximized
        else if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
            zoomFactor = 0.55f;
        }
    }

    private void handleReadOut() throws IOException {
        if (isReading) {
            // Stop the Python process if it's running
            if (pythonProcess != null && pythonProcess.isAlive()) {
                pythonProcess.destroy();
                JOptionPane.showMessageDialog(this, "Reading stopped.");
            }
            isReading = false;
        } else {
            // Start reading
            PDFTextStripper textStripper = new PDFTextStripper();
            textStripper.setStartPage(currentPage + 1); // PDFBox pages are 1-based
            textStripper.setEndPage(currentPage + 1);
            String pageText = textStripper.getText(document);
            if (pageText.isBlank()) {
                JOptionPane.showMessageDialog(this, "No readable content on this page.");
            } else {
                readTextAloud(pageText);
            }

        }
    }

    private void readTextAloud(String text) {
        // Run the process in a separate thread
        new Thread(() -> {
            try {
                // Define the Python command and the path to your script
                String pythonCommand = "python"; // Use "python" or "python3" depending on your environment
                String scriptPath = "D:\\Projects\\Desktop_Assistant\\voice_op.py";

                // Create the process builder to run the Python script
                ProcessBuilder processBuilder = new ProcessBuilder(pythonCommand, scriptPath);
                processBuilder.environment().put("PYTHONIOENCODING", "utf-8");
                processBuilder.redirectErrorStream(true);

                // Start the process and send the text
                pythonProcess = processBuilder.start();
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(pythonProcess.getOutputStream(), "UTF-8"), true);
                writer.println(text); // Pass the text to be spoken
                writer.close();

                // Mark reading as active
                isReading = true;

                // Wait for the process to finish
                int exitCode = pythonProcess.waitFor();
                isReading = false; // Reset the flag after the process completes

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
