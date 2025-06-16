package org.streams;

public class Lambda {

    // Q. what is lambda function??
    // A. a function inside a function used to implement a functional interface to increase readability of code.
    // Q. what is functional interface??
    // A. an interface with only abstract method.


    public static void main(String[] args) {

        // example 1
        MathOperations sumOp=(a, b)->a+b;
        System.out.println(sumOp.operate(1,2));

        // example 2
        MathOperations diffOp=(int a, int b)->{
            return a-b;
        };
        System.out.println(diffOp.operate(4,3));

        // example 3
        MathOperations multiplyOp=(a, b)->{
            return a*b;
        };
        System.out.println(multiplyOp.operate(3,4));
    }

}

interface MathOperations{
    int operate(int a, int b);
}