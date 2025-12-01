import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.*;

public class EncryptorSimulator {

    public static final String TARGET_FOLDER = "C:\\test file";

    public static void main(String[] args) {
        try {
            // 1. Generate a safe AES key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            SecretKey key = keyGen.generateKey();

            // 2. Save key so decryption is possible (SAFE)
            Files.write(Paths.get("key.bin"), key.getEncoded());

            // 3. Encrypt each file in the target folder
            File folder = new File(TARGET_FOLDER);
            File[] files = folder.listFiles();

            if (files == null) {
                System.out.println("Folder not found!");
                return;
            }

            for (File file : files) {
                if (file.isFile()) {
                    encryptFile(file, key);
                }
            }

            System.out.println("SAFE SIMULATION COMPLETE: Files encrypted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void encryptFile(File inputFile, SecretKey key) throws Exception {
        byte[] fileBytes = Files.readAllBytes(inputFile.toPath());

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(fileBytes);

        // Write encrypted file with .encrypted extension
        File encryptedFile = new File(inputFile.getAbsolutePath() + ".encrypted");
        Files.write(encryptedFile.toPath(), encryptedBytes);

        // Safe delete of original
        inputFile.delete();
    }
}
