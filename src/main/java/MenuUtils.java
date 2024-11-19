import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileOutputStream;
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

    public static String decryptFile() {
        return "";
    }
}
