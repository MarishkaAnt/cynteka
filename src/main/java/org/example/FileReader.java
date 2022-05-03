package org.example;

import org.example.exceptions.FileReaderException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** @author MARIIA
 * This reader open txt file from path
 * read file and write all lines to list of strings
 * the method skips all blank lines
 *
 * input: String path
 * output: list of strings
 */
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
