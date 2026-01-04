package org.neetcode;

import java.util.Arrays;

public class PermutationInString {
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1Arr = new int[26];
        int[] s2Arr = new int[26];

        // 1. Initialize the first window
        for (int i = 0; i < s1.length(); i++) {
            s1Arr[s1.charAt(i) - 'a']++;
            s2Arr[s2.charAt(i) - 'a']++;
        }

        // 2. Slide the window over s2
        // We start from s1.length() because the first window is already built
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (Arrays.equals(s1Arr, s2Arr)) return true;

            // Slide window:
            // Add new character (at i + s1.length())
            s2Arr[s2.charAt(i + s1.length()) - 'a']++;

            // Remove old character (at i)
            s2Arr[s2.charAt(i) - 'a']--;
        }

        // Check the final window after the loop finishes
        return Arrays.equals(s1Arr, s2Arr);
    }


    public boolean checkInclusionSelf(String s1, String s2) {
        if (s1.length()>s2.length()) return false;
        int[] s1Arr=new int[26];
        int[] s2Arr=new int[26];
        for (int i=0;i<s1.length();i++){
            s1Arr[s1.charAt(i)-'a']++;
            s2Arr[s2.charAt(i)-'a']++;
        }
        if (Arrays.equals(s1Arr, s2Arr)) return true;
        for (int i=0;i<s2.length()-s1.length();i++){
            s2Arr[s2.charAt(i+s1.length())-'a']++;
            s2Arr[s2.charAt(i)-'a']--;
            if (Arrays.equals(s1Arr, s2Arr)) return true;
        }
        return false;
    }
}

/*
the main idea here is, we first create 2 arrays of size 26(since only small case letters are involved).
We increment the indexes of the letters(based on ascii values)
then we compare if the 2 arrays are equal.

- first we run the first window  (size of s1 string) for both arrays with both strings
- check if its same
- if not we shift the window and compare for each window
 */