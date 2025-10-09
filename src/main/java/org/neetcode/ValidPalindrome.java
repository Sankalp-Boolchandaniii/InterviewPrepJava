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

    static boolean isPalindromeXd(String s){
        String valid="QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
        String res="";
        for (char i: s.toCharArray()){
            if (valid.contains(String.valueOf(i))){
                res+=String.valueOf(i);
            }
        }

        for (int i=0; i<res.length(); i++){
            res.charAt(i);
            if (!(String.valueOf(res.charAt(i)).equals(String.valueOf(res.charAt(res.length()-1-i))))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindromeXd("sankalp"));
        System.out.println(isPalindromeXd("racecar"));
    }

}
