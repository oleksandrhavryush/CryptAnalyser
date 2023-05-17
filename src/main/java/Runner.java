import java.io.IOException;

/**
 * Runner class that contains the main method to run the program.
 */
public class Runner {

    /**
     * Main method that takes command line arguments and performs the specified operation.
     *
     * @param args Command line arguments
     * @throws IOException if an I/O error occurs
     */
    public void run(String[] args) throws IOException {
        // Check if command line arguments are provided
        if (args.length > 0) {
            // Create instances of FileService and CaesarCipher classes
            FileService fileService = new FileService();
            CaesarCipher caesarCipher = new CaesarCipher();

            // Check the first argument to determine the operation
            switch (args[0]) {
                case "ENCRYPT":
                    // Encrypt the file and write the encrypted content to a new file
                    fileService.writeEncryptedFile(args[1], caesarCipher.Encrypt(fileService.readFile(args[1]), Integer.parseInt(args[2])));
                    break;
                case "DECRYPT":
                    // Decrypt the file and write the decrypted content to a new file
                    fileService.writeDecryptedFile(args[1], caesarCipher.Decrypt(fileService.readFile(args[1]), Integer.parseInt(args[2])));
                    break;
                case "BRUTE_FORCE":
                    // Perform brute force decryption and write the decrypted content to a new file
                    fileService.writeDecryptedFileBruteForce(args[1], caesarCipher.decryptCaesarWithFrequencies(fileService.readFile(args[1])));
                    break;
                default:
                    // Invalid operation
                    System.out.println("Invalid operation. Please provide a valid operation: ENCRYPT, DECRYPT or BRUTE_FORCE.");
            }
        } else {
            // No command line arguments provided, launch GUI
            new CaesarGUI();
        }
    }
}