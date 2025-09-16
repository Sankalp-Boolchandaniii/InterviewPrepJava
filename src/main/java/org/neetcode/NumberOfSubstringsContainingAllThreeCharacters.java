package org.neetcode;

public class NumberOfSubstringsContainingAllThreeCharacters {

    public static int numberOfSubstrings(String s) {
        int count=0;

        char[] charArray = s.toCharArray();
        int len=charArray.length;
        int[] hash= {-1, -1, -1};
        for (int i=0; i<len; i++){
            hash[charArray[i]-'a']=i;
            count=count+(1+Math.min(Math.min(hash[0], hash[1]), hash[2]));
        }

        return count;
    }

}
