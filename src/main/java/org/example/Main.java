package org.example;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String path = args[0];
        FileReader fileReader = new FileReader();
        Divider divider = new Divider();
        List<String> list = fileReader.read(path);
        List<List<String>> lists = divider.divide(list);

        List<String> arr1 = lists.get(0);
        List<String> arr2 = lists.get(1);

        int firstArrayLength = 0;
        int lastArrayLength = 0;
        try {
            firstArrayLength = Integer.parseInt(list.get(0));
            for (int i = 1, listSize = firstArrayLength; i < listSize + 1; i++) {
                arr1.add(list.get(i));
            }
            lastArrayLength = Integer.parseInt(list.get(firstArrayLength + 1));
            for (int i = firstArrayLength + 2, listSize = list.size(); i < listSize; i++) {
                arr2.add(list.get(i));
            }
            if(!(arr1.size() == firstArrayLength && arr2.size() == lastArrayLength)){
                System.out.println("Something goes wrong, please check the content of the file and try again");
                throw new ListDividerException("There is mismatch of lengths from file with sizes of result arrays");
            }
        } catch (NumberFormatException | ListDividerException e) {
            e.printStackTrace();
        }

        Map<String, String> map = new LinkedHashMap<>();
        for (String s1: arr1) {
            String arr2result = arr2.stream()
                    .filter(s -> StringUtils.containsIgnoreCase(s, s1)
                            ||StringUtils.containsIgnoreCase(s1, s)
                    || isIntersected(s, s1))
                    .findAny()
                    .orElse("?");
            map.put(s1, arr2result);
        }
        System.out.println(map);

    }
    private static boolean isIntersected(String s1, String s2){
        HashSet<Character> h1 = new HashSet<>(), h2 = new HashSet<>();
        for(int i = 0; i < s1.length(); i++)
        {
            h1.add(s1.charAt(i));
        }
        for(int i = 0; i < s2.length(); i++)
        {
            h2.add(s2.charAt(i));
        }
        h1.retainAll(h2);
        return h1.size() > 5;
    }

}
