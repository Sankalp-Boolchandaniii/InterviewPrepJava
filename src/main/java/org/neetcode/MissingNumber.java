package org.neetcode;

import java.util.Arrays;

// to find missing number from an array
public class MissingNumber {

    public static void missingNumber(int[] arr) {
        Arrays.sort(arr);
        int num=arr[0], idx=0;
        while (idx < arr.length){
            if (num!=arr[idx]){
                System.out.println(num);
                break;
            }
            idx++; num++;        }
    }

    public static void missingNumberBetter(int[] arr) {
        int n= arr.length;
        int sum=(n*(n+1))/2;
        int arrSum=0;
        int large=0;
        for (int i:arr){
            arrSum+=i;
            if (i>large) large=i;
        }
        System.out.println(sum-arrSum+large);
    }

}
// main idea here is to calculate the sum and find the missing number based on the difference