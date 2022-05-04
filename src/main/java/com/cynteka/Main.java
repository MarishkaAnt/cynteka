package com.cynteka;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class Main {

    public static void main(String[] args) throws IOException {
        if (args == null || args.length < 2) {
            log.info("Empty or wrong size of args array");
        } else {
            String inputPath = args[0];
            String outputPath = args[1];

            List<String> list = TxtFileReader.readNotBlankLinesFromFile(inputPath);
            int headListSize = Integer.parseInt(list.get(0));
            int tailListSize = Integer.parseInt(list.get(headListSize + 1));
            List<String> head = list.subList(1, headListSize + 1);
            List<String> tail = list.subList(headListSize + 2, list.size());
            if (!(head.size() == headListSize && tail.size() == tailListSize)) {
                log.info("There is mismatch of lengths from file with sizes of result arrays");
            }
            Map<String, String> map = SimilarStringsMatcher.convert(head, tail);
            TxtFileWriter.writeToTxTFile(outputPath, map);
        }
    }
}
