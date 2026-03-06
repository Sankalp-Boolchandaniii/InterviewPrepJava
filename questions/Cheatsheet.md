# 2026 Java Backend Developer Interview Questions

## Core Java Interview Questions

1. **What is the difference between JDK, JRE, and JVM?**
   The JDK is a complete development kit that includes the JRE and development tools like compilers. The JRE provides the libraries and environment required to run Java applications. The JVM is the virtual machine that actually executes the Java bytecode, making Java platform-independent.

2. **How does the `equals()` method differ from the `==` operator?**
   The `==` operator checks for reference equality, meaning it verifies if both references point to the exact same object in memory. The `equals()` method evaluates the actual content or state of the objects to see if they are logically equivalent.

3. **What are the differences between an Abstract Class and an Interface?**
   An abstract class can have both abstract and concrete methods, as well as state (instance variables). An interface represents a contract with purely abstract methods (until Java 8 introduced default/static methods) and cannot hold instance state. A class can implement multiple interfaces but can only extend one abstract class.

4. **Explain the difference between `HashMap` and `ConcurrentHashMap`.**
   `HashMap` is not thread-safe and can throw a `ConcurrentModificationException` if modified concurrently by multiple threads. `ConcurrentHashMap` is designed for concurrent environments, using a locking mechanism at the segment or bucket level to allow thread-safe reads and writes without locking the entire map.

5. **What are Java Records (introduced in Java 14/16)?**
   Records are a special kind of class intended to act as transparent carriers for immutable data. They drastically reduce boilerplate code by automatically generating constructors, accessors, `equals()`, `hashCode()`, and `toString()` methods based on the declared fields.

6. **How do Virtual Threads (Java 21) differ from Platform Threads?**
   Platform threads are mapped 1:1 to OS threads, making them heavy and resource-intensive to create and maintain. Virtual threads are lightweight, managed directly by the JVM, and allow developers to run millions of concurrent tasks with minimal memory overhead.

7. **What is the difference between a shallow copy and a deep copy?**
   A shallow copy creates a new object but inserts references to the exact same memory locations as the original object's fields. A deep copy creates a completely new object and recursively creates new copies of all objects referenced by the original, ensuring total independence.

8. **Explain the concept of the String Pool in Java.**
   The String Pool is a special storage area in the Java heap dedicated to storing unique string literals. When you create a string literal, the JVM checks the pool first; if the string already exists, it returns the reference, which saves memory and optimizes performance.

9. **What is a memory leak in Java, and how does Garbage Collection handle it?**
   A memory leak occurs when an application keeps references to objects that are no longer needed, preventing the Garbage Collector (GC) from reclaiming their memory. While the GC automatically removes unreferenced objects, it cannot clean up referenced ones, eventually leading to an `OutOfMemoryError`.

10. **What is the `volatile` keyword used for?**
    The `volatile` keyword ensures that the value of a variable is always read from the main memory, rather than from a thread's local CPU cache. It provides visibility guarantees across threads but does not provide atomicity for compound operations like increments.

11. **What is the "Diamond Problem" and how does Java handle it?**
    It occurs when a class inherits from two interfaces that both have a default method with the same signature. Java forces the developer to resolve the ambiguity by overriding the method in the implementation class.

12. **Explain the difference between `final`, `finally`, and `finalize`.**
`final` is a modifier to make variables, methods, or classes immutable/un-inheritable; `finally` is a block used in exception handling to execute cleanup code; `finalize` is a deprecated method formerly used by the Garbage Collector.

13. **How does a `HashMap` work internally?**
    It uses an array of "buckets" and hashing to store key-value pairs. In modern Java, if a bucket exceeds a threshold (8 elements), it converts from a linked list to a Balanced Tree (Red-Black Tree) to improve performance from $O(n)$ to $O(\log n)$.
---

## Spring Boot Interview Questions

