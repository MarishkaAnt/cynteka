package org.example;

import org.example.exceptions.WriterToTxtFile;

import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        String inputPath = args[0];
        String outputPath = args[1];
        FileReader fileReader = new FileReader();
        ListDivider divider = new ListDivider();
        ListToMapConverter converter = new ListToMapConverter();
        WriterToTxtFile writer = new WriterToTxtFile();

        List<String> list = fileReader.read(inputPath);
        List<List<String>> lists = divider.divide(list);
        Map<String, String> map = new HashMap<>(converter.convert(lists)){
            @Override
            public String toString() {
                Iterator<Entry<String,String>> i = entrySet().iterator();
                if (! i.hasNext())
                    return "{}";
                StringBuilder sb = new StringBuilder();
                for (;;) {
                    Entry<String,String> e = i.next();
                    String  key = e.getKey();
                    String value = e.getValue();
                    sb.append(key);
                    sb.append(':');
                    sb.append(value);
                    if (! i.hasNext())
                        return sb.toString();
                    sb.append('\n');
                }
            }
        };
        writer.writeToFile(outputPath, map);
    }
}
