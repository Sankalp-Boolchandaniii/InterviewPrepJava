package org.neetcode;

import org.utils.TwoPointer;

// to be done in O(1) space
@TwoPointer
public class TwoSumIntegerII {
    public int[] twoSum(int[] nums, int target) {
        int l=0, r=nums.length-1;
        while(l<r){
            int currSum=nums[l]+nums[r];
            if (currSum==target){
                return new int[]{l+1, r+1};
            }
            else if (currSum<target)l++;
            else if (currSum>target)r--;
        }
        return new int[]{};
    }
}

/*
- array is sorted
- set 2 pointers at opposite ends
- sum the elements at pointer indexes, compare with the target
- if target is bigger, move left pointer
- if target is smaller, move right pointer
 */