package ee.taltech.iti0202.files;

import ee.taltech.iti0202.files.input.FileReaderException;
import ee.taltech.iti0202.files.input.InputFilesBufferReader;
import ee.taltech.iti0202.files.input.InputFilesLines;
import ee.taltech.iti0202.files.input.InputFilesScanner;
import ee.taltech.iti0202.files.morse.MorseTranslator;
import ee.taltech.iti0202.files.output.OutputFilesWriter;
import org.junit.jupiter.api.Assertions;
import java.util.List;
import java.util.Map;

class FilesReaderWriterTests {
    final int FIFTYFOUR = 54;
    @org.junit.jupiter.api.Test
    void testBufferReadingFromFile() {
        InputFilesBufferReader bufferReader = new InputFilesBufferReader();
        String exceptionMessage = "";
        Assertions.assertEquals(bufferReader.readTextFromFile("src/ee/taltech/iti0202/files/files/morse.txt")
                .size(), FIFTYFOUR);

        try {
            List<String> readingResult = bufferReader.
                    readTextFromFile("src/ee/taltech/iti0202/files/files/notthatmorse.txt");
        } catch (FileReaderException e) {
            exceptionMessage = e.getMessage();
        }
        Assertions.assertEquals("No such file", exceptionMessage);
    }
    @org.junit.jupiter.api.Test
    void testScannerReadingFromFile() {
        InputFilesScanner scanner = new InputFilesScanner();
        String exceptionMessage = "";
        Assertions.assertEquals(scanner
                .readTextFromFile("src/ee/taltech/iti0202/files/files/morse.txt").size(), FIFTYFOUR);

        try {
            List<String> readingResult = scanner
                    .readTextFromFile("src/ee/taltech/iti0202/files/files/notthatmorse.txt");
        } catch (FileReaderException e) {
            exceptionMessage = e.getMessage();
        }
        Assertions.assertEquals("No such file", exceptionMessage);
    }
    @org.junit.jupiter.api.Test
    void testLinesReaderFromFile() {
        InputFilesLines linesReader = new InputFilesLines();
        String exceptionMessage = "";
        Assertions.assertEquals(linesReader
                .readTextFromFile("src/ee/taltech/iti0202/files/files/morse.txt").size(), FIFTYFOUR);

        try {
            List<String> readingResult = linesReader
                    .readTextFromFile("src/ee/taltech/iti0202/files/files/notthatmorse.txt");
        } catch (FileReaderException e) {
            exceptionMessage = e.toString();
        }
        Assertions.assertEquals("No such file", exceptionMessage);
    }
}

class MorseTranslationTests {
    public int FIFTYFOUR= 54;
    @org.junit.jupiter.api.Test
    void testMorseMap() {
        InputFilesBufferReader bufferReader = new InputFilesBufferReader();
        MorseTranslator morseTranslator = new MorseTranslator();

        List<String> readingResult = bufferReader
                .readTextFromFile("src/ee/taltech/iti0202/files/files/morse.txt");
        Map<String, String> charMorse = morseTranslator.addMorseCodes(readingResult);

        Assertions.assertEquals(charMorse.size(), FIFTYFOUR);
        Assertions.assertEquals(charMorse.get("e"), ".");
    }
    @org.junit.jupiter.api.Test
    void testTranslatingToMorse() {
        InputFilesBufferReader bufferReader = new InputFilesBufferReader();
        List<String> morseLines = bufferReader
                .readTextFromFile("src/ee/taltech/iti0202/files/files/morse.txt");

        MorseTranslator morseTranslator = new MorseTranslator();
        Map<String, String> charMorse = morseTranslator.addMorseCodes(morseLines);

        String morse = ".-.. --- .-. . --\t.. .--. ... ..- --\t-.. --- .-.. --- .-.\t... .. -\t" +
                ".- -- . - --..--\t-.-. --- -. ... . -.-. - . - ..- .-.\t" +
                ".- -.. .. .--. .. ... -.-. .. -. --.\t. .-.. .. - --..--";
        List<String> testLine = bufferReader.readTextFromFile("src/ee/taltech/iti0202/files/files/test.txt");

        Assertions.assertEquals(morseTranslator.translateLinesToMorse(testLine).get(0), morse);
    }
    @org.junit.jupiter.api.Test
    void testTranslatingFromMorse() {
        InputFilesBufferReader bufferReader = new InputFilesBufferReader();
        List<String> morseLines = bufferReader.readTextFromFile("src/ee/taltech/iti0202/files/files/morse.txt");

        MorseTranslator morseTranslator = new MorseTranslator();
        Map<String, String> charMorse = morseTranslator.addMorseCodes(morseLines);

        String fromMorse = "lorem ipsum dolor sit amet, consectetur adipiscing elit,";
        List<String> testLine = morseTranslator.translateLinesToMorse(bufferReader
                .readTextFromFile("src/ee/taltech/iti0202/files/files/test.txt"));

        Assertions.assertEquals(morseTranslator.translateLinesFromMorse(testLine).get(0), fromMorse);
    }
}
class OutputFilesTest {
    @org.junit.jupiter.api.Test
    void testOutputFileWriter() {
        List<String> lines = List.of("testing", "testing", "123");

        OutputFilesWriter outputFilesWriter = new OutputFilesWriter();
        outputFilesWriter.writeLinesToFile(lines, "src/ee/taltech/iti0202/files/files/output.txt");

        InputFilesBufferReader bufferReader = new InputFilesBufferReader();
        List<String> writtenLines  = bufferReader
                .readTextFromFile("src/ee/taltech/iti0202/files/files/output.txt");

        Assertions.assertEquals(writtenLines, lines);

    }

}
