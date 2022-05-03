package org.example;

import java.util.ArrayList;
import java.util.List;

public class Divider {

    public List<List<String>> divide(List<String> list){
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
            if(!(arr1.size() == firstArrayLength && arr2.size() == lastArrayLength)){
                System.out.println("Something goes wrong, please check the content of the file and try again");
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
