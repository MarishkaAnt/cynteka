package com.cynteka;

import org.slf4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            log.info("Empty args array");
        } else {
            String inputPath = args[0];
            String outputPath = args[1];

            List<String> list = TxtFileReader.readNotBlankLinesFromFile(inputPath);
            List<List<String>> twoSetsOfStrings = ListDivider.divide(list);
            Map<String, String> map = SimilarStringsMatcher.convert(twoSetsOfStrings);
            TxtFileWriter.writeToFile(outputPath, map);
        }
    }
}
