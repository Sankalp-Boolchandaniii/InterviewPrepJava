package org.neetcode;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LongestConsecutiveSequence {

    static public int longestConsecutive(int[] nums) {
        Set<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int longestLen=0;
        for (int j: nums){
            if (!list.contains(j-1)){
                int nextNum=j+1;
                int currentLen=1;
                while (list.contains(nextNum)){
                    nextNum+=1;
                    currentLen+=1;
                }
                longestLen= Math.max(longestLen, currentLen);
            }
        }
        return longestLen;
    }

}
