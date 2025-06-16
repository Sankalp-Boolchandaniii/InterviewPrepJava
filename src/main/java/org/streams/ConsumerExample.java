package org.streams;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumerExample {

    // Consumer - takes argument and performs but returns nothing. Return type void.

    public static void main(String[] args) {
        // Consumer takes one variable
        Consumer<Integer> consumer=x-> System.out.println(x);
        Consumer<Integer> consumer1=x-> System.out.println(x+1);

        // BiConsumer takes two variables
        BiConsumer<Integer, Integer> biConsumer=(x,y)->System.out.println(x+y);

        consumer.accept(2);
        biConsumer.accept(2, 3);
        consumer.andThen(consumer1).accept(1);

    }
}
