package org.example;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**@author MARIIA
 * This converter found if two arrays has any intersections
 * it compares arrays on same words and same meanings
 * input: List of two lists with strings
 * output: map with keys of first list strings and values of
 * founded from second list same strings
 * if same string wasn't found, value = ?
 */
public class ListToMapConverter {

    public Map<String, String> convert( List<List<String>> lists) {
        List<String> arr1 = lists.get(0);
        List<String> arr2 = lists.get(1);
        Map<String, String> map = new LinkedHashMap<>();
        for (String s1 : arr1) {
            String arr2result = arr2.stream()
                    .filter(s -> StringUtils.containsIgnoreCase(s, s1)
                            || StringUtils.containsIgnoreCase(s1, s)
                            || isIntersected(s, s1))
                    .findAny()
                    .orElse("?");
            map.put(s1, arr2result);
        }
        System.out.println(map);
        return map;
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
