import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.SecureRandom;

public class MenuUtils {

    public SecretKey randomEncryptionKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128, new SecureRandom());
        return keyGen.generateKey();
    }

    public void encryptFile(byte[] fileContent, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedContent = cipher.doFinal(fileContent);

        File encryptedFile = new File("ciphertext.txt");
        try (FileOutputStream fos = new FileOutputStream(encryptedFile)) {
            fos.write(encryptedContent);
        }
    }

    public void decryptFile(byte[] encryptedContent, String encryptedKey) throws Exception {
        byte[] decodedKey = java.util.Base64.getDecoder().decode(encryptedKey);
        SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedContent = cipher.doFinal(encryptedContent);

        File decryptedFile = new File("plaintext.txt");
        try (FileOutputStream fos = new FileOutputStream(decryptedFile)) {
            fos.write(decryptedContent);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
