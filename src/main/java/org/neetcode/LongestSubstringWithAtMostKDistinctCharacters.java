package org.neetcode;

import org.utils.SlidingWindow;
import org.utils.TwoPointer;

import java.util.HashMap;
import java.util.Map;

@TwoPointer
@SlidingWindow
public class LongestSubstringWithAtMostKDistinctCharacters {

    // best solution -- O(n)
    public static int kDistinctChar(String s, int k) {
        int maxlen=0, l=0, r=0;
        Map<Character, Integer> map=new HashMap<>();
        while (r<s.length()){
            map.put(s.charAt(r), map.getOrDefault(s.charAt(r), 0)+1);
            if (map.size()>k){
                map.put(s.charAt(l), map.get(s.charAt(l))-1);
                if (map.get(s.charAt(l))==0){
                    map.remove(s.charAt(l));
                }
                l++;
            }
            if (map.size()<=k){
                maxlen=Math.max(maxlen, r-l+1);
            }
            r++;
        }
        return maxlen;
    }

}
