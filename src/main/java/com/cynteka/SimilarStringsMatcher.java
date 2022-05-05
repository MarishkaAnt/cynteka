package com.cynteka;

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
 * firstList = {Бетон с присадкой}, secondList = {присадка бля бетона, доставка}
 * Example output: map = {присадка бля бетона=Бетон с присадкой, доставка=?}
 */
public class SimilarStringsMatcher {
    private static final Map<String, String> synonyms = Map.of(
            "бетон", "цемент",
            "цемент", "бетон",
            "ведро", "корыто",
            "корыто", "ведро");

    public static Map<String, String> findSimilarStrings(List<String> head, List<String> tail) {
        List<String> firstList = new ArrayList<>(head);
        List<String> secondList = new ArrayList<>(tail);

        Map<String, String> map = new LinkedHashMap<>();
        for (String string : firstList) {
            String result = secondList.stream()
                    .filter(s -> StringUtils.containsIgnoreCase(s, string)
                            || StringUtils.containsIgnoreCase(string, s)
                            || hasSynonymWords(string, s)
                            || hasSameSubstringWithThisLength(s, string))
                    .findAny()
                    .orElse("?");
            map.put(string, result);
            secondList.remove(result);
        }
        for (String s : secondList) {
            map.put(s, "?");
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

    private static boolean hasSynonymWords(String firstString, String secondString) {
        Locale locale = new Locale("ru", "RU");
        boolean res = false;
        List<String> firstStringWords = Arrays.stream(firstString.split(" "))
                .map(s4 -> s4.toLowerCase(locale))
                .toList();
        List<String> secondStringWords = Arrays.stream(secondString.split(" "))
                .map(s4 -> s4.toLowerCase(locale))
                .toList();
        for (String word : firstStringWords) {
            String foundedSynonym = synonyms.get(word);
            if (foundedSynonym != null) {
                res = secondStringWords.contains(foundedSynonym);
            }
        }
        return res;
    }
}
