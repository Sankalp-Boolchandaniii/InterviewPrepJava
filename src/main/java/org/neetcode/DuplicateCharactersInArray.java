package org.neetcode;

import java.util.HashSet;
import java.util.Set;

public class DuplicateCharactersInArray {

    static void duplicateCharactersInArray(int[] arr){
        Set<Integer> set=new HashSet<>();
        for (int i:arr){
            if (set.contains(i)){
                System.out.println(i);
            }
            set.add(i);
        }
    }

    static void duplicateCharactersInArrayBetter(int[] arr){
        Set<Integer> set=new HashSet<>();
        for (int i:arr){
            if (!set.add(i)){
                System.out.println(i);
            }
        }
    }

}


// .add directly is better because it anyways checks for the value before inserting