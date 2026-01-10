package org.neetcode;

import java.util.Stack;

public class DailyTemperatures {

    // would work but time complexity is O(n^2)
    public static int[] dailyTemperaturesBrute(int[] temperatures) {
        int len=temperatures.length;
        int[] res=new int[len];
        for (int i=0;i<len;i++){
            for (int j=i+1;j<len;j++){
                if (temperatures[j]>temperatures[i]){
                    res[i]=j-i;
                    break;
                }
            }
        }
        return res;
    }

    // better - O(n)
    public static int[] dailyTemperaturesBetter(int[] temperatures) {
        int len=temperatures.length;
        int[] res=new int[len];

        Stack<Integer> stack=new Stack<>();
        for (int i=0;i<len;i++){
            while ((!stack.empty()) && (temperatures[stack.peek()]<temperatures[i])){
                res[stack.peek()]=i-stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return res;
    }

}
