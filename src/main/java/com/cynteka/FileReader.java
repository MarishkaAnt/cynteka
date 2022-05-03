package com.cynteka;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;

public class FileReader {

    public static List<String> read(String path) throws IOException {
        return readAllLines(Path.of(path), StandardCharsets.UTF_8).stream()
                .filter(s -> !s.isBlank())
                .collect(Collectors.toList());
    }

}
