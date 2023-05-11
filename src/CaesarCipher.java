import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements a CaesarCipher cipher for encrypting and decrypting text.
 */
public class CaesarCipher {
    private String text;
    private int key;

    private static final String ENGLISH_ALPHABET_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ENGLISH_ALPHABET_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UKRAINIAN_ALPHABET_UPPER = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ";
    private static final String UKRAINIAN_ALPHABET_LOWER = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
    public static int bestKey = -1;

    /**
     * Encrypts the given text using the CaesarCipher cipher algorithm with the given key.
     *
     * @param text the text to encrypt
     * @param key  the key to use for encryption
     * @return the encrypted text
     */
    public String Encrypt(String text, int key) {
        Map<String, String> encryptedAlphabets = new HashMap<>();
        encryptedAlphabets.put(ENGLISH_ALPHABET_UPPER, getEncryptedAlphabet(ENGLISH_ALPHABET_UPPER, key));
        encryptedAlphabets.put(ENGLISH_ALPHABET_LOWER, getEncryptedAlphabet(ENGLISH_ALPHABET_LOWER, key));
        encryptedAlphabets.put(UKRAINIAN_ALPHABET_UPPER, getEncryptedAlphabet(UKRAINIAN_ALPHABET_UPPER, key));
        encryptedAlphabets.put(UKRAINIAN_ALPHABET_LOWER, getEncryptedAlphabet(UKRAINIAN_ALPHABET_LOWER, key));

        char[] textToChar = text.toCharArray();
        StringBuilder encryptText = new StringBuilder();
        for (int i = 0; i < textToChar.length; i++) {
            String language = LanguageDetector.detectLanguage(String.valueOf(textToChar[i]));
            switch (language) {
                case "ENG":
                    if (Character.isUpperCase(textToChar[i])) {
                        int indexOfLetter = ENGLISH_ALPHABET_UPPER.indexOf(textToChar[i]);
                        encryptText.append(encryptedAlphabets.get(ENGLISH_ALPHABET_UPPER).charAt(indexOfLetter));
                    } else if (Character.isLowerCase(textToChar[i])) {
                        int indexOfLetter = ENGLISH_ALPHABET_LOWER.indexOf(textToChar[i]);
                        encryptText.append(encryptedAlphabets.get(ENGLISH_ALPHABET_LOWER).charAt(indexOfLetter));
                    } else {
                        encryptText.append(textToChar[i]);
                    }
                    break;
                case "UKR":
                    if (Character.isUpperCase(textToChar[i])) {
                        int indexOfLetter = UKRAINIAN_ALPHABET_UPPER.indexOf(textToChar[i]);
                        encryptText.append(encryptedAlphabets.get(UKRAINIAN_ALPHABET_UPPER).charAt(indexOfLetter));
                    } else if (Character.isLowerCase(textToChar[i])) {
                        int indexOfLetter = UKRAINIAN_ALPHABET_LOWER.indexOf(textToChar[i]);
                        encryptText.append(encryptedAlphabets.get(UKRAINIAN_ALPHABET_LOWER).charAt(indexOfLetter));
                    } else {
                        encryptText.append(textToChar[i]);
                    }
                    break;
            }
        }
        return encryptText.toString();
    }

    /**
     * Decrypts the given text that was encrypted using the CaesarCipher cipher algorithm with the given key.
     *
     * @param text the text to decrypt
     * @param key  the key that was used for encryption
     * @return the decrypted text
     */
    public String Decrypt(String text, int key) {
        Map<String, String> decryptedAlphabets = new HashMap<>();
        decryptedAlphabets.put(ENGLISH_ALPHABET_UPPER, getDecryptedAlphabet(ENGLISH_ALPHABET_UPPER, key));
        decryptedAlphabets.put(ENGLISH_ALPHABET_LOWER, getDecryptedAlphabet(ENGLISH_ALPHABET_LOWER, key));
        decryptedAlphabets.put(UKRAINIAN_ALPHABET_UPPER, getDecryptedAlphabet(UKRAINIAN_ALPHABET_UPPER, key));
        decryptedAlphabets.put(UKRAINIAN_ALPHABET_LOWER, getDecryptedAlphabet(UKRAINIAN_ALPHABET_LOWER, key));

        char[] textToChar = text.toCharArray();
        StringBuilder decryptText = new StringBuilder();
        for (int i = 0; i < textToChar.length; i++) {
            String language = LanguageDetector.detectLanguage(String.valueOf(textToChar[i]));
            switch (language) {
                case "ENG":
                    if (Character.isUpperCase(textToChar[i])) {
                        int indexOfLetter = ENGLISH_ALPHABET_UPPER.indexOf(textToChar[i]);
                        decryptText.append(decryptedAlphabets.get(ENGLISH_ALPHABET_UPPER).charAt(indexOfLetter));
                    } else if (Character.isLowerCase(textToChar[i])) {
                        int indexOfLetter = ENGLISH_ALPHABET_LOWER.indexOf(textToChar[i]);
                        decryptText.append(decryptedAlphabets.get(ENGLISH_ALPHABET_LOWER).charAt(indexOfLetter));
                    } else {
                        decryptText.append(textToChar[i]);
                    }
                    break;
                case "UKR":
                    if (Character.isUpperCase(textToChar[i])) {
                        int indexOfLetter = UKRAINIAN_ALPHABET_UPPER.indexOf(textToChar[i]);
                        decryptText.append(decryptedAlphabets.get(UKRAINIAN_ALPHABET_UPPER).charAt(indexOfLetter));
                    } else if (Character.isLowerCase(textToChar[i])) {
                        int indexOfLetter = UKRAINIAN_ALPHABET_LOWER.indexOf(textToChar[i]);
                        decryptText.append(decryptedAlphabets.get(UKRAINIAN_ALPHABET_LOWER).charAt(indexOfLetter));
                    } else {
                        decryptText.append(textToChar[i]);
                    }
                    break;
            }
        }
        return decryptText.toString();
    }

