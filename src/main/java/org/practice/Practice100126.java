package org.practice;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Practice100126 {

    public static void main(String[] args) {

    }

    static void q1(){
        List<Transactionn> transactions = Arrays.asList(
                new Transactionn("1", "ACC123", 12000.0, "AppleStore", LocalDate.now()),
                new Transactionn("2", "ACC456", 500.0, "FlaggedStore", LocalDate.now()),
                new Transactionn("3", "ACC123", 50.0, "CoffeeShop", LocalDate.now()),
                new Transactionn("4", "ACC789", 15000.0, "CarDealer", LocalDate.now().minusDays(1)), // Yesterday
                new Transactionn("5", "ACC000", 100.0, "FlaggedStore", LocalDate.now()),
                new Transactionn("6", "ACC456", 20000.0, "LuxuryWatch", LocalDate.now())
        );

        transactions.stream()
                .filter(x -> x.getAmount() > 10000 || x.getMerchant().equals("FlaggedStore"))
                .map(Transactionn::getAccountId)
                .collect(Collectors.toSet());
    }

    static void q2(){
        List<Customer> customers = Arrays.asList(
                new Customer("C1", Arrays.asList(
                        new Order("O1", "COMPLETED", 250.0),
                        new Order("O2", "PENDING", 500.0)
                )),
                new Customer("C2", Arrays.asList(
                        new Order("O3", "COMPLETED", 1000.0),
                        new Order("O4", "COMPLETED", 50.0)
                )),
                new Customer("C3", Arrays.asList(
                        new Order("O5", "CANCELLED", 3000.0)
                ))
        );

        Double revenue = customers.stream().flatMap(customer -> customer.getOrders().stream())
                .filter(order -> "COMPLETED".equals(order.getStatus()))
                .mapToDouble(Order::getTotalAmount).sum();
    }

    static void q3(){
        List<Payment> payments = Arrays.asList(
                new Payment("USD", 100.0),
                new Payment("USD", 200.0),
                new Payment("EUR", 50.0),
                new Payment("GBP", 80.0),
                new Payment("EUR", 150.0)
        );

        Map<String, Double> collect = payments.stream().collect(Collectors.groupingBy(
                Payment::getCurrency,
                Collectors.averagingDouble(Payment::getAmount)
        ));
    }

    static void q4(){
        List<Sale> sales = Arrays.asList(
                new Sale("Amazon", 1000.0),
                new Sale("Walmart", 2000.0),
                new Sale("Amazon", 500.0),
                new Sale("Target", 1500.0),
                new Sale("Walmart", 500.0),
                new Sale("Apple", 5000.0)
        );

        sales.stream()
                .collect(Collectors.groupingBy(
                        Sale::getMerchantName,
                        Collectors.summingDouble(Sale::getRevenue)
                )).entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
    }

    static void q5(){
        List<Server> servers = Arrays.asList(
                new Server("Server-A", Arrays.asList(
                        new LogEntry("INFO", "Startup"),
                        new LogEntry("ERROR", "Connection refused"),
                        new LogEntry("ERROR", "Timeout")
                )),
                new Server("Server-B", Arrays.asList(
                        new LogEntry("ERROR", "Disk full"),
                        new LogEntry("WARN", "Memory high")
                )),
                new Server("Server-C", Arrays.asList(
                        new LogEntry("ERROR", "Kernel panic"),
                        new LogEntry("INFO", "User logged in")
                ))
        );

        servers.stream().flatMap(x->x.getLogs().stream())
                .filter(x->"ERROR".equals(x.getLevel())).limit(100).toList();
    }


}

class LogEntry {
    private String level; // "INFO", "WARN", "ERROR"
    private String message;

    public LogEntry(String level, String message) {
        this.level = level;
        this.message = message;
    }

    public String getLevel() { return level; }
    public String getMessage() { return message; }
}

class Server {
    private String serverName;
    private List<LogEntry> logs;

    public Server(String serverName, List<LogEntry> logs) {
        this.serverName = serverName;
        this.logs = logs;
    }

    public List<LogEntry> getLogs() { return logs; }
}

class Order {
    private String orderId;
    private String status; // e.g., "COMPLETED", "PENDING", "CANCELLED"
    private double totalAmount;

    public Order(String orderId, String status, double totalAmount) {
        this.orderId = orderId;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public String getStatus() { return status; }
    public double getTotalAmount() { return totalAmount; }
}

class Customer {
    private String customerId;
    private List<Order> orders;

    public Customer(String customerId, List<Order> orders) {
        this.customerId = customerId;
        this.orders = orders;
    }

    public List<Order> getOrders() { return orders; }
}


class Transactionn {
    private String id;
    private String accountId;
    private double amount;
    private String merchant;
    private LocalDate date;

    public Transactionn(String id, String accountId, double amount, String merchant, LocalDate date) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.merchant = merchant;
        this.date = date;
    }

    // Getters
    public String getId() { return id; }
    public String getAccountId() { return accountId; }
    public double getAmount() { return amount; }
    public String getMerchant() { return merchant; }
    public LocalDate getDate() { return date; }
}

class Payment {
    String currency; // e.g., "USD", "EUR"
    double amount;

    public Payment(String currency, double amount) {
        this.currency = currency;
        this.amount = amount;
    }
    public String getCurrency() { return currency; }
    public double getAmount() { return amount; }
}

class Sale {
    String merchantName;
    double revenue;

    public Sale(String merchantName, double revenue) {
        this.merchantName = merchantName;
        this.revenue = revenue;
    }
    public String getMerchantName() { return merchantName; }
    public double getRevenue() { return revenue; }
}
