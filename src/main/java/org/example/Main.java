package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
//        System.out.println(greaterThanHundred());
//        try{
//            validateException("werio");
//        } catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
//        System.out.println(secondHighest());
        System.out.println(noDuplicates("program"));
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

    static Integer secondHighest(){
        List<Employee> employeeList=Arrays.asList(
                new Employee("qw", 12),
                new Employee("er", 34),
                new Employee("ty", 34),
                new Employee("ui", 56),
                new Employee("op", 78),
                new Employee("as", 90)
        );

        List<Integer> collect = employeeList.stream().map(Employee::getSal).sorted(Comparator.reverseOrder()).limit(2).collect(Collectors.toList());
        return collect.get(1);
    }

//    Given a string, find and print the first duplicate character. If none, print "No duplicates".
    static String noDuplicates(String str){
        List<Character> seen=new ArrayList<>();
        for (char c : str.toCharArray()) {
            if (seen.contains(c)){
                return String.valueOf(c);
            }
            seen.add(c);
        }

        return "NO duplicates";
    }

}