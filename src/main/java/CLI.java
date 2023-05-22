import java.io.IOException;
import java.util.Scanner;

/**
 * This class provides a Command Line Interface (CLI) for encrypting and decrypting text files using the CaesarCipher cipher.
 */
public class CLI {
    /**
     * This method starts the CLI and prompts the user to choose an action and enter the necessary information.
     */
    public void getStart() {
        System.out.println("ENCRYPT | DECRYPT | BRUTE_FORCE |");
        System.out.println("--------------------------------");
        System.out.println("   1    |    2    |      3      |\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an action...");
        int numberOfAction = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter a path to file...");
        String pathToFile = scanner.nextLine();

        CaesarCipher caesarCipher = new CaesarCipher();
        FileService fileService = new FileService();
        try {
            if (numberOfAction == 1) {
                System.out.println("Enter a key...");
                int key = scanner.nextInt();
                scanner.nextLine();
                // Encrypts the file at the given path using the given key and writes the result to a new file
                fileService.writeEncryptedFile(pathToFile, caesarCipher.Encrypt(fileService.readFile(pathToFile), key));
                System.out.println("Successfully!");
            } else if (numberOfAction == 2) {
                System.out.println("Enter a key...");
                int key = scanner.nextInt();
                // Decrypts the file at the given path using the given key and writes the result to a new file
                fileService.writeDecryptedFile(pathToFile, caesarCipher.Decrypt(fileService.readFile(pathToFile), key));
                System.out.println("Successfully!");
            } else if (numberOfAction == 3) {
                // Decrypts the file at the given path using brute-force and writes the result to a new file
                fileService.writeDecryptedFileBruteForce(pathToFile, caesarCipher.decryptCaesarWithFrequencies(fileService.readFile(pathToFile)));
                System.out.println("Successfully!");
            } else {
                System.out.println("Enter correct number of action...");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}