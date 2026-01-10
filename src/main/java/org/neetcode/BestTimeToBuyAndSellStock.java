package org.neetcode;

import org.utils.TwoPointer;

@TwoPointer
public class BestTimeToBuyAndSellStock {

    public static int maxProfit(int[] prices) {
        int maxdif=0, l=0, r=0, n=prices.length;
        while (r<n){
            int diff=prices[r]-prices[l];
            if (diff>0){
                maxdif=Math.max(diff, maxdif);
            } else {
                l=r;
            }
            r++;
        }
        return maxdif;
    }

}
