package org.neetcode;

import java.util.Stack;

public class EvaluateReversePolishNotation {

    public static int evalRPN(String[] tokens) {
        if (tokens.length==1){
            return Integer.parseInt(tokens[0]);
        }
        String ops="/*-+";
        Stack<Integer> stck=new Stack<>();
        for (String s:tokens){
            if (!ops.contains(s)){
                stck.add(Integer.parseInt(s));
            } else {
                int x=stck.get(stck.size()-2);
                int y=stck.get(stck.size()-1);
                int res=calc(s, x, y);
                stck.pop();
                stck.pop();
                stck.add(res);
            }
        }

        return stck.get(0);

    }

    private static int calc(String s, int x, int y) {
        if (s.equals("*")){
            return x*y;
        } else if (s.equals("+")) {
            return x+y;
        } else if (s.equals("-")) {
            return x-y;
        } else {
            return x/y;
        }
    }

}
