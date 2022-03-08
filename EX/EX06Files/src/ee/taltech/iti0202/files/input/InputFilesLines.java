package ee.taltech.iti0202.files.input;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class InputFilesLines implements InputFilesReader {

    @Override
    public List<String> readTextFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        Path path = Paths.get(filename);
        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(lines::add);
        } catch (FileReaderException | IOException e) {
            throw new FileReaderException("No such file", e);
        }
        return lines;
    }
}
