import java.io.IOException;
import java.util.Scanner;

/**
 * Runner class that contains the main method to run the program.
 */

public class Runner {
    /**
     * This method takes in a String array of arguments and throws an IOException.
     * It checks if the length of the arguments is greater than 0. If it is, it creates instances of the FileService and CaesarCipher classes.
     * It then uses a switch statement to check the first argument and perform one of three operations: "ENCRYPT", "DECRYPT", or "BRUTE_FORCE".
     *
     * @param args String array of arguments
     * @throws IOException
     */
    public void run(String[] args) throws IOException {
        // Check if arguments are provided
        if (args.length > 0) {
            // Create instances of FileService and CaesarCipher
            FileService fileService = new FileService();
            CaesarCipher caesarCipher = new CaesarCipher();
            // Check first argument for operation
            switch (args[0]) {
                case "ENCRYPT":
                    // Encrypt file
                    fileService.writeEncryptedFile(args[1], caesarCipher.Encrypt(fileService.readFile(args[1]), Integer.parseInt(args[2])));
                    break;
                case "DECRYPT":
                    // Decrypt file
                    fileService.writeDecryptedFile(args[1], caesarCipher.Decrypt(fileService.readFile(args[1]), Integer.parseInt(args[2])));
                    break;
                case "BRUTE_FORCE":
                    // Brute force decryption
                    fileService.writeDecryptedFileBruteForce(args[1], caesarCipher.decryptCaesarWithFrequencies(fileService.readFile(args[1])));
                    break;
                default:
                    // Invalid operation
                    System.out.println("Invalid operation. Please provide a valid operation: ENCRYPT, DECRYPT or BRUTE_FORCE.");
            }
        } else {
            // No arguments provided
            System.out.println("What interface do you want to use:");
            System.out.println("Swing GUI | Console |");
            System.out.println("---------------------");
            System.out.println("    1     |    2    |\n");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose an action...");
            int numberOfAction = scanner.nextInt();
            scanner.nextLine();

            // Check user input for interface choice
            switch (numberOfAction) {
                case 1:
                    // Swing GUI
                    new CaesarGUI();
                    break;
                case 2:
                    // Console
                    CLI client = new CLI();
                    client.getStart();
                    break;
                default:
                    // Invalid operation
                    System.out.println("Invalid operation. Please provide a valid operation: 1 or 2");
            }
        }
    }
}