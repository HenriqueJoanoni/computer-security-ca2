import javax.crypto.SecretKey;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Scanner;

public interface AppMenu {
    default void startMenu() throws Exception {
        Scanner sc = new Scanner(System.in);
        MenuUtils utils = new MenuUtils();

        String fileName, encodedSecretKey, decodedSecretKey;
        int userInputOption;
        SecretKey secret;

        System.out.println("\n" +
                "\n" +
                "d88888b d8b   db  .o88b. d8888b. db    db d8888b. d888888b d88888b d8888b.\n" +
                "88'     888o  88 d8P  Y8 88  `8D `8b  d8' 88  `8D `~~88~~' 88'     88  `8D\n" +
                "88ooooo 88V8o 88 8P      88oobY'  `8bd8'  88oodD'    88    88ooooo 88oobY'\n" +
                "88~~~~~ 88 V8o88 8b      88`8b      88    88~~~      88    88~~~~~ 88`8b  \n" +
                "88.     88  V888 Y8b  d8 88 `88.    88    88         88    88.     88 `88.\n" +
                "Y88888P VP   V8P  `Y88P' 88   YD    YP    88         YP    Y88888P 88   YD\n" +
                "\n");

        System.out.print("Select one of the options below: \n" +
                "1. Encrypt a file \n" +
                "2. Decrypt a file \n" +
                "3. Quit \n");

        try {
            userInputOption = sc.nextInt();
            switch (userInputOption) {
                case 1:
                    sc.nextLine();
                    System.out.println("Enter the file name to encrypt: ");
                    fileName = sc.nextLine();

                    File file = new File(fileName);
                    if (!file.exists()) {
                        System.out.println("File not found :(");
                        System.out.println("Please, enter a valid file name...");
                        startMenu();
                        break;
                    }
                    byte[] fileContent = Files.readAllBytes(file.toPath());

                    secret = utils.randomEncryptionKey();
                    encodedSecretKey = Base64.getEncoder().encodeToString(secret.getEncoded());
                    System.out.println("Encryption key is: " + encodedSecretKey + " | - KEEP IT SAFE!!");
                    utils.encryptFile(fileContent, secret);
                    System.out.println("The encrypted file has been saved as ciphertext.txt");

                    startMenu();
                    break;
                case 2:
                    sc.nextLine();
                    System.out.print("Enter the filename to decrypt: ");
                    fileName = sc.nextLine();

                    File decrypted = new File(fileName);
                    if (!decrypted.exists()) {
                        System.out.println("File not found :(");
                        System.out.println("Please, enter a valid file name...");
                        startMenu();
                        break;
                    }

                    byte[] encryptedContent;
                    try (FileInputStream fis = new FileInputStream(fileName)) {
                        encryptedContent = fis.readAllBytes();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.print("Enter the decryption key (in Base64 format): ");
                    decodedSecretKey = sc.nextLine();
                    utils.decryptFile(encryptedContent, decodedSecretKey);
                    System.out.println("Your plaintext file has been saved as plaintext.txt");

//                    startMenu();
                    break;
                case 3:
                    System.out.println("Bye! :)");
                    break;
                default:
                    System.out.println("Invalid option, please try again!");
                    startMenu();
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Invalid !! please input a number between 1 and 3");
            sc.nextLine();
            startMenu();
        }
    }
}
