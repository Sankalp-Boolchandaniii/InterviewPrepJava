package org.streams;

import java.util.List;

public class Practice0 {

    public static void main(String[] args) {
        q1();
    }

    // get the sum of all the numbers in the stream
    static void q1(){
        List<Integer> list=List.of(1,2,3,4,5);
        Integer sum = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        sum = list.stream().reduce(1, (a, b) -> a + b);         // identity is starting number
        System.out.println(sum);

        sum = list.stream().reduce((a, b) -> a + b).get();
        System.out.println(sum);
    }
}
