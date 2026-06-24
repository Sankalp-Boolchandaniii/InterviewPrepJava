package org.neetcode;

public class ValidAnagram {

    static boolean validAnagram(String s1, String s2){
        if (s1.length()==s2.length()){
            int[] res=new int[26];
            for (int i=0; i<s1.length();i++){
                res[s1.charAt(i)-'a']++;
                res[s2.charAt(i)-'a']--;
            }
            for (int i: res){
                if (i!=0) return false;
            }
            return true;
        }
        return false;
    }

}
