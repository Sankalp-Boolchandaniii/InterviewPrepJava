package org.streams;

import java.util.*;
import java.util.stream.Collectors;

public class Practice {

    public static void main(String[] args) {
        q2();
    }

    static void q1(){
        List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
        Map<String, Long> collect = words.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        System.out.println(collect);
    }

    static void q2(){
        List<String> words = Arrays.asList("apple", "banana", "avocado", "blueberry", "apricot");
        Map<Character, List<String>> collect = words.stream().collect(Collectors.groupingBy(x -> x.toLowerCase().charAt(0)));
        System.out.println(collect);
    }

    static void q4(){
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 7);
        List<Integer> integers = list2.stream().collect(Collectors.partitioningBy(list1::contains)).get(true);
        System.out.println(integers);
        integers = list2.stream().filter(list1::contains).toList();
        System.out.println(integers);
    }

    static void q5(){
        List<String> list = Arrays.asList("java", "spring", "boot");
        List<String> list1 = list.stream().map(x -> x.toUpperCase()).toList();
        System.out.println(list1);
    }

    static void q6(){
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 70000),
                new Employee("Bob", 50000),
                new Employee("Charlie", 60000),
                new Employee("David", 80000),
                new Employee("Eve", 55000)
        );
        employees.stream().sorted(Comparator.comparing(Employee::getSalary)
                .reversed())
                .forEachOrdered(x-> System.out.println(x.getName()+", "+x.getSalary()));
    }

    static void q7(){
        List<List<String>> nested = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d"),
                Arrays.asList("e")
        );
        List<String> list = nested.stream().flatMap(x -> x.stream()).toList();
        System.out.println(list);
    }

    static void q8(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 5, 1);
        Map<Integer, Long> collect = numbers.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        List<Integer> list = collect.entrySet().stream().filter(x -> x.getValue() > 1).map(x -> x.getKey()).toList();
        System.out.println(list);
    }
}

class Employee {
    String name;
    double salary;
    String department;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    @Override
    public String toString() {
//        return "Employee{" +
//                "name='" + name + '\'' +
//                ", salary=" + salary +
//                ", department='" + department + '\'' +
//                '}';
        return name;
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Employee(String name, String department) {
        this.name = name;
        this.department=department;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }
}
