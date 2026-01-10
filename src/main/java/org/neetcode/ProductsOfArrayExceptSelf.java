package org.neetcode;

import java.util.Arrays;
import java.util.List;

public class ProductsOfArrayExceptSelf {

    public static int[] productExceptSelf(int[] nums) {
        int len=nums.length;
        int[] resArr=new int[len];
        int zeros=0;
        for (int i=0;i<len;i++){
            int prod=1;
            if (nums[i]==0){
                zeros+=1;
                for (int j=0;j<len;j++){
                    if (!(i==j)){
                        prod*=nums[j];
                    }
                }

            } else {
                for (int j=0;j<len;j++){
                    prod*=nums[j];
                }
                prod=prod/nums[i];
            }
            resArr[i]=prod;
        }
        if (zeros==2){
            for (int i=0;i<len;i++){
                resArr[i]=0;
            }
        }
        return resArr;
    }

    public static int[] productExceptSelfBetter(int[] nums) {
        int len=nums.length;
        int[] resArr=new int[len];
        int zeros=0;
        for (int i:nums){
            if (i==0){
                zeros++;
            }
        }
        if (zeros>1){
            for (int i=0;i<nums.length;i++){
                resArr[i]=0;
            }
        } else if (zeros == 1) {
            int prod=1;
            for (int i=0;i<len;i++){
                if (nums[i]!=0){
                    prod*=nums[i];
                }
            }
            for (int i=0;i<len;i++){
                if (nums[i]==0){
                    resArr[i]=prod;
                } else {
                    resArr[i]=0;
                }
            }
        } else {
            int prod=1;
            for (int i=0;i<len;i++){
                prod*=nums[i];
            }
            for (int i=0;i<len;i++){
                resArr[i]=prod/nums[i];
            }
        }
        return resArr;
    }

}
