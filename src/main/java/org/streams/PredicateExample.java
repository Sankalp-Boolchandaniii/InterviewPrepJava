package org.streams;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PredicateExample {

    // Predicate returns only boolean

    public static void main(String[] args) {
        Predicate<Integer> p1=x->x%2==0;
        System.out.println(p1.test(100));
        System.out.println(p1.test(101));

        // Predicate takes one arg
        Predicate<String> strtsWithA=x->x.startsWith("a");
        Predicate<String> endsWithA=x->x.endsWith("a");

        System.out.println(strtsWithA.and(endsWithA).test("ana"));

        // BiPredicate takes two args
        BiPredicate<Integer, String> compare=(x,y)->x.equals(Integer.parseInt(y));
        System.out.println(compare.test(1, "1"));

    }

}
