package com.cynteka;

import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        String inputPath = args[0];
        String outputPath = args[1];

        List<String> list = FileReader.read(inputPath);
        List<List<String>> lists = ListDivider.divide(list);
        Map<String, String> map = ListToMapConverter.convert(lists);
        WriterToTxtFile.writeToFile(outputPath, map);
    }
}
