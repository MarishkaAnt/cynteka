package org.example;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/** @author MARIIA
 * This converter found if two lists has any intersections.
 * It compares arrays on same words and same meanings
 * input: List of two lists with strings
 * output: map with keys of first list strings and values of
 * the same strings founded from second list
 * if same string wasn't found, value = ?
 *
 * Example input:
 * list1 = {Бетон с присадкой}, list2 = {присадка бля бетона, доставка}
 * Example output: map = {Бетон с присадкой=присадка бля бетона}
 */
public class ListToMapConverter {

    public Map<String, String> convert( List<List<String>> lists) {
        List<String> arr1;
        List<String> arr2;
        if(lists.get(0).size() >= lists.get(1).size()) {
            arr1 = lists.get(0);
            arr2 = lists.get(1);
        } else{
            arr2 = lists.get(0);
            arr1 = lists.get(1);
        }
        Map<String, String> map = new LinkedHashMap<>();
        for (String s1 : arr1) {
            String arr2result = arr2.stream()
                    .filter(s -> StringUtils.containsIgnoreCase(s, s1)
                           || StringUtils.containsIgnoreCase(s1, s)
                            || hasSameSubstringWithThisLength(s, s1)
                    )
                    .findAny()
                    .orElse("?");
            map.put(s1, arr2result);
        }
        System.out.println(map);
        return map;
    }

    private static boolean hasSameSubstringWithThisLength(String s1, String s2){
        int length = 7;
        boolean res = false;
        for (int i = 0; i < (s1.length() - length); i++) {
            int end = i + length;
            String substring = s1.substring(i, end);
            res = s2.contains(substring);
            if(res){
                break;
            }
        }
        return res;
    }
}
