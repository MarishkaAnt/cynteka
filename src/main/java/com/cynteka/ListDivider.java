package com.cynteka;

import com.cynteka.exceptions.ListDividerException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MARIIA
 * This is custom divider that divide list of structure like this:
 * n - number
 * then n lines
 * m - number
 * then m lines
 * to two lists with n strings and m strings.
 * <p>
 * Example input:
 * 1
 * Бетон с присадкой
 * 2
 * присадка бля бетона
 * доставка
 * <p>
 * Example output:
 * firstList = {Бетон с присадкой}
 * secondList = {присадка бля бетона, доставка}
 */
public class ListDivider {

    public static List<List<String>> divide(List<String> list) {
        List<List<String>> lists = new ArrayList<>();
        List<String> firstList = new ArrayList<>();
        List<String> secondList = new ArrayList<>();
        int firstListSize;
        int secondListSize;

        firstListSize = Integer.parseInt(list.get(0));
        for (int i = 1, listSize = firstListSize; i < listSize + 1; i++) {
            firstList.add(list.get(i));
        }
        secondListSize = Integer.parseInt(list.get(firstListSize + 1));
        for (int i = firstListSize + 2, listSize = list.size(); i < listSize; i++) {
            secondList.add(list.get(i));
        }
        if (!(firstList.size() == firstListSize && secondList.size() == secondListSize)) {
            throw new ListDividerException("There is mismatch of lengths from file with sizes of result arrays");
        }
        lists.add(firstList);
        lists.add(secondList);
        return lists;
    }
}
