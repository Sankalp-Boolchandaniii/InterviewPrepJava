package org.neetcode;

import java.util.Arrays;
import java.util.List;

public class TwoIntegerSum2 {

    public static int[] twoSum(int[] numbers, int target) {
        int[] resArr=new int[2];
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

}