    private String getEncryptedAlphabet(String alphabet, int key) {
        return alphabet.substring(key) + alphabet.substring(0, key);
    }

    private String getDecryptedAlphabet(String alphabet, int key) {
        return alphabet.substring(alphabet.length() - key) + alphabet.substring(0, alphabet.length() - key);
    }

    /**
     * Decrypts a given encrypted text using the CaesarCipher cipher with frequencies.
     * The method detects the language of the encrypted text and uses the corresponding
     * language frequencies to determine the best key for decryption.
     *
     * @param encryptedText The encrypted text to be decrypted
     * @return The decrypted text
     */
    public String decryptCaesarWithFrequencies(String encryptedText) {
        // Define English and Ukrainian letter frequencies
        double[] englishFrequencies = {0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015, 0.06094, 0.06966,
                0.00153, 0.00772, 0.04025, 0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987, 0.06327, 0.09056,
                0.02758, 0.00978, 0.02360, 0.00150, 0.01974, 0.00074};
        double[] ukrainianFrequencies = {0.072, 0.017, 0.052, 0.016, 0.016, 0.035, 0.017, 0.008, 0.009, 0.023, 0.061,
                0.057, 0.006, 0.008, 0.035, 0.036, 0.031, 0.065, 0.094, 0.029, 0.047, 0.041, 0.055, 0.04, 0.001, 0.012,
                0.006, 0.018, 0.012, 0.001, 0.029, 0.004, 0.029};
        int[] encryptedFrequencies;
        double[] languageFrequencies;
        String languageAlphabet;

        // Detect the language of the encrypted text
        String detectedLanguage = LanguageDetector.detectLanguage(encryptedText);

        // Set the corresponding language frequencies and alphabet
        if (detectedLanguage.equals("ENG")) {
            encryptedFrequencies = new int[ENGLISH_ALPHABET_LOWER.length()];
            languageFrequencies = englishFrequencies;
            languageAlphabet = ENGLISH_ALPHABET_LOWER;
        } else {
            encryptedFrequencies = new int[UKRAINIAN_ALPHABET_LOWER.length()];
            languageFrequencies = ukrainianFrequencies;
            languageAlphabet = UKRAINIAN_ALPHABET_LOWER;
        }

        // Count the frequencies of each letter in the encrypted text
        for (int i = 0; i < encryptedText.length(); i++) {
            char c = encryptedText.charAt(i);
            if (Character.isLetter(c)) {
                int index = Arrays.binarySearch(languageAlphabet.toCharArray(), Character.toLowerCase(c));
                if (index >= 0) {
                    encryptedFrequencies[index]++;
                }
            }
        }

        // Find the best key for decryption by minimizing the difference between
        // the language frequencies and the encrypted text frequencies
        double minDifference = Double.MAX_VALUE;
        for (int key = 1; key <= languageAlphabet.length(); key++) {
            double difference = computeDifference(languageFrequencies,
                    encryptedFrequencies,
                    key,
                    encryptedText.length(),
                    languageAlphabet.length());
            if (difference < minDifference) {
                minDifference = difference;
                bestKey = key;
            }
        }

        // Decrypt the text using the best key
        return Decrypt(encryptedText, bestKey);
    }

    /**
     * Computes the difference between the given language frequencies and
     * the shifted encrypted text frequencies for a given key.
     *
     * @param languageFrequencies  The language letter frequencies
     * @param encryptedFrequencies The encrypted text letter frequencies
     * @param key                  The key used to shift the encrypted text frequencies
     * @param textLength           The length of the encrypted text
     * @param alphabetLength       The length of the alphabet used in the encryption
     * @return The computed difference between the language and shifted encrypted text frequencies
     */
    private double computeDifference(double[] languageFrequencies,
                                     int[] encryptedFrequencies,
                                     int key,
                                     int textLength,
                                     int alphabetLength) {
        double difference = 0;
        for (int i = 1; i <= alphabetLength; i++) {
            difference += Math.abs(languageFrequencies[i - 1] -
                    (double) encryptedFrequencies[(i + key - 1) % alphabetLength] / textLength);
        }
        return difference;
    }
}