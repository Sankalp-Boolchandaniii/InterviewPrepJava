package org.neetcode;

import org.utils.SlidingWindow;
import org.utils.TwoPointer;

@TwoPointer
@SlidingWindow
public class MaxConsecutiveOnesIII {

    // O(2n)
    public static int longestOnes(int[] nums, int k) {
        int maxLen=0, l=0, r=0, zeros=0;
        while (r<nums.length){
            while(zeros>k){
                if (nums[l]==0)
                    zeros--;
                l++;
            }
            if (nums[r]==0)
                zeros++;
            if (zeros<=k)
                maxLen=Math.max(maxLen, r-l+1);
            r++;
        }

        return maxLen;
    }

    // best optimized ---> O(n)
    public static int longestOnesBetter(int[] nums, int k){
        int maxlen=0, l=0, r=0, zeros=0;
        while (r<nums.length){
            if (nums[r]==0)
                zeros+=1;
            if (zeros>k){
                if (nums[l]==0)
                    zeros-=1;
                l+=1;
            }
            if(zeros<=k)
                maxlen=Math.max(maxlen, r-l+1);
            r++;
        }
        return maxlen;
    }

}
