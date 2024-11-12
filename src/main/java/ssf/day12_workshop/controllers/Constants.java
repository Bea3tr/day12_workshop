package ssf.day12_workshop.controllers;

import java.util.*;

public class Constants {
    
    public static final String ATTR_NAME = "name";
    public static final String ATTR_COUNT = "count";
    public static final String ATTR_LIST = "list";
    public static final String ATTR_VALUES = "values";

    public static int toInt(String str, int defValue) {
        try{
            return Integer.parseInt(str);
        } catch (Exception ex) {
            return defValue;
        }
    }

    public static List<Integer> toList(String csv) {
        return Arrays.asList(csv.split(","))
                .stream()
                .map(str -> Integer.parseInt(str))
                .toList();
    }

    public static List<Integer> generateRandom(int count) {
        List<Integer> nums =  new LinkedList<>();
        for(int i = 0; i < 31; i++) 
            nums.add(i);
        Collections.shuffle(nums);
        return nums.subList(0, count);
    }
}
