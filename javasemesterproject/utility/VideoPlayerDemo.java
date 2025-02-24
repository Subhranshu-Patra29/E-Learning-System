package javasemesterproject.utility;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class VideoPlayerDemo extends JFrame {
    private JFXPanel jfxPanel;

    public VideoPlayerDemo(String videoUrl) {
        setTitle("Integrated Video Player");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        jfxPanel = new JFXPanel(); // JavaFX Panel
        add(jfxPanel, BorderLayout.CENTER);

        Platform.runLater(() -> initFX(videoUrl)); // Run JavaFX code
        setVisible(true);
    }

    private void initFX(String videoUrl) {
        try {
            Media media = new Media(new URI(videoUrl).toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            MediaView mediaView = new MediaView(mediaPlayer);

            // JavaFX scene setup
            Scene scene = new Scene(new javafx.scene.Group(mediaView), 800, 600);
            mediaView.setFitWidth(800);
            mediaView.setFitHeight(600);

            jfxPanel.setScene(scene); // Attach scene to JFXPanel
            mediaPlayer.play();       // Start video playback
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error playing video: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VideoPlayerDemo("https://www.youtube.com/live/i5hUQVwtock?si=4q7uRAFMVmvcdYiM"));
    }
}
