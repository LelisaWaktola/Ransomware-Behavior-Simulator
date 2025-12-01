import javax.swing.*;
import java.io.File;

public class GUIAntivirusSimulator {

    public static final String TARGET_FOLDER = "C:\\test file";

    public static void main(String[] args) {
        File folder = new File(TARGET_FOLDER);
        File[] files = folder.listFiles();

        boolean threatFound = false;
        StringBuilder encryptedFiles = new StringBuilder();

        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".encrypted")) {
                    encryptedFiles.append(file.getName()).append("\n");
                    threatFound = true;
                }
            }
        }

        if (threatFound) {
            // Show alert dialog
            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Encrypted files detected:\n" + encryptedFiles + "\nDo you want to restore them?",
                    "Ransomware Alert!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (choice == JOptionPane.YES_OPTION) {
                // User allows recovery → run Decryptor
                Decryptor.runDecryptor();
                JOptionPane.showMessageDialog(null, "Files have been restored!", "Recovery Complete", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Recovery canceled. Files remain encrypted.", "Recovery Canceled", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No infected files found. Your folder is safe!", "Scan Complete", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
