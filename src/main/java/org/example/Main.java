package org.example;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String path = args[0];
        FileReader fileReader = new FileReader();
        ListDivider divider = new ListDivider();
        ListToMapConverter converter = new ListToMapConverter();

        List<String> list = fileReader.read(path);
        List<List<String>> lists = divider.divide(list);
        Map<String, String> map = converter.convert(lists);

    }
}
