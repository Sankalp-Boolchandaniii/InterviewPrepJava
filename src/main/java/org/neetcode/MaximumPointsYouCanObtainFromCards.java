package org.neetcode;

public class MaximumPointsYouCanObtainFromCards {

    public static int maxScore(int[] cardPoints, int k) {
        int n=cardPoints.length;
        int currSum=0;
        for (int i=0;i<k;i++){
            currSum+=cardPoints[i];
        }
        int maxSum=currSum>0?currSum:0;
        int l=k-1;
        int r=n-1;
        for (int i=0; i<k;i++){
            currSum=currSum-cardPoints[l]+cardPoints[r];
            maxSum= Math.max(maxSum, currSum);
            l--;
            r--;
        }
        return maxSum;
    }

}
