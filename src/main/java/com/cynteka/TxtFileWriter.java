package com.cynteka;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static java.nio.file.Files.createFile;
import static java.nio.file.Files.writeString;

public class TxtFileWriter {

    public static void writeToTxTFile(String path, Map<String, String> map) throws IOException {
        if (Files.notExists(Path.of(path))) {
            createFile(Path.of(path));
        }
        StringBuilder sb = new StringBuilder();
        map.forEach((key, value) -> {
            sb.append(key);
            sb.append(':');
            sb.append(value);
            sb.append(System.lineSeparator());
            sb.append(System.lineSeparator());
        });
        writeString(Path.of(path), sb);
    }
}
