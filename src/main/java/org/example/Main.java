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
//        System.out.println(noDuplicates("program"));
//        System.out.println(anagram("racecas", "carrace"));
//        sortByLength();
//        longestWord();
//        missigNumber(new int[]{1, 2, 4, 5, 6});
//        fizzBuzz();
        highestSal();
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

    static Boolean anagram(String sOne, String sTwo){
        if(sOne.length()!=sTwo.length()){
            return Boolean.FALSE;
        }
        List<String> listOne = Arrays.stream(sOne.strip().split("")).sorted().toList();
        List<String> listtwo = Arrays.stream(sTwo.strip().split("")).sorted().toList();
        for (int i=0;i<=sOne.length()-1;i++){
            if (!listOne.get(i).equals(listtwo.get(i))){
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    static void sortByLength(){
        List<String> words = Arrays.asList(
                "apple", "bat", "ball", "grape", "pear", "cherry", "banana", "kiwi", "melon", "orange"
        );
        Map<Integer, List<String>> collect = words.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(collect);
    }

    static void longestWord(){
        List<String> words = Arrays.asList(
                "apple", "bat", "ball", "grape", "pear", "cherry", "banana", "kiwi", "melon", "orange"
        );
        String word = words.stream().max(Comparator.comparingInt(String::length)).get();
        System.out.println(word);
    }

//    Given an array of n-1 integers from 1 to n, with no duplicates, find the missing number.
    static void missigNumber(int[] numbers){
        int actualSum=0;
        int sum = Arrays.stream(numbers).sum();
        for (int i=0; i<=numbers.length+1;i++){
            actualSum+=i;
        }
        System.out.println(actualSum-sum);
    }

    static void fizzBuzz(){
        List<String> stringList=new ArrayList<>();
        for (int i=1;i<=50;i++){
            if (i%3==0 && i%5==0){
                stringList.add("fizzbuzz");
            } else if (i%3==0) {
                stringList.add("fizz");
            } else if (i%5==0) {
                stringList.add("buzz");
            } else {
                stringList.add(String.valueOf(i));
            }
        }
        System.out.println(stringList);
    }

    static void highestSal(){
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 82000),
                new Employee("Bob", 55000),
                new Employee("Charlie", 68000),
                new Employee("Diana", 82000),
                new Employee("Ethan", 47000)
        );
//        employees.sort(Comparator.comparing(Employee::getSal).reversed().thenComparing(Employee::getName));
//        employees.forEach(x-> System.out.println(x.getName()+": "+x.getSal()));
        List<Employee> list = employees.stream().sorted(Comparator.comparing(Employee::getSal).reversed().thenComparing(Employee::getName)).toList();
        list.forEach(x-> System.out.println(x.getName()+": "+x.getSal()));
    }
}