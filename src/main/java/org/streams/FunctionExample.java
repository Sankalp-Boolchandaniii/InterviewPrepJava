package org.streams;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class FunctionExample {

    // Function takes args are returns the values as needed

    public static void main(String[] args) {
        // Function takes one arg
        Function<Integer, String> function=x->String.valueOf(x+1);
        System.out.println(function.apply(1));

        // BiFunction takes two args
        BiFunction<Integer, Integer, String> fn2=(x,y)->String.valueOf(x+y+1);
        System.out.println(fn2.apply(2, 3));

        // UnaryOperator is used where the return type and parameter type are same and its an extension of Function
        UnaryOperator<Integer> unaryOperator=x->x+x;
        System.out.println(unaryOperator.apply(2));

        // BinaryOperator is used where the return type and both the parameters type are same and its an extension of BiFunction
        BinaryOperator<Integer> binaryOperator=(x, y)->(x+y)*3;
        System.out.println(binaryOperator.apply(2, 3));
    }

}
