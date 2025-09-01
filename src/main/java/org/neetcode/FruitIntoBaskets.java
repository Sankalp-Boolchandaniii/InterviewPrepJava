package org.neetcode;

import org.utils.SlidingWindow;
import org.utils.TwoPointer;

import java.util.HashMap;
import java.util.Map;

@TwoPointer
@SlidingWindow
public class FruitIntoBaskets {

    public int totalFruit(int[] fruits){
        int maxlen=0, l=0, r=0;
        Map<Integer, Integer> map=new HashMap<>();
        while (r<fruits.length) {
            map.put(fruits[r], map.getOrDefault(fruits[r], 0) + 1);

            while (map.size() > 2) {
                map.put(fruits[l], map.get(fruits[l]) - 1);
                if (map.get(fruits[l]) == 0)
                    map.remove(fruits[l]);
                l++;
            }

            if (map.size() <= 2) {
                maxlen = Math.max(r - l + 1, maxlen);
            }
            r++;
        }
        return maxlen;
    }


    public int totalFruitBetter(int[] fruits) {
        int maxlen=0, l=0, r=0;
        Map<Integer, Integer> map=new HashMap<>();
        while(r<fruits.length){
            map.put(fruits[r], map.getOrDefault(fruits[r], 0)+1);

            if (map.size()>2){
                map.put(fruits[l], map.get(fruits[l])-1);
                if (map.get(fruits[l])==0){
                    map.remove(fruits[l]);
                }
                l++;
            }

            if (map.size()<=2){
                maxlen=Math.max(r-l+1, maxlen);
            }

            r++;
        }
        return maxlen;
    }

}
