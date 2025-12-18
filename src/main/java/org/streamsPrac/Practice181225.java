package org.streamsPrac;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Practice181225 {

    public static void main(String[] args) {
        q4();
    }

    static void q1(){
        List<Integer> numbers = Arrays.asList(3, 7, 2, 9, 4, 10, 6);
        List<Integer> list = numbers.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x*x)
                .toList();
        System.out.println(list);
    }

    static void q2(){
        List<String> names = Arrays.asList(
                "sankalp", "java", "stream", "API", "code", "Lambda"
        );
        names.stream()
                .filter(x->x.length()>=5)
                .map(String::toUpperCase)
                .sorted()
                .toList();
    }

    static void q3(){
        List<Integer> numbers = Arrays.asList(5, 12, 7, 20, 3, 18, 10);
        Integer i = numbers.stream()
                .filter(x -> x > 10)
                .map(x -> x * x)
                .reduce(0, Integer::sum);
        System.out.println(i);
    }

    static void q4(){
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Sankalp", "IT", 80000),
                new Employee(2, "Ravi", "HR", 50000),
                new Employee(3, "Amit", "IT", 90000),
                new Employee(4, "Neha", "Finance", 70000),
                new Employee(5, "Priya", "IT", 60000)
        );
        System.out.println(
        employees.stream().filter(x->"IT".equals(x.getDepartment()))
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .map(Employee::getName).toList());
    }

    static void q5(){
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Sankalp", "IT", 80000),
                new Employee(2, "Ravi", "HR", 50000),
                new Employee(3, "Amit", "IT", 90000),
                new Employee(4, "Neha", "Finance", 70000),
                new Employee(5, "Priya", "IT", 60000)
        );
        employees.stream().collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.mapping(Employee::getSalary, Collectors.reducing(Integer::sum))
        ));
    }

    static void q6(){
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Sankalp", "IT", 80000),
                new Employee(2, "Ravi", "HR", 50000),
                new Employee(3, "Amit", "IT", 90000),
                new Employee(4, "Neha", "Finance", 70000),
                new Employee(5, "Priya", "IT", 60000)
        );
        employees.stream().collect(Collectors.partitioningBy(x->x.getSalary()>=70000));
    }

}




class Employee {
    private int id;
    private String name;
    private String department;
    private int salary;

    public Employee(int id, String name, String department, int salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDepartment() {
        return this.department;
    }

    public int getSalary() {
        return this.salary;
    }
}
