package org.streamsPrac;

import java.util.*;
import java.util.stream.Collectors;

public class Practice181225 {

    public static void main(String[] args) {
        q11();
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

    static void q7(){
        List<List<Integer>> numbers = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 9)
        );
        List<Integer> list = numbers.stream().flatMap(x -> x.stream().filter(y -> y % 2 == 0).map(z -> z * z)).toList();
        System.out.println(list);
    }

    static void q8(){
        List<Order> orders = Arrays.asList(
                new Order(1, Arrays.asList(
                        new Item("Pen", 10),
                        new Item("Book", 50)
                )),
                new Order(2, Arrays.asList(
                        new Item("Pencil", 5),
                        new Item("Book", 50),
                        new Item("Bag", 500)
                ))
        );

        orders.stream()
                .flatMap(orderss->orderss.items.stream()
                                .filter(item -> item.price>=50))
                .map(x->x.name)
                .collect(Collectors.toSet());
    }

    static void q9(){
        List<Order> orders = Arrays.asList(
                new Order(1, Arrays.asList(
                        new Item("Pen", 10),
                        new Item("Book", 50)
                )),
                new Order(2, Arrays.asList(
                        new Item("Pencil", 5),
                        new Item("Book", 50),
                        new Item("Bag", 500)
                ))
        );
        System.out.println(
        orders.stream().
                flatMap(order -> order.getItems().stream()
                        .map(Item::getName))
                .collect(Collectors.groupingBy(x->
                        x,
                        Collectors.counting())));
    }

    static void q10(){
        List<Order> orders = Arrays.asList(
                new Order(1, Arrays.asList(
                        new Item("Pen", 10),
                        new Item("Book", 50)
                )),
                new Order(2, Arrays.asList(
                        new Item("Pencil", 5),
                        new Item("Book", 50),
                        new Item("Bag", 500)
                ))
        );
        String s = orders.stream()
                .flatMap(order -> order.getItems().stream().map(Item::getName))
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
        System.out.println(s);
    }

    static void q11(){
        List<Customer> customers = Arrays.asList(
                new Customer(1, Arrays.asList(
                        new Transaction("Food", 200),
                        new Transaction("Travel", 500)
                )),
                new Customer(2, Arrays.asList(
                        new Transaction("Food", 300),
                        new Transaction("Shopping", 1000)
                )),
                new Customer(3, Arrays.asList(
                        new Transaction("Food", 200),
                        new Transaction("Travel", 700)
                ))
        );

        customers.stream().flatMap(customer -> customer.getTransactions().stream())
                .collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingInt(Transaction::getAmount)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).get();
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


class Order {
    int orderId;
    List<Item> items;

    public Order(int orderId, List<Item> items) {
        this.orderId = orderId;
        this.items = items;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

class Item {
    String name;
    int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}


class Customer {
    int id;
    List<Transaction> transactions;

    public Customer(int id, List<Transaction> transactions) {
        this.id = id;
        this.transactions = transactions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}

class Transaction {
    String category;
    int amount;

    public Transaction(String category, int amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

