# Caesar Cipher Program

This Java program provides a Command Line Interface (CLI) and a graphical user interface (GUI) for encrypting and decrypting text files using the Caesar cipher.

## How to Run the Program

To run the program, follow these steps:

1. Ensure that you have Java installed on your system.
2. Download the program’s source code from GitHub.
3. Compile the source code using a Java compiler.
4. Run the compiled program using the command java Main.

## Running the JAR File

You can run JAR file by following these steps:

1. Ensure that you have Java installed on your system.
2. Open a command prompt or terminal window and navigate to the directory where the JAR file is located.
3. Run the JAR file using the command java -jar <JAR_FILE_NAME>, replacing <JAR_FILE_NAME> with the name of the JAR file.

## Program Structure and Logic

The program consists of the following classes:

- **Main**: The main class that contains the main method to start the program. It creates an instance of the `Runner` class and calls its run method.

- **Runner**: This class contains the main method to run the program. It takes in a String array of arguments and can run the program in either console interface mode or Swing GUI mode. If arguments are provided, it creates instances of the `FileService` and `CaesarCipher` classes and uses a switch statement to check the first argument and perform one of three operations: “ENCRYPT”, “DECRYPT”, or “BRUTE_FORCE”. If no arguments are provided, it starts the Swing GUI or console interface.

- **CLI**: This class provides a console interface for the program. It prompts the user to choose an action (encryption, decryption, or brute force) and enter the necessary information. The `getStart` method starts the console interface and prompts the user to choose an action by entering a number (1 for encryption, 2 for decryption, or 3 for brute force). The user is then prompted to enter a path to a file. If the user chooses encryption or decryption, they are also prompted to enter a key. The method then creates instances of the `CaesarCipher` and `FileService` classes and performs the chosen action on the specified file.

- **CaesarGUI**: This class provides a GUI for the program. It allows the user to choose an action (encrypt, decrypt, or brute force), enter a path to a file, and enter a key (for encryption and decryption). The GUI is created using Java Swing components such as JFrame, JPanel, JButton, JLabel, and JTextField. The layout of the components is managed using BorderLayout and GridLayout. The CaesarGUI class extends JFrame and implements ActionListener to handle button clicks. When a button is clicked, the actionPerformed method is called and performs the appropriate action based on which button was clicked.

- **FileService**: This class provides methods for reading and writing encrypted and decrypted files. The readFile method reads the content of a file at the given path and returns it as a string. The `writeEncryptedFile`, `writeDecryptedFile`, and `writeFileWithStatus` methods write the given file content to a new file with the same name as the original file, but with a status added to the file name.

- **LanguageDetector**: This class provides a method for detecting the language of a given text. The detectLanguage method detects the language of the given text and returns a string representing the detected language.

- **CaesarCipher**: This class implements a CaesarCipher cipher for encrypting and decrypting text. The encrypt method encrypts the given text using the CaesarCipher cipher algorithm with the given key. The decrypt method decrypts the given text that was encrypted using the CaesarCipher cipher algorithm with the given key. The `transformChar` method transforms a single character using the provided map of alphabets. The `decryptWithFrequencies` method decrypts a given encrypted text using the CaesarCipher cipher with frequencies.

## Interesting Features

- **CLI and GUI Interfaces**: The program provides both a command-line interface (CLI) and a graphical user interface (GUI), allowing users to choose their preferred mode of interaction.

- **File Encryption and Decryption**: The program can encrypt and decrypt text files using the Caesar cipher algorithm, providing a simple yet effective method of securing sensitive information.

- **Language Detection**: The program includes a language detection feature, which can automatically detect the language of an encrypted text and use language-specific frequencies to perform accurate decryption.

- **Brute Force Decryption**: In case the key for decryption is unknown, the program offers a brute force decryption option, which systematically tries all possible keys to find the correct decryption result.
