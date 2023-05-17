import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The CaesarGUI class provides a graphical user interface (GUI) for a CaesarCipher cipher program.
 * The GUI allows the user to choose an action (encrypt, decrypt, or brute force), enter a path to a file,
 * and enter a key (for encryption and decryption).
 * <p>
 * The GUI is created using Java Swing components such as JFrame, JPanel, JButton, JLabel, and JTextField.
 * The layout of the components is managed using BorderLayout and GridLayout.
 * <p>
 * The CaesarGUI class extends JFrame and implements ActionListener to handle button clicks.
 * When a button is clicked, the actionPerformed method is called and performs the appropriate action
 * based on which button was clicked.
 */
public class CaesarGUI extends JFrame implements ActionListener {
    // Instance variables for the Swing components
    private JTextField pathField;
    private JTextField keyField;
    private JButton encryptButton;
    private JButton decryptButton;
    private JButton bruteForceButton;

    /**
     * Constructs a new CaesarGUI object and sets up the GUI.
     */
    public CaesarGUI() {
        // Set up the frame
        setTitle("CaesarCipher Cipher");
        setBounds(500, 100, 900, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(40, 20));

        // Create the components
        JLabel pathLabel = new JLabel("Enter a path to file:");
        pathField = new JTextField();
        JLabel keyLabel = new JLabel("Enter a key:");
        keyField = new JTextField();
        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");
        bruteForceButton = new JButton("Brute Force");

        // Add action listeners to the buttons
        encryptButton.addActionListener(this);
        decryptButton.addActionListener(this);
        bruteForceButton.addActionListener(this);

        // Create panels to organize the components
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 2));
        topPanel.add(pathLabel);
        topPanel.add(pathField);
        topPanel.add(keyLabel);
        topPanel.add(keyField);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(encryptButton);
        bottomPanel.add(decryptButton);
        bottomPanel.add(bruteForceButton);

        // Add the panels to the frame
        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Show the frame
        setVisible(true);
    }

    /**
     * Handles button clicks by performing the appropriate action based on which button was clicked.
     *
     * @param e the ActionEvent that occurred
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String pathToFile = pathField.getText();
        CaesarCipher caesarCipher = new CaesarCipher();
        String encryptedText = "";

        if (e.getSource() == encryptButton) {
            int key = Integer.parseInt(keyField.getText());
            try {
                encryptedText = caesarCipher.Encrypt(FileService.readFile(pathToFile), key);
                FileService.writeEncryptedFile(pathToFile, caesarCipher.Encrypt(FileService.readFile(pathToFile), key));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(this, "File encrypted successfully!");
            JTextArea textArea = new JTextArea(encryptedText);
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(this, scrollPane, "Encrypted text", JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getSource() == decryptButton) {
            int key = Integer.parseInt(keyField.getText());
            try {
                encryptedText = caesarCipher.Decrypt(FileService.readFile(pathToFile), key);
                FileService.writeDecryptedFile(pathToFile, caesarCipher.Decrypt(FileService.readFile(pathToFile), key));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(this, "File decrypted successfully!");
            JTextArea textArea = new JTextArea(encryptedText);
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(this, scrollPane, "Decrypted text", JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getSource() == bruteForceButton) {
            try {
                encryptedText = caesarCipher.decryptCaesarWithFrequencies(FileService.readFile(pathToFile));
                FileService.writeDecryptedFileBruteForce(pathToFile, caesarCipher.decryptCaesarWithFrequencies(FileService.readFile(pathToFile)));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(this, "Brute force file decrypted successful!");
            JTextArea textArea = new JTextArea(encryptedText);
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(this, scrollPane, "Brute force decrypted text", JOptionPane.INFORMATION_MESSAGE);

        }
    }
}