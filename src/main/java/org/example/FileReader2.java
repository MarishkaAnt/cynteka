package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class FileReader2 {

    public List<String> read(String path){
        List<String> lines = new ArrayList<>();
        try {
            lines = readAllLines(Path.of(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
