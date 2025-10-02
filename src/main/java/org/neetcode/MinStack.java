package org.neetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class MinStack {

    List<Integer> minStack;

    public MinStack() {
        minStack=new ArrayList<>();
    }

    public void push(int val) {
        minStack.add(val);
    }

    public void pop() {
        minStack.remove(minStack.size()-1);
    }

    public int top() {
        return minStack.get(minStack.size()-1);
    }

    public int getMin() {
        return minStack.stream().min(Comparator.comparingInt(Integer::intValue)).get();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
