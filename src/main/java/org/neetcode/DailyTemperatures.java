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

/*
the main idea here is to initialize a stack that would store the indices of elements. and pop the indices while we have the current ith element greater that top of stack indexed element
we will iterate through the given array. if the stack is empty we will add the element index to the stack

say suppose we have an array [73,74,75,71,70,69,76,73]
i=0 - we will add index of 73 i.e 0 to the stack (since the stack is empty)
i=1 - we will pop 0 now that we got 74 which is greater than 73 and push in 1 to the stack. Here res[0] (peeked index from stack) would be stored as i - peeked index which will be 1-0=1
i=2 - 75>74, so we will pop the top element from stack (1) and put in index of 75 (2). since 75>74, stack.peek=1 and res[stack.peek] would be (i - stack.peek) i.e. 2-1=1
i=3 - 71<75. index of 71 goes to the stack, stack is now (2,3)
i=4 - 70<71. index of 70 goes to stack, stack is (2,3,4)
i=5 - 69<70. index of 70 goes to stack, stack is (2,3,4,5)
i=6 - 76>69. now while we dont see an element greater than 76, we will pop the stack and res would be having the indexes changed accordingly. i.e.
        res[5] = 6-5 = 1
        res[4] = 6-4 = 2
        res[3] = 6-3 = 3
        res[2] = 6-2 = 4
     after this, we will put in the index of 76(6) in the stack
i=7 - 73<76. index of 73 goes to stack, stack is (6,7)
for both the cases now, res is not changed and indexes for both the temperatures(76, 73) remain as (0, 0)
final res= [1,1,4,3,2,1,0,0]
 */