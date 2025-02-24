package javasemesterproject.Teacher;

//import javasemesterproject.DBConnection;
//import javax.swing.JOptionPane;
//
//public class TeacherDeleteAccount{
//    int input;
//    public TeacherDeleteAccount(){
//
//            input = JOptionPane.showConfirmDialog(null, "Do you want to proceed?", "Select an Option...",
//            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
//            
//            if(input == 0){
//                try{
//                    DBConnection c1 = new DBConnection();
//
//                    String q = "Delete From Teacher Where teacherID ='" + TeacherLogin.currentTeacherID + "'";
//
//                    int x = c1.s.executeUpdate(q);
//                    if(x == 0){
//                        JOptionPane.showMessageDialog(null, "Got some error");
//                    }else{
//                        JOptionPane.showMessageDialog(null, "Account Deleted Successfully");
//                        
//                    }
//            }catch(Exception e){
//                e.printStackTrace();
//                }
//            }
//        
//        }
//    public static void main(String[] args) {
//        new TeacherDeleteAccount();
//    }
//}

import com.formdev.flatlaf.FlatLightLaf;
import javasemesterproject.DBConnection;

import javax.swing.*;
import java.awt.*;
import javasemesterproject.Main;

public class TeacherDeleteAccount {
    private final int userChoice;

    public TeacherDeleteAccount() {
        setupFlatLaf();
        userChoice = showConfirmationDialog();
        if (userChoice == JOptionPane.YES_OPTION) {
            deleteAccount();
        }
    }

    private void setupFlatLaf() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
    }

    /**
     * Displays a confirmation dialog to the user.
     *
     * @return user's choice from the options.
     */
    private int showConfirmationDialog() {
        return JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete your account?\nThis action cannot be undone.",
                "Confirm Account Deletion",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
    }


    private void deleteAccount() {
        try {
            DBConnection dbConnection = new DBConnection();
            String query = "DELETE FROM Teacher WHERE teacherID = '" + TeacherLogin2.currentTeacherID + "'";
            int result = dbConnection.s.executeUpdate(query);

            if (result > 0) {
                JOptionPane.showMessageDialog(
                        null,
                        "Account deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                navigateToMainMenu();
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Error occurred while deleting the account. Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "An unexpected error occurred: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    /**
     * Redirects the user to the main menu after account deletion.
     */
    private void navigateToMainMenu() {
        SwingUtilities.invokeLater(() -> {
            new Main();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(TeacherDeleteAccount::new);
    }
}
