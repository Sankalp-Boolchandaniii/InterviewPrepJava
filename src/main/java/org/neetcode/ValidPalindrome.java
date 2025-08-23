package org.neetcode;

public class ValidPalindrome {

    static boolean isPalindrome(String s){
        String valid="QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
        StringBuilder resString= new StringBuilder();
        for (char i: s.toCharArray()){
            if (valid.contains(String.valueOf(i))){
                resString.append(String.valueOf(i).toLowerCase());
            }
        }
        String revString=new StringBuilder(resString.toString()).reverse().toString();
        char[] charArray = revString.toCharArray();
        char[] charArray1 = resString.toString().toCharArray();
        for (int i=0;i<charArray.length;i++){
            if (charArray1[i]!=charArray[i]){
                return false;
            }
        }
        return true;
    }

}
