package org.neetcode;

// to find if the rotation of one string is substring of another
public class StringRotation {

    // O(n)
    public static boolean rotateString(String s, String goal){
        if (!(s.length()==goal.length())){
            return false;
        }
        for (int i=0;i<s.length();i++){
            s=rotateOnce(s);
            if (s.equals(goal)) return true;
        }
        return false;
    }

    private static String rotateOnce(String s) {
        String first = s.substring(0, 1);
        return s.substring(1)+first;
    }

    public static boolean rotateStringBetter(String s, String goal){
        String concat=s+s;
        return concat.contains(goal);
    }

}
