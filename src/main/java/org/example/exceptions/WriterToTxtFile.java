package org.example.exceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static java.nio.file.Files.createFile;
import static java.nio.file.Files.writeString;

public class WriterToTxtFile {

    public void writeToFile(String path, Map<String, String> map) throws IOException {
        if(Files.notExists(Path.of(path))) {
            createFile(Path.of(path));
        }
        writeString(Path.of(path), map.toString());
    }
}
