import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Decryptor {

    public static final String TARGET_FOLDER = "C:\\test file";

    public static void runDecryptor() {
        try {
            byte[] keyBytes = Files.readAllBytes(Paths.get("key.bin"));
            SecretKey key = new SecretKeySpec(keyBytes, "AES");

            File folder = new File(TARGET_FOLDER);
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith(".encrypted")) {
                        decryptFile(file, key);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void decryptFile(File encFile, SecretKey key) throws Exception {
        byte[] encBytes = Files.readAllBytes(encFile.toPath());

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(encBytes);

        // Restore original file
        String originalName = encFile.getAbsolutePath().replace(".encrypted", "");
        Files.write(Paths.get(originalName), decryptedBytes);

        encFile.delete(); // remove encrypted version
    }
}
