import java.util.Scanner;

/**
 * This class provides a Command Line Interface (CLI) for encrypting and decrypting text files using the CaesarCipher cipher.
 */
public class CLI {
    /**
     * This method starts the CLI and prompts the user to choose an action and enter the necessary information.
     */
    public void getStart() {
        displayMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an action...");
        int numberOfAction = readInt(scanner);
        System.out.println("Enter a path to file...");
        String pathToFile = scanner.nextLine();

        CaesarCipher caesarCipher = new CaesarCipher();
        FileService fileService = new FileService();
        switch (numberOfAction) {
            case 1:
                System.out.println("Enter a key...");
                int key = readInt(scanner);
                // Encrypts the file at the given path using the given key and writes the result to a new file
                fileService.writeEncryptedFile(pathToFile, caesarCipher.Encrypt(fileService.readFile(pathToFile), key));
                System.out.println("Successfully!");
                break;
            case 2:
                System.out.println("Enter a key...");
                key = readInt(scanner);
                // Decrypts the file at the given path using the given key and writes the result to a new file
                fileService.writeDecryptedFile(pathToFile, caesarCipher.Decrypt(fileService.readFile(pathToFile), key));
                System.out.println("Successfully!");
                break;
            case 3:
                // Decrypts the file at the given path using brute-force and writes the result to a new file
                fileService.writeDecryptedFileBruteForce(pathToFile, caesarCipher.decryptCaesarWithFrequencies(fileService.readFile(pathToFile)));
                System.out.println("Successfully!");
                break;
            default:
                System.out.println("Enter correct number of action...");
        }
    }

    private void displayMenu() {
        System.out.println("ENCRYPT | DECRYPT | BRUTE_FORCE |");
        System.out.println("--------------------------------");
        System.out.println("   1    |    2    |      3      |\n");
    }

    private int readInt(Scanner scanner) {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}