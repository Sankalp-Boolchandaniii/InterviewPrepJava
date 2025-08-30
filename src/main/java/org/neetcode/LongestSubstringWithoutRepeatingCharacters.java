package org.neetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        int maxLen=0;
        int l=0;
        char[] charArray = s.toCharArray();
        int length = charArray.length;
        Set<Character> characterSet=new HashSet<>();
        for (int r=0;r<length;r++){
            while (characterSet.contains(charArray[r])){
                characterSet.remove(charArray[l]);
                l+=1;
            }
            characterSet.add(charArray[r]);
            maxLen=maxLen>(r-l+1)?maxLen:(r-l+1);
        }
        return maxLen;
    }

//    using char array
    public static int lengthOfLongestSubstringBetter(String s){
        int maxLen=0;
        int l=0;
        int[] freq=new int[128];
        int n = s.length();

        for (int r=0;r<n;r++){
            char currentChar = s.charAt(r);
            currentChar++;          // increase the frequency of current char
            while(freq[currentChar]>1){         // check if the current char frequency is greater than 1, i.e. it's repeating
                freq[s.charAt(l)]--;            // if so ----> start shrinking window of characters till all are unique again
                l++;                            // removing the chars at left pointer and move the left pointer ahead
            }
            maxLen=Math.max(freq.length, r-l+1);
        }

        return maxLen;
    }
}
