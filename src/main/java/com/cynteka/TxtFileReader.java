package com.cynteka;

import org.slf4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;

public class TxtFileReader {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TxtFileReader.class);

    public static List<String> readNotBlankLinesFromFile(String inputFilePath) throws IOException {
        Path path;
        List<String> linesFromInputFile = new ArrayList<>();

        if(!(inputFilePath == null) && !inputFilePath.isBlank()) {
            path = Path.of(inputFilePath);
            linesFromInputFile = readAllLines(path, StandardCharsets.UTF_8).stream()
                    .filter(s -> !s.isBlank())
                    .collect(Collectors.toList());
        }else{
            log.info("Empty path to input file");
        }
        return linesFromInputFile;
    }

}
