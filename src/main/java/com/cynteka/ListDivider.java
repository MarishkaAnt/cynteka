package com.cynteka;

import com.cynteka.exceptions.ListDividerException;

import java.util.ArrayList;
import java.util.List;

/** @author MARIIA
 * This is custom divider that divide list of structure like this:
 *      n - number
 *      then n lines
 *      m - number
 *      then m lines
 * to two lists with n strings and m strings.
 *
 * Example input:
 * 1
 * Бетон с присадкой
 * 2
 * присадка бля бетона
 * доставка
 *
 * Example output:
 * list1 = {Бетон с присадкой}
 * list2 = {присадка бля бетона, доставка}
 *
 */
public class ListDivider {

    public static List<List<String>> divide(List<String> list) {
        List<List<String>> lists = new ArrayList<>();
        List<String> arr1 = new ArrayList<>();
        List<String> arr2 = new ArrayList<>();
        int firstArrayLength;
        int lastArrayLength;
        try {
            firstArrayLength = Integer.parseInt(list.get(0));
            for (int i = 1, listSize = firstArrayLength; i < listSize + 1; i++) {
                arr1.add(list.get(i));
            }
            lastArrayLength = Integer.parseInt(list.get(firstArrayLength + 1));
            for (int i = firstArrayLength + 2, listSize = list.size(); i < listSize; i++) {
                arr2.add(list.get(i));
            }
            if (!(arr1.size() == firstArrayLength && arr2.size() == lastArrayLength)) {
                throw new ListDividerException("There is mismatch of lengths from file with sizes of result arrays");
            }
            lists.add(arr1);
            lists.add(arr2);
        } catch (NumberFormatException | ListDividerException e) {
            e.printStackTrace();
        }
        return lists;
    }
}
