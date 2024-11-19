import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public interface AppMenu {
    default void startMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        MenuUtils utils = new MenuUtils();

        String fileName;

        ArrayList<String> fileContent;
        ArrayList<String> encryptedContent = new ArrayList<>();

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

        switch (sc.nextInt()) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                System.out.println("Bye! :)");
                break;
            default:
                System.out.println("Invalid option, please try again!");
                startMenu();
                break;
        }
    }
}
