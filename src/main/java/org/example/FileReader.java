package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public List<String> read(String path){
        List<String> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if(nextLine != null && !nextLine.isBlank()){
                    list.add(nextLine);
                }
            }
            scanner.close();
            System.out.println("The file contents lines: " + list);
        } catch (FileNotFoundException e) {
            throw  new FileReaderException(e, "File not found or broken");
        }
        return list;
    }

}
