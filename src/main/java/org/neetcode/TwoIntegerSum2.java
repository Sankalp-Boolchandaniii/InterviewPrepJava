package org.neetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoIntegerSum2 {

    public static int[] twoSum(int[] numbers, int target) {
        List<Integer> list = Arrays.stream(numbers).boxed().toList();
        int len=numbers.length;
        for (int i=0;i<len;i++) {
            int diff = target - numbers[i];
            if (numbers[i] == diff) {
                numbers[i] = Integer.MIN_VALUE;
            }
            if (list.contains(diff)) {
                return new int[] { i + 1, list.indexOf(diff) + 1 };
            }
        }
        return new int[] {};
    }


    public static int[] twoSumBetter(int[] numbers, int target) {
        Map<Integer, Integer> map=new HashMap<>();
        for (int i=0; i<numbers.length; i++){
            int diff=target-numbers[i];
            if (map.containsKey(diff)){
                return new int[]{map.get(diff), i};
            }
            map.put(numbers[i], i);
        }
        return new int[]{};
    }
}
