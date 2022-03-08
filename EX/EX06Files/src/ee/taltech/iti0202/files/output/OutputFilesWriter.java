package ee.taltech.iti0202.files.output;
import ee.taltech.iti0202.files.input.FileReaderException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class OutputFilesWriter {

    public boolean writeLinesToFile(List<String> lines, String filename) {
        Path path = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (String s: lines) {
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}