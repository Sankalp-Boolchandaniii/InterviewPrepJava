package org.streams;

import java.util.function.Function;
import java.util.function.Supplier;

public class SupplierExample {
    // Supplier takes no arg but return a value

    public static void main(String[] args) {
        Supplier<Integer> getOne=() -> 1;
        System.out.println(getOne.get());
    }

}
