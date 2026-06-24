package org.neetcode;

import org.utils.SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SlidingWindow
public class TwoSum {

    public static int[] twoSum(int[] numbers, int target) {
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

    public static int[] twoSumSet(int[] nums, int target) {
        Set<Integer> set=new HashSet<>();
        for (int i: nums){
            int diff=target-i;
            if (set.contains(diff)){
                return new int[]{i, diff};
            }
            set.add(i);
        }
        return new int[2];
    }

    public static int[] twoSumPrac(int[] nums, int target) {
        Map<Integer, Integer> map=new HashMap<>();
        for (int i=0;i<nums.length;i++){
            int diff=target-nums[i];
            if (map.containsKey(diff)){
                return new int[]{map.get(diff), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[2];
    }
}

/*
the main idea here is to find 2 numbers in a given array that equal to given target.
- create a map that will store elements and their indexes
- we iterate through the entire list
- first we get the complement/difference of every element and find if we have it stored in our map with its index
- if so, we return the indexes
- if not we continue the execution and till it ends
 */