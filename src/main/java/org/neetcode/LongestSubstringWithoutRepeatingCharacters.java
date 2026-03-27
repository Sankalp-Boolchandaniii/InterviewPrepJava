package org.neetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Longest Substring Without Repeating Characters
 * 
 * Problem: Given a string s, find the length of the longest substring without repeating characters.
 * 
 * Example:
 * Input: "abcabcbb"
 * Output: 3 (Explanation: The answer is "abc", with the length of 3)
 * 
 * Input: "bbbbb"
 * Output: 1 (Explanation: The answer is "b", with the length of 1)
 * 
 * Input: "pwwkew"
 * Output: 3 (Explanation: The answer is "wke", with the length of 3)
 * 
 * Approach: Sliding Window Algorithm
 * - Maintain a window [l, r] of unique characters
 * - Expand window by moving right pointer
 * - When duplicate found, shrink window from left until all characters are unique
 * - Track maximum window size encountered
 * 
 * Time Complexity: O(n) - each character visited at most twice
 * Space Complexity: O(min(n, m)) where m is character set size (128 for ASCII)
 */

    public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * Implementation 1: HashSet Sliding Window Approach (Recommended)
     * 
     * Algorithm:
     * 1. Use HashSet to store characters in current window
     * 2. Expand window by moving right pointer (r)
     * 3. If character at r already exists in set:
     *    - Remove characters from left until duplicate is removed
     *    - Move left pointer (l) forward
     * 4. Add current character to set and update max length
     * 
     * @param s Input string
     * @return Length of longest substring without repeating characters
     */
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
            maxLen= Math.max(maxLen, (r - l + 1));
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
            currentChar++;                      // increase the frequency of current char
            while(freq[currentChar]>1){         // check if the current char frequency is greater than 1, i.e. it's repeating
                freq[s.charAt(l)]--;            // if so ----> start shrinking window of characters till all are unique again
                l++;                            // removing the chars at left pointer and move the left pointer ahead
            }
            maxLen=Math.max(freq.length, r-l+1);
        }

        return maxLen;
    }
}


class Practice{
    public int lengthOfLongestSubstring(String s) {
        int maxlen=0, l=0, n=s.length();
        Set<Character> set=new HashSet<>();
        for (int r=0; r<n; r++){
            while (set.contains(s.charAt(r))){
                set.remove(s.charAt(l));
                l++;
            }
            set.add(s.charAt(r));
            maxlen=Math.max(r-l+1, maxlen);
        }
        return maxlen;
    }
}
