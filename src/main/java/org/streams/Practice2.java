package org.streams;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Practice2 {

    public static void main(String[] args) {
        q9();
    }


    static void q1(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> integers = numbers.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0)).get(true);
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

}

