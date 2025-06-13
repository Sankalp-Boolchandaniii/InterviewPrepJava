package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
//        System.out.println(greaterThanHundred());
        try{
            validateException("werio");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

//    Given a Map<String, Integer>, print all keys that have values greater than 100.
    static Map<String, Integer> greaterThanHundred(){
        Map<String, Integer> stringIntegerMap=new HashMap<>();
        stringIntegerMap.put("key1", 1);
        stringIntegerMap.put("key2", 100);
        stringIntegerMap.put("key3", 150);
        stringIntegerMap.put("key4", 120);
        stringIntegerMap.put("key5", 10);

        Map<String, Integer> resultM=new HashMap<>();
        for (Map.Entry<String, Integer> map: stringIntegerMap.entrySet()){
            if (map.getValue()>100){
                resultM.put(map.getKey(), map.getValue());
            }
        }

        return resultM;
    }

    static void validateException(String email) throws InvalidEmailException {
        if (!email.contains("@")){
            throw new InvalidEmailException("invalid email");
        }
    }

}