package com.cynteka;

import org.slf4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            log.info("Empty or wrong size of args array");
        } else {
            String inputPath = args[0];
            String outputPath = args[1];
            try {
                List<String> list = TxtFileReader.readNotBlankLinesFromFile(inputPath);
                Map<String, String> similarStrings = SimilarStringsMatcher.findSimilarStrings(list);
                TxtFileWriter.writeToTxTFile(outputPath, similarStrings);
                log.info("Success");

            } catch (IOException e) {
                log.error("Something goes wrong: ", e);
            }
            log.info("Finish program executing");
        }
    }
}
