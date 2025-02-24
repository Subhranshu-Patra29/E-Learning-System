package javasemesterproject.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class chatFromJava {
    public static void main(String[] args) {
        try {
            // Define the Python command and the path to your script
            String pythonCommand = "python"; // Use "python" or "python3" depending on your environment
            String scriptPath = "D:\\Projects\\ChatBot\\chatbot.py"; // Replace with your actual script path
            
            // Prompt to be sent to Python script
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter prompt: ");
            String prompt = br.readLine();

            // Create the process builder to run the Python script
            ProcessBuilder processBuilder = new ProcessBuilder(pythonCommand, scriptPath);
            processBuilder.environment().put("PYTHONIOENCODING", "utf-8");
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Send the prompt to the Python script
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(process.getOutputStream(), "UTF-8"), true);
            writer.println(prompt);
            writer.close();

            // Capture the output from the Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }

            // Close the reader
            reader.close();

            // Output the response
            System.out.println("Response from Python script:");
            System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