14. **What is the purpose of the `@SpringBootApplication` annotation?**
    It is a convenience annotation that combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`. This single annotation bootstraps the entire Spring Boot application and configures beans automatically based on class-path dependencies.

15. **How does Spring Boot's Auto-Configuration work?**
    Auto-configuration intelligently attempts to configure your Spring application based on the jar dependencies you have added. For example, if it detects `spring-boot-starter-web` and Tomcat on the classpath, it automatically sets up a dispatcher servlet and a web server without manual XML or Java configuration.

16. **What is Dependency Injection (DI) and how does Spring implement it?**
    DI is a design pattern where an object's dependencies are provided to it dynamically rather than the object creating them itself. Spring implements DI using an Inversion of Control (IoC) container that manages the lifecycle and wiring of beans using annotations like `@Autowired`.

17. **What are Spring Boot Actuators used for?**
    Actuators provide production-ready features to help you monitor and manage your live application. They expose HTTP endpoints to check application health, gather metrics, view environment properties, and analyze thread dumps.

18. **What is the difference between `@Controller` and `@RestController`?**
    `@Controller` is used to mark a class as a web controller returning a view (like an HTML or JSP page). `@RestController` is a specialized version that combines `@Controller` and `@ResponseBody`, meaning it automatically serializes the returned objects into JSON or XML for RESTful APIs.

19. **Explain the difference between `@Component`, `@Service`, and `@Repository`.**
    `@Component` is a generic stereotype for any Spring-managed component. `@Service` is a specialization used at the business layer to indicate business logic, while `@Repository` is used at the persistence layer and translates database exceptions into Spring's `DataAccessException`.

20. **How do you handle exceptions globally in a Spring Boot application?**
    You can use the `@ControllerAdvice` or `@RestControllerAdvice` annotations on a centralized class. Within this class, you use `@ExceptionHandler` methods to intercept specific exceptions thrown by any controller and return standardized error responses to the client.

21. **What are Spring Boot Profiles?**
    Profiles allow developers to segregate application configuration and make certain beans or properties available only in specific environments. You can define profiles for `dev`, `test`, or `prod` in your `application.properties` and activate them via command-line arguments or environment variables.

22. **How does Spring Data JPA simplify database access?**
    Spring Data JPA drastically reduces boilerplate code by allowing you to define repository interfaces that automatically generate database queries based on method names. It wraps JPA/Hibernate, handling entity management, transactions, and pagination automatically.

23. **What is the purpose of the `@Transactional` annotation?**
    This annotation is used to declaratively manage database transactions at the method or class level. If a method annotated with `@Transactional` completes successfully, the transaction commits; if an unchecked runtime exception is thrown, the transaction is automatically rolled back.

---

## REST APIs Interview Questions

24. **What are the architectural constraints of a RESTful API?**
    REST requires a client-server architecture, stateless interactions, cacheability, a uniform interface, and a layered system. These constraints ensure that the API is scalable, reliable, and easy to modify without breaking client implementations.

25. **What is the difference between PUT and PATCH HTTP methods?**
    `PUT` is used to completely replace an existing resource with the provided payload, requiring all fields to be sent. `PATCH` is used to apply partial modifications to a resource, updating only the specific fields provided in the request body.

26. **Explain the concept of Idempotency in REST APIs.**
    An API endpoint is idempotent if making multiple identical requests has the same effect on the server state as making a single request. `GET`, `PUT`, and `DELETE` are idempotent, whereas `POST` is not, since multiple requests would create multiple duplicate resources.

27. **How do you secure a REST API?**
    REST APIs are typically secured using HTTPS to encrypt data in transit and authentication mechanisms like OAuth2 or JWT (JSON Web Tokens). Additionally, proper input validation, rate limiting, and CORS configuration are implemented to prevent common web vulnerabilities.

28. **What is the significance of HTTP Status Codes?**
    Status codes communicate the result of a client's request to the server. Common categories include 2xx for success (e.g., 200 OK), 4xx for client errors (e.g., 404 Not Found, 400 Bad Request), and 5xx for server-side errors (e.g., 500 Internal Server Error).

29. **Explain JWT (JSON Web Token) authentication.**
    JWT is a stateless way of handling authentication where the server sends a signed token to the client after login. The client includes this token in the header of subsequent requests, allowing the server to verify the user without storing session data.

---

## Microservices & Cloud Interview Questions

30. **What is an API Gateway in a microservices architecture?**
    An API Gateway acts as a single entry point for all client requests, routing them to the appropriate backend microservices. It often handles cross-cutting concerns like authentication, rate limiting, SSL termination, and request aggregation.

31. **Explain the Circuit Breaker pattern.**
    The Circuit Breaker pattern prevents a system from repeatedly trying to execute an operation that is likely to fail, such as calling a down microservice. If failures reach a certain threshold, the circuit "trips" and fast-fails subsequent requests, giving the failing service time to recover.

32. **How do microservices communicate with each other?**
    Microservices can communicate synchronously using HTTP/REST or gRPC APIs. They can also communicate asynchronously using message brokers like Kafka or RabbitMQ, which helps decouple services and improve system resilience.

33. **What is Service Discovery?**
    In a dynamic cloud environment, the IP addresses of microservice instances change constantly due to scaling or failures. Service discovery (using tools like Netflix Eureka or Consul) allows services to dynamically find and route requests to each other without hardcoded network addresses.

34. **What is the Saga Pattern in distributed transactions?**
    The Saga pattern manages distributed transactions by breaking them down into a sequence of local transactions coordinated across multiple microservices. If one step fails, the Saga executes compensating transactions to undo the database changes made by the preceding successful steps.

---

## Other Backend Fundamentals

35. **What are ACID properties in a database?**
    ACID stands for Atomicity, Consistency, Isolation, and Durability, which ensure reliable processing of database transactions. These properties guarantee that transactions are processed entirely or not at all, maintaining data integrity even in the event of system failures.

36. **What is a database index and how does it improve performance?**
    An index is a data structure (often a B-Tree) that improves the speed of data retrieval operations on a database table at the cost of additional storage and slower writes. It allows the database engine to locate the requested rows instantly without scanning the entire table.

37. **Explain the difference between SQL and NoSQL databases.**
    SQL databases are relational, strictly structured with schemas, and scale vertically, making them ideal for complex queries and transactional consistency. NoSQL databases are non-relational, document- or key-value-based, schema-less, and scale horizontally, fitting well for rapid development and highly unstructured data.

38. **Why would you use Apache Kafka over a traditional message queue like RabbitMQ?**
    Kafka is a distributed streaming platform designed for high-throughput, replayable, and persistent event streaming. While RabbitMQ excels at complex routing and traditional pub/sub queuing, Kafka is better suited for event sourcing, log aggregation, and handling massive real-time data pipelines.

39. **What is Redis and how is it used in backend development?**
    Redis is an in-memory, key-value data store known for its extremely fast read and write speeds. It is heavily used in backend development for caching frequently accessed database queries, managing distributed user sessions, and implementing rate limiters.

40. **What is the N+1 query problem and how do you solve it in Hibernate?**
    The N+1 problem occurs when an ORM framework executes one query to fetch a list of entities and then 'N' additional queries to fetch their related associations. You can solve it by using `JOIN FETCH` in your JPQL queries or configuring `EntityGraphs` to load associations eagerly in a single query.

41. **What is the role of Docker in backend development?**
    Docker packages an application and its dependencies into a standardized, isolated unit called a container. This ensures that the application runs identically across multiple environments (development, testing, production), eliminating the classic "it works on my machine" problem.

42. **Explain the concept of Rate Limiting.**
    Rate limiting restricts the number of requests a user or client can make to an API within a specified time window. It protects backend services from being overwhelmed by traffic spikes, brute-force attacks, or Denial of Service (DoS) attacks.

43. **What is OAuth2 and how does the authorization code flow work?**
    OAuth2 is an industry-standard protocol for authorization, allowing third-party applications to access user resources without exposing passwords. The authorization code flow redirects the user to an auth server to log in, returns a short-lived code to the client, and exchanges that code for an access token securely via the backend.

44. **How do you ensure data security in transit and at rest?**
    Data in transit is secured by enforcing TLS/HTTPS protocols to encrypt communications between the client and server over the network. Data at rest is protected by encrypting database storage volumes, using strong hashing algorithms (like bcrypt) for passwords, and securing sensitive keys in vault systems.

45. **What is the "CAP Theorem"?**
    It states that a distributed system can only provide two out of three guarantees: Consistency, Availability, and Partition Tolerance. In the event of a network partition, you must choose between keeping the system available or keeping the data consistent.