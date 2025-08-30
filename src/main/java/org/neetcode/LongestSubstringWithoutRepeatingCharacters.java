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

}
