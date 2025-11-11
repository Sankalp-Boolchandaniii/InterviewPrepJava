package org.streams;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Practice0 {

    private static List<Integer> list=List.of(1,2,3,4,5);

    public static void main(String[] args) {
        q1();
    }

    // get the sum of all the numbers in the stream
    static void q1(){
        Integer sum = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        sum = list.stream().reduce(1, (a, b) -> a + b);         // identity is starting number
        System.out.println(sum);

        sum = list.stream().reduce((a, b) -> a + b).get();
        System.out.println(sum);
    }

    // max from the list
    static void q2(){
        Integer max = list.stream().reduce((a, b) -> Integer.max(a, b)).get();
        System.out.println(max);

        max = list.stream().max(Comparator.comparingInt(Integer::intValue)).get();
        System.out.println(max);
    }
}
