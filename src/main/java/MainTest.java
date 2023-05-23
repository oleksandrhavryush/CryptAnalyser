import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final String ENCRYPT_COMMAND = "ENCRYPT";
    private final String DECRYPT_COMMAND = "DECRYPT";
    private final String BF_COMMAND = "BRUTE_FORCE";

    private final boolean UKRAINIAN_LANGUAGE_TEST = true;

    private Path inputFilePath_EN;
    private Path inputFilePath_UA;
    @TempDir
    private Path tempDir;

    private final String hamlet_EN = """
            THE TRAGEDY OF HAMLET, PRINCE OF DENMARK


            by William Shakespeare



            Dramatis Personae

              Claudius, King of Denmark.
              Marcellus, Officer.
              Hamlet, son to the former, and nephew to the present king.
              Polonius, Lord Chamberlain.
              Horatio, friend to Hamlet.
              Laertes, son to Polonius.
              Voltemand, courtier.
              Cornelius, courtier.
              Rosencrantz, courtier.
              Guildenstern, courtier.
              Osric, courtier.
              A Gentleman, courtier.
              A Priest.
              Marcellus, officer.
              Bernardo, officer.
              Francisco, a soldier
              Reynaldo, servant to Polonius.
              Players.
              Two Clowns, gravediggers.
              Fortinbras, Prince of Norway. \s
              A Norwegian Captain.
              English Ambassadors.

              Getrude, Queen of Denmark, mother to Hamlet.
              Ophelia, daughter to Polonius.

              Ghost of Hamlet's Father.

              Lords, ladies, Officers, Soldiers, Sailors, Messengers, Attendants.""";
    private final String orwell_UA = """
            Стояла ясна та прохолодна квітнева днина, на годинниках пробило тринадцяту годину.
            Вінстон Сміт, притискуючи підборіддя до грудей щоб сховатися від підступного вітру,
            швидко ковзнув крізь скляні двері великого панельного будинку що звався "Перемога",
            але не достатньо швидко щоб завадити вихру з піску та пилюки увійти разом з ним.
                        
            У вестибюлі тхнуло вареною капустою та старими драними килимками. В одному з його
            кінців був кольоровий плакат, завеликий щоб розташувати його всередині квартири,
            прибитий кнопками до стіни. На ньому було зображено лише величезне обличчя,
            більш ніж метр завширшки : обличчя чоловіка приблизно сорока п'яти років,
            з масивними чорними вусами та привабливо суворими та прямими рисами обличчя. 
            Вінстон пішов сходами. Не було сенсу намагатися піднятися ліфтом. Навіть у 
            найкращі часи він працював лише зрідка, а відтепер черговий електрик вимикав 
            його взагалі під час світлого часу доби. Це була частина політики заощадження 
            під час приготування до Тижня Ненависті. Квартира знаходилася на сьомому поверсі,
             і Вінстон, який мав тридцять дев'ять років та варикозну виразку на правій 
             щиколотці, йшов дуже повільно, відпочиваючи по декілька разів під час сходження. 
             На кожному поверсі, навпроти ліфтової шахти, зі стіни пильно дивився плакат з 
             величезним обличчям. Це було одне з тих зображень,які створені так щоб очі 
             невідривно слідкували за тобою куди б ти не пішов. СТАРШИЙ БРАТ НАГЛЯДАЄ ЗА ТОБОЮ, 
             промовляв напис під зображенням.
                        
            Всередині квартири солодкий та принадний голос диктував перелік цифр, що якось 
            стосувалися виробництва чушкового чавуну. Цей голос лунав з видовженої металевої 
            тарелі, що нагадувала поблякле дзеркало, та була вмонтована в поверхню стіни праворуч. 
            Вінстон повернув вимикача і голос дещо вщух, одначе слова що лунали ще можна було 
            розібрати. Цей прилад (який звався телезахист) можна було приглушити, але не було 
            жодного способу вимкнути його повністю. Він підійшов до вікна : маленька, квола 
            фігурка, його худорлявого тіла лише підкреслювалась блакитним спецодягом, 
            що був уніформою його партії. Його волосся було яскраво світлим, його обличчя було
            природньо рум'яним та життєрадісним, його шкіра була огрубілою від господарчого 
            мила та тупого леза бритви, та вкрите крижаною маскою зими яка щойно скінчилася.
            """;


    @BeforeEach
    public void setUp() throws IOException {

        try {
            inputFilePath_EN = Files.createFile(tempDir.resolve("EN_Text.txt"));
            inputFilePath_UA = Files.createFile(tempDir.resolve("UA_Text.txt"));
        } catch (InvalidPathException ipe) {
            System.err.println("error creating temporary test file in " + this.getClass().getSimpleName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Files.writeString(inputFilePath_EN, hamlet_EN);
        Files.writeString(inputFilePath_UA, orwell_UA);
    }


    // FILE TESTS
    @Nested
    @DisplayName("File tests")
    class FileTests {

        @Nested
        @DisplayName("ENCRYPT")
        class EncryptFileTests {
            @Test
            @DisplayName("File should be created")
            void encryptFileCreatingTest() {
                Path encryptedFile = runEncrypt(inputFilePath_EN, 5);

                assertNotNull(encryptedFile, "Encrypted file was not created");
            }

            @Test
            @DisplayName("File should have a marker '[ENCRYPTED]'")
            void encryptFileMarkerTest() {
                Path encryptedFile = runEncrypt(inputFilePath_EN, 5);

                assertNotNull(encryptedFile, "Encrypted file was not created");
                assertTrue(encryptedFile.getFileName().toString().contains("[ENCRYPTED]"), "Encrypted file doesn't have '[ENCRYPTED]' marker. \n" + "File name: " + encryptedFile.getFileName());
            }
        }

        @Nested
        @DisplayName("DECRYPT")
        class decryptFileTests {
            @Test
            @DisplayName("File should be created")
            void decryptedFileCreatingTest() {
                Path encryptedFilePath = runEncrypt(inputFilePath_EN, 5);
                Path decryptedFilePath = runDecrypt(encryptedFilePath, 5);

                assertNotNull(decryptedFilePath, "Encrypted file was not created");
            }

            @Test
            @DisplayName("File should have a marker '[DECRYPTED]'")
            void decryptedFileMarkerTest() {
                Path encryptedFilePath = runEncrypt(inputFilePath_EN, 5);
                Path decryptedFilePath = runDecrypt(encryptedFilePath, 5);

                assertTrue(decryptedFilePath.getFileName().toString().contains("[DECRYPTED]"), "Decrypted file doesn't have '[DECRYPTED]' marker. \n" + "File name: " + decryptedFilePath.getFileName());
            }
        }
    }

    @Nested
    @DisplayName("English language tests")
    class English {

        @DisplayName("[ENCRYPT] Simple letters encoding")
        @ParameterizedTest
        @CsvSource({"A, 1, B", "a, 1, b", "A, 25, Z", "a, 25, z",})
        void encrypt(String input, int key, String expected) throws IOException {
            Path testFilePath = Files.createFile(tempDir.resolve("testFile.txt"));
            Files.writeString(testFilePath, input);

            Path encryptedFilePath = runEncrypt(testFilePath, key);

            String encryptedText = readFile(encryptedFilePath);
            assertEquals(expected, encryptedText);
        }

        @Test
        @DisplayName("[DECRYPT] Decrypted text should be equal to the original.")
        void decryptedFileTextValidate() {
            Path encryptedFilePath = runEncrypt(inputFilePath_EN, 5);
            Path decryptedFilePath = runDecrypt(encryptedFilePath, 5);

            String decryptedText = readFile(decryptedFilePath);
            assertEquals(hamlet_EN, decryptedText, "Decrypted text is not the same as original");
        }

        @Test()
        @DisplayName("[BRUTE_FORCE] Decrypted text should be equal to the original.")
        void bruteForceEN() {
            Path encryptedFilePath = runEncrypt(inputFilePath_EN, 5);
            Path bruteForcedFilePath = runBruteForce(encryptedFilePath);

            assertNotNull(bruteForcedFilePath, "File force was not created after brute force\"");

            String bruteForcedText = readFile(bruteForcedFilePath);

            assertTrue(hamlet_EN.equalsIgnoreCase(bruteForcedText), "Decrypted text is not the same as original even in ignore case");
            assertEquals(hamlet_EN, bruteForcedText, "Decrypted text is not the same");
        }
    }

    @Nested
    @DisplayName("Ukrainian Language Tests")
    class UkrainianLanguageTest {


        @DisplayName("[ENCRYPT] Simple letters encoding")
        @ParameterizedTest
        @CsvSource({"А, 1, Б",
                "а, 1, б",
                "А, 32, Я",
                "а, 32, я",})
        void encrypt(String input, int key, String expected) throws IOException {
            Assumptions.assumeTrue(UKRAINIAN_LANGUAGE_TEST);
            Path testFilePath = Files.createFile(tempDir.resolve("testFile.txt"));
            Files.writeString(testFilePath, input);

            Path encryptedFilePath = runEncrypt(testFilePath, key);

            String encryptedText = readFile(encryptedFilePath);
            assertEquals(expected, encryptedText);
        }

        @Test
        @DisplayName("[DECRYPT] Decrypted text should be equal to the original.")
        void decryptFileCreatingTest_UA() {
            Assumptions.assumeTrue(UKRAINIAN_LANGUAGE_TEST);

            Path encryptedFilePath = runEncrypt(inputFilePath_UA, 5);
            Path decryptedFilePath = runDecrypt(encryptedFilePath, 5);
            String decryptedText = readFile(decryptedFilePath);

            assertNotNull(decryptedFilePath, "Encrypted file was not created");
            assertEquals(orwell_UA, decryptedText, "Decrypted text is not the same as original");
        }

    }

    @Nested
    @DisplayName("Validation")
    class validations{
        @DisplayName("Negative key should be validated (Better way)")
        @ParameterizedTest
        @CsvSource({"A, -1, B",
                "a, -1, b",
                "A, -25, Z",
                "a, -25, z",
        })
        void negativeKeyEncryption(String input, int key, String expected) throws IOException {
            Path testFilePath = Files.createFile(tempDir.resolve("testFile.txt"));
            Files.writeString(testFilePath, input);

            Path encryptedFilePath = runEncrypt(testFilePath, key);

            String encryptedText = readFile(encryptedFilePath);
            assertEquals(expected, encryptedText);
        }
        @DisplayName("Negative key should be validated ")
        @ParameterizedTest
        @CsvSource({"A, -1, Z",
                "a, -1, z",
                "Z, -25, A",
                "z, -25, A",
        })
        @Disabled
        void negativeKeyEncryption2(String input, int key, String expected) throws IOException {
            Path testFilePath = Files.createFile(tempDir.resolve("testFile.txt"));
            Files.writeString(testFilePath, input);

            Path encryptedFilePath = runEncrypt(testFilePath, key);

            String encryptedText = readFile(encryptedFilePath);
            assertEquals(expected, encryptedText);
        }

        @Test
        @DisplayName("File not exists exception should be handled")
        void fileNotExists(){
            Path fakeFilePath = Path.of("/fake/path/file.txt");

            assertDoesNotThrow(() -> runEncrypt(fakeFilePath, 5));
        }

    }



    private Path runEncrypt(Path inputFilePath, Integer key) {
        List<Path> filesBeforeEncryption = getFileList(tempDir);

        String[] params = new String[3];
        params[0] = ENCRYPT_COMMAND;
        params[1] = inputFilePath.toString();
        params[2] = key.toString();

        try {
            Main.main(params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return findNewFile(filesBeforeEncryption);
    }

    private Path runDecrypt(Path inputFilePath, Integer key) {
        List<Path> filesBeforeDecryption = getFileList(tempDir);
        String[] params = new String[3];
        params[0] = DECRYPT_COMMAND;
        params[1] = inputFilePath.toString();
        params[2] = key.toString();

        try {
            Main.main(params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return findNewFile(filesBeforeDecryption);
    }

    private Path runBruteForce(Path inputFilePath) {
        List<Path> filesBeforeBruteForce = getFileList(tempDir);
        String[] params = new String[2];
        params[0] = BF_COMMAND;
        params[1] = inputFilePath.toString();

        try {
            Main.main(params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return findNewFile(filesBeforeBruteForce);
    }

    private String readFile(Path inputFilePath) {
        try {
            return Files.readString(inputFilePath);
        } catch (IOException e) {
            return null;
        }
    }

    private List<Path> getFileList(Path tempDir) {
        List<Path> filesList;

        try {
            filesList = Files.list(tempDir).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return filesList;
    }

    private Path findNewFile(List<Path> filesBefore) {
        List<Path> updatedPaths;

        System.out.println(filesBefore);

        try {
            updatedPaths = Files.list(tempDir).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Path file : updatedPaths) {
            if (!filesBefore.contains(file)) {
                return file;
            }
        }
        System.out.println("Null");
        return null;
    }



}