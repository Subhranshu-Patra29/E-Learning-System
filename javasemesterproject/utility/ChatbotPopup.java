package javasemesterproject.utility;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatbotPopup extends JFrame implements ActionListener {

    private JTextArea inputArea;
    private JTextArea responseArea;
    private JButton sendButton;

    private static final String API_URL = "https://elearningsystemchatbot.onrender.com/generate-response";

    
    public ChatbotPopup() {
        // Apply FlatLaf theme
        FlatLightLaf.setup();

        setTitle("Chatbot");
        setSize(650, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Title label
        JLabel titleLabel = new JLabel("Chat with AI", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(51, 102, 255));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        // Input area
        inputArea = new JTextArea(5, 30);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        JScrollPane inputScrollPane = new JScrollPane(inputArea);
        inputScrollPane.setBorder(BorderFactory.createTitledBorder("Enter your query:"));

        // Response area
        responseArea = new JTextArea(8, 30);
        responseArea.setEditable(false);
        responseArea.setLineWrap(true);
        responseArea.setWrapStyleWord(true);
        JScrollPane responseScrollPane = new JScrollPane(responseArea);
        responseScrollPane.setBorder(BorderFactory.createTitledBorder("Chatbot Response:"));

        // Send button
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);

        // Bottom panel for button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(sendButton);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(inputScrollPane, BorderLayout.NORTH);
        mainPanel.add(responseScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton) {
            String userInput = inputArea.getText().trim();
            if (!userInput.isEmpty()) {
                String chatbotResponse = callChatbotAPI(userInput);
                responseArea.setText(chatbotResponse);
                inputArea.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a query!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static void main(String args[]) {
        new ChatbotPopup();
    }

    private String callChatbotAPI(String prompt) {
        try {
            // Create URL object
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configure HTTP request
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Prepare JSON payload
            String jsonPayload = "{\"query\": \"" + prompt + "\"}";

            // Send request
            try (OutputStream os = connection.getOutputStream()) {
                os.write(jsonPayload.getBytes());
                os.flush();
            }

            // Read response
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line).append("\n");
            }

            br.close();
            return response.toString();

        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: Unable to connect to the chatbot. Please try again later.";
        }
    }
}
