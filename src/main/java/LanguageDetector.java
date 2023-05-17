import java.util.HashSet;
import java.util.Set;

/**
 * This class provides a method for detecting the language of a given text.
 */
public class LanguageDetector {
    private static final String ENGLISH_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String UKRAINIAN_ALPHABET = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯабвгґдеєжзиіїйклмнопрстуфхцчшщьюя";

    /**
     * This method detects the language of the given text.
     *
     * @param text the text to be analyzed
     * @return a string representing the detected language ("UKR" for Ukrainian, "ENG" for English, or "UNKNOWN" if the language could not be detected)
     */
    public static String detectLanguage(String text) {
        int ukrainianCharsCount = countChars(text, UKRAINIAN_ALPHABET);
        int englishCharsCount = countChars(text, ENGLISH_ALPHABET);

        int maxCount = Math.max(ukrainianCharsCount, englishCharsCount);

        if (maxCount == ukrainianCharsCount) {
            return "UKR";
        } else if (maxCount == englishCharsCount) {
            return "ENG";
        } else {
            return "UNKNOWN";
        }
    }

    /**
     * This method counts the number of characters in the given text that are present in the given alphabet.
     *
     * @param text     the text to be analyzed
     * @param alphabet the alphabet to be used for counting characters
     * @return the number of characters in the text that are present in the alphabet
     */
    private static int countChars(String text, String alphabet) {
        Set<Character> alphabetSet = new HashSet<>();
        for (char c : alphabet.toCharArray()) {
            alphabetSet.add(c);
        }

        int count = 0;
        for (char c : text.toCharArray()) {
            if (alphabetSet.contains(c)) {
                count++;
            }
        }
        return count;
    }
}