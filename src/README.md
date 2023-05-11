CaesarCipher Cipher Program
This Java program provides a Command Line Interface (CLI) and a graphical user interface (GUI) for encrypting and decrypting text files using the CaesarCipher cipher.

How to Run the Program
To run the program, follow these steps:

Ensure that you have Java installed on your system.
Download the program's source code from GitHub.
Compile the source code using a Java compiler.
Run the compiled program using the command java Main.

Program Structure and Logic
The program consists of the following classes:

Main: The main class that contains the main method to start the program. It creates an instance of the Runner class and calls its run method.
Runner: This class provides the CLI for the program. It prompts the user to choose an action (encryption, decryption, or brute force) and enter the necessary information.
CaesarGUI: This class provides a GUI for the program. It allows the user to choose an action, enter a file path, and specify a key for encryption or decryption. The GUI is created using Java Swing components and extends the JFrame class.
FileService: This class provides methods for reading and writing encrypted and decrypted files. It has methods to read the content of a file, write encrypted content to a new file, and write decrypted content to a new file.
LanguageDetector: This class implements a language detection algorithm to determine the language of a given text. It has a method to detect the language of the text based on character frequencies.
CaesarCipher: This class implements the CaesarCipher cipher algorithm for encryption and decryption. It has methods to encrypt text using a specified key, decrypt text using a specified key, and perform brute force decryption by trying all possible keys.

Interesting Features
CLI and GUI Interfaces: The program provides both a command-line interface (CLI) and a graphical user interface (GUI), allowing users to choose their preferred mode of interaction.
File Encryption and Decryption: The program can encrypt and decrypt text files using the CaesarCipher cipher algorithm, providing a simple yet effective method of securing sensitive information.
Language Detection: The program includes a language detection feature, which can automatically detect the language of an encrypted text and use language-specific frequencies to perform accurate decryption.
Brute Force Decryption: In case the key for decryption is unknown, the program offers a brute force decryption option, which systematically tries all possible keys to find the correct decryption result.

The code is well-documented with Javadoc.