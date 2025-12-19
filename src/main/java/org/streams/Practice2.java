package org.streams;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Practice2 {

    public static void main(String[] args) {
        q16();
    }


    static void q1(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> integers = numbers.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0)).get(true);
        System.out.println(integers);
        // or
        integers = numbers.stream().filter(x->x%2==0).toList();
        System.out.println(integers);
    }

    static void q2(){
        List<String> names = Arrays.asList("john", "jane", "jack");
        List<String> list = names.stream().map(x -> x.toUpperCase()).toList();
        System.out.println(list);
    }

    static void q3(){
        List<String> names = Arrays.asList("John", "Alice", "Bob");
        String a = names.stream().filter(x -> x.startsWith("A")).findFirst().get();
        System.out.println(a);
    }

    static void q4(){
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "HR"),
                new Employee("Bob", "Engineering"),
                new Employee("Charlie", "HR"),
                new Employee("David", "Engineering"),
                new Employee("Eve", "Marketing")
        );
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDepartment)));
    }

    static void q5(){
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "HR"),
                new Employee("Bob", "Engineering"),
                new Employee("Charlie", "HR"),
                new Employee("David", "Engineering"),
                new Employee("Eve", "Marketing")
        );
        List<String> list = employees.stream().map(Employee::getName).toList();
        System.out.println(list);
    }

    static void q6(){
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "HR", 50000),
                new Employee("Bob", "Engineering", 75000),
                new Employee("Charlie", "HR", 65000),
                new Employee("David", "Engineering", 85000),
                new Employee("Eve", "Marketing", 60000)
        );
        employees.stream().collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.mapping(Employee::getSalary, Collectors.reducing(Double::max))
        )).forEach((dept, sal)-> System.out.println(dept+": "+sal.get()));
        System.out.println("-------------------------------------------------");
        employees.stream().collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.mapping(Employee::getSalary, Collectors.reducing(Double::max))
        )).entrySet().forEach(x->System.out.println(x.getKey()+": "+x.getValue().get()));
    }

    static void q7(){
        List<List<String>> nested = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d")
        );
        List<String> list = nested.stream().flatMap(x -> x.stream()).toList();
        System.out.println(list);
    }

    static void q8(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 2, 3);
        List<Integer> list = numbers.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting())).entrySet().stream().filter(x -> x.getValue() > 1).map(x->x.getKey()).toList();
        System.out.println(list);
    }

    static void q9(){
        List<String> items = Arrays.asList("apple", "banana", "orange", "apple", "grape", "banana", "kiwi", "apple");
        Map<String, Long> collect = items.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        List<String> list = collect.entrySet().stream().filter(x -> x.getValue() == 1).map(x -> x.getKey()).toList();
        Map<Boolean, List<Map.Entry<String, Long>>> collect1 = collect.entrySet().stream().collect(Collectors.partitioningBy(x -> x.getValue() == 1));
        System.out.println(collect1);
    }

    static void q10(){
        List<String> strings = Arrays.asList("apple", "banana", "cherry", "dragonfruit");
        String s = strings.stream().max(Comparator.comparingInt(String::length)).get();
        System.out.println(s);
    }

    static void q11(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> list = numbers.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0)).get(true);
        System.out.println(list);
    }

    static void q12(){
        String sentence = "apple banana apple orange banana apple";
        Map<String, Long> collect = Arrays.stream(sentence.split(" ")).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        System.out.println(collect);
    }

    static void q13(){
        String upper="QWERTYUIOPASDFGHJKLZXCVBNM";
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Jack");
        List<String> list = names.stream().filter(x -> upper.contains(String.valueOf(x.charAt(0)))).toList();
        if (list.size()!=names.size()){
            System.out.println("false");
        } else {
            System.out.println("true");
        }
    }

    static void q14(){
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6);
        List<Integer> list = list2.stream().filter(x -> list1.contains(x)).toList();
        System.out.println(list);
    }

    static void q15(){
//        18. Convert List of Numbers to a Comma-Separated String
//// Output: "1,2,3,4"
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        String collect = numbers.stream().map(x -> String.valueOf(x)).collect(Collectors.joining(","));
        System.out.println(collect);
    }

    static void q16(){
        String input = "stream";
        Map<String, Long> collect = Arrays.stream(input.split("")).collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        System.out.println(collect);
    }

    static void q17(){
        List<Integer> numbers = Arrays.asList(5, 10, 15, 20);
        Integer collect = numbers.stream().filter(x -> x > 10).map(x -> x * x).collect(Collectors.summingInt(x -> x));
        System.out.println(collect);
    }

    static void q18(){
        List<Integer> numbers = Arrays.asList(3, 5, 7, 2, 8, 10, 6);
        List<Integer> list = numbers.stream().sorted(Comparator.reverseOrder()).limit(3).toList();
        System.out.println(list);
    }

    static void q19(){
        List<String> emails = Arrays.asList("test@gmail.com", "admin@yahoo.com", "user@gmail.com");
        List<String> list = emails.stream().filter(x -> x.endsWith("gmail.com")).toList();
        System.out.println(list);
    }

    static void q20(){
        List<Integer> list1 = Arrays.asList(5, 2, 8, 1);
        List<Integer> list2 = Arrays.asList(7, 3, 6, 4);

        List<Integer> list = Stream.concat(list1.stream(), list2.stream()).sorted().toList();
        System.out.println(list);
    }

    static void q21(){
        Employee e1=new Employee("n1", "d1", 10000);
        Employee e2=new Employee("n2", "d2", 20000);
        Employee e3=new Employee("n3", "d3", 30000);
        Employee e4=new Employee("n4", "d2", 40000);
        Employee e5=new Employee("n5", "d1", 50000);
        List<Employee> list=List.of(e1,e2,e3,e4,e5);

        Map<String, Double> collect = list.stream().collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(collect);
    }

}

