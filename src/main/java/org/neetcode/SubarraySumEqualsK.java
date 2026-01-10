package org.neetcode;

public class SubarraySumEqualsK {

    public int subarraySumBrute(int[] nums, int k) {
        int count=0;
        for (int i=0;i<nums.length;i++){
            int currSum=nums[i];
            if (currSum==k){
                count++;
            }
            for (int j=i+1;j<nums.length;j++){
                currSum+=nums[j];
                if (currSum==k){
                    count++;
                }
            }
        }
        return count;
    }


}
