import java.io.IOException;

/**
 * Main class that contains the main method to run the program.
 */
public class Main {
    /**
     * Main method that creates an instance of the Runner class and calls its run method.
     * @param args Command line arguments
     * @throws IOException if an I/O error occurs
     */
    public static void main(String[] args) throws IOException {
        // Create an instance of the Runner class
        Runner runner = new Runner();
        // Call the run method of the Runner class with the provided command line arguments
        runner.run(args);
    }
}