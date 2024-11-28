/**
 * FONT BASED ON: https://www.baeldung.com/java-aes-encryption-decryption
 */

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.Scanner;

public class MenuUtils {

    /**
     * @return SecretKey
     * @throws Exception Exception class
     */
    public SecretKey randomEncryptionKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128, new SecureRandom());
        return keyGen.generateKey();
    }

    /**
     * @param fileContent Plain text from the file
     * @param key         SecretKey value base64
     * @throws Exception Exception class
     */
    public void encryptFile(byte[] fileContent, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedContent = cipher.doFinal(fileContent);

        File encryptedFile = new File("ciphertext.txt");
        try (FileOutputStream fos = new FileOutputStream(encryptedFile)) {
            fos.write(encryptedContent);
        }
    }

    /**
     * @param encryptedContent Encrypted file content
     * @param encryptedKey     SecretKey value base64
     * @throws Exception Exception class
     */
    public void decryptFile(byte[] encryptedContent, String encryptedKey) throws Exception {
        byte[] decodedKey = java.util.Base64.getDecoder().decode(encryptedKey);
        SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedContent = cipher.doFinal(encryptedContent);

        File decryptedFile = new File("plaintext.txt");
        try (FileOutputStream fos = new FileOutputStream(decryptedFile)) {
            fos.write(decryptedContent);
            fos.flush();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * @param promptMessage Message to prompt
     * @param sc            Scanner
     * @return Returns filename
     * @throws Exception Exception class
     */
    public File validateFile(String promptMessage, Scanner sc) throws Exception {
        while (true) {
            System.out.println(promptMessage);
            String fileName = sc.nextLine();
            File file = new File(fileName);

            if (file.exists()) {
                return file;
            } else {
                System.out.println("File not found :(");
                System.out.println("Please, enter a valid file name...");
                AppMenu menu = new AppMenu() {
                };
                menu.startMenu();
            }
        }
    }

    /**
     * @param file File path
     * @return Bytes read from file
     * @throws IOException Exception class
     */
    public byte[] readFileContent(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }
}
