package org.example;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author MARIIA
 * This converter found if two lists has any intersections.
 * It compares arrays on same words and same meanings
 * input: List of two lists with strings
 * output: map with keys of first list strings and values of
 * the same strings founded from second list
 * if same string wasn't found, value = ?
 * <p>
 * Example input:
 * list1 = {Бетон с присадкой}, list2 = {присадка бля бетона, доставка}
 * Example output: map = {присадка бля бетона=Бетон с присадкой, доставка=?}
 */
public class ListToMapConverter {
    private static final Map<String, String> synonims = Map.of("бетон", "цемент",
            "цемент", "бетон",
            "ведро", "корыто",
            "корыто", "ведро");

    public Map<String, String> convert(List<List<String>> lists) {
        List<String> arr1;
        List<String> arr2;
        if (lists.get(0).size() >= lists.get(1).size()) {
            arr1 = lists.get(0);
            arr2 = lists.get(1);
        } else {
            arr2 = lists.get(0);
            arr1 = lists.get(1);
        }
        Map<String, String> map = new LinkedHashMap<>();
        for (String s1 : arr1) {
            String arr2result = arr2.stream()
                    .filter(s -> StringUtils.containsIgnoreCase(s, s1)
                            || StringUtils.containsIgnoreCase(s1, s)
                            || hasSynonyms(s1, s)
                            || hasSameSubstringWithThisLength(s, s1))
                    .findAny()
                    .orElse("?");
            map.put(s1, arr2result);
        }
        return map;
    }


    private static boolean hasSameSubstringWithThisLength(String s1, String s2) {
        int length = 7;
        boolean res = false;
        for (int i = 0; i < (s1.length() - length); i++) {
            int end = i + length;
            String substring = s1.substring(i, end);
            res = s2.contains(substring);
            if (res) {
                break;
            }
        }
        return res;
    }

    private static boolean hasSynonyms(String s1, String s2) {
        boolean res = false;
        List<String> strings1 = Arrays.stream(s1.split(" "))
                .map(ListToMapConverter::cyrillicToLowerCase)
                .toList();
        List<String> strings2 = Arrays.stream(s2.split(" "))
                .map(ListToMapConverter::cyrillicToLowerCase)
                .toList();
        for (String s :
                strings1) {
            String s3 = synonims.get(s);
            if (s3 != null) {
                res = strings2.contains(s3);
            }
        }
        return res;
    }

    private static String cyrillicToLowerCase(String str) {
        char[] uppercaseCyrillics = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З',
                'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У',
                'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'};
        char[] lowercaseCyrillics = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
                'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
                'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
        for (int i = 0; i < uppercaseCyrillics.length; i++) {
            str = str.replace(uppercaseCyrillics[i], lowercaseCyrillics[i]);
        }
        return str;
    }
}
