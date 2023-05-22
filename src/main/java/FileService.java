import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class provides methods for reading and writing encrypted and decrypted files.
 */
public class FileService {
    /**
     * This method reads the content of a file at the given path.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws IOException if an I/O error occurs
     */
    public String readFile(String filePath) throws IOException {
        return Files.readString(Paths.get(filePath));
    }

    /**
     * Writes the given file content to a new file with the same name as the original file,
     * but with "[ENCRYPTED]" added to the file name.
     *
     * @param filePath    the path of the original file
     * @param fileContent the content to be written to the new file
     * @throws IOException if an I/O error occurs
     */
    public void writeEncryptedFile(String filePath, String fileContent) throws IOException {
        writeFile(filePath, fileContent, "[ENCRYPTED]");
    }

    /**
     * Writes the given file content to a new file with the same name as the original file,
     * but with "[DECRYPTED]" added to the file name.
     *
     * @param filePath    the path of the original file
     * @param fileContent the content to be written to the new file
     * @throws IOException if an I/O error occurs
     */
    public void writeDecryptedFile(String filePath, String fileContent) throws IOException {
        writeFile(filePath, fileContent, "[DECRYPTED]");
    }

    /**
     * Writes the given file content to a new file with the same name as the original file,
     * but with "[DECRYPTED]" added to the file name.
     *
     * @param filePath    the path of the original file
     * @param fileContent the content to be written to the new file
     * @throws IOException if an I/O error occurs
     */
    public void writeDecryptedFileBruteForce(String filePath, String fileContent) throws IOException {
        writeFile(filePath, fileContent, "[B key-" + CaesarCipher.bestKey + "]");
    }

    /**
     * Writes the given file content to a new file with the same name as the original file,
     * but with the given status added to the file name.
     *
     * @param filePath    the path of the original file
     * @param fileContent the content to be written to the new file
     * @param status      the status to be added to the new file name
     * @throws IOException if an I/O error occurs
     */
    private void writeFile(String filePath, String fileContent, String status) throws IOException {
        Path path = Paths.get(filePath);
        String fileName = path.getFileName().toString();
        String fileExtension = "";
        int extensionIndex = fileName.lastIndexOf('.');
        if (extensionIndex > 0) {
            // Extracts the extension from fileName and removes it from fileName
            fileExtension = fileName.substring(extensionIndex);
            fileName = fileName.substring(0, extensionIndex);
        }

        // Adds status and extension to fileName
        String newFileName = fileName + status + fileExtension;
        Path newFilePath = path.resolveSibling(newFileName);
        Files.writeString(newFilePath, fileContent);
    }
}