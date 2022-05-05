package com.cynteka;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.*;

/**
 * @author MARIIA
 * This converter found if two lists has any intersections.
 * It compares arrays on same words and same meanings
 * input: List of two lists with strings
 * output: map with keys of first list strings and values of
 * the same strings founded from second list
 * if same string wasn't found, value = ?
 * Example input:
 * firstList = {Бетон с присадкой}, secondList = {присадка бля бетона, доставка}
 * Example output: map = {присадка бля бетона=Бетон с присадкой, доставка=?}
 */
public class SimilarStringsMatcher {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SimilarStringsMatcher.class);

    private static final Map<String, String> synonyms = Map.of(
            "бетон", "цемент",
            "цемент", "бетон",
            "ведро", "корыто",
            "корыто", "ведро");

    public static Map<String, String> findSimilarStrings(List<String> inputList) {
        int headListSize = 0;
        int tailListSize = 0;
        try {
            headListSize = Integer.parseInt(inputList.get(0));
            tailListSize = Integer.parseInt(inputList.get(headListSize + 1));
        } catch (NumberFormatException e) {
            log.error("There is a mistake in sizes of sets in input.txt ", e);
        }
        if (headListSize > 0 || tailListSize > 0) {
            List<String> firstList = inputList.subList(1, headListSize + 1);
            List<String> secondList = inputList.subList(headListSize + 2, inputList.size());
            if (firstList.size() == headListSize && secondList.size() == tailListSize) {
                Map<String, String> map = new LinkedHashMap<>();
                List<String> outers = new ArrayList<>(secondList);
                for (String string : firstList) {
                    String result = secondList.stream()
                            .filter(s -> StringUtils.containsIgnoreCase(s, string)
                                    || StringUtils.containsIgnoreCase(string, s)
                                    || hasSynonymWords(string, s)
                                    || hasSameSubstringWithThisLength(s, string))
                            .findAny()
                            .orElse("?");
                    map.put(string, result);
                    outers.remove(result);
                }
                for (String s : outers) {
                    map.put(s, "?");
                }
                return map;
            } else {
                log.info("There is mismatch of lengths from file with sizes of result arrays");
                return null;
            }
        } else {
            log.info("The program can't to find similar strings in empty sets");
            return null;
        }
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
