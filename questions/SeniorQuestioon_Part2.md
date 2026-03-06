# Backend Developer Interview Questions and Answers

### 1. `synchronized` vs `ReentrantLock`
* **`synchronized`:** A JVM-level, implicit lock that is simple to use but lacks flexibility. It blocks indefinitely if a lock is unavailable.
* **`ReentrantLock`:** An API-level lock (`java.util.concurrent.locks`) that requires manual locking and unlocking (always in a `finally` block). It offers advanced features like fairness policies, interruptible lock waits, and `tryLock()` to avoid deadlocks.

### 2. Purpose of the `volatile` keyword
`volatile` guarantees **visibility**. Without it, a thread might cache a variable locally, making updates invisible to other threads. Declaring a variable `volatile` forces all reads and writes to go directly to main memory. *Note: It guarantees visibility, but not atomicity (e.g., `count++` is still not thread-safe).*

### 3. How `ThreadLocal` works internally
Every `Thread` object maintains a private, internal `ThreadLocalMap`. When you call `threadLocal.set(value)`, it retrieves the current thread's map and stores the value using the `ThreadLocal` instance itself as the key. This ensures that each thread can only access its own isolated value.

### 4. Fail-Fast vs Fail-Safe Iterators
* **Fail-Fast (e.g., `ArrayList`, `HashMap`):** Operates directly on the collection. If the collection is structurally modified during iteration, it immediately throws a `ConcurrentModificationException`.
* **Fail-Safe (e.g., `CopyOnWriteArrayList`, `ConcurrentHashMap`):** Operates on a clone of the collection or uses internal data structures to safely handle concurrent updates. It allows modifications during iteration without throwing an exception.

### 5. The Diamond Problem in Java 8 and its Resolution
The diamond problem occurs when a class implements two interfaces that both have a `default` method with the exact same signature. The compiler gets confused about which one to inherit.
* **Resolution:** Java forces the implementing class to override the method. Inside the overridden method, you can resolve it by explicitly calling the desired interface's method using the syntax: `InterfaceA.super.methodName()`.

### 6. PermGen vs Metaspace
* **PermGen (Java 7 and earlier):** Stored class metadata in contiguous Java heap memory with a fixed maximum size, often leading to `OutOfMemoryError: PermGen space`.
* **Metaspace (Java 8+):** Replaced PermGen. It stores metadata in native OS memory (RAM) and can auto-scale dynamically by default, practically eliminating class-loading related OOM errors.

### 7. G1GC vs CMS Garbage Collector
* **CMS (Concurrent Mark Sweep):** Aims for low latency but does not compact memory, leading to heap fragmentation over time. It was deprecated and removed in later Java versions.
* **G1GC (Garbage-First):** Partitions the heap into equal-sized regions and focuses on clearing regions with the most garbage first. It compacts memory on the fly (preventing fragmentation) and allows you to set predictable, target pause times.

### 8. Functional Interface and Lambda Expressions
A functional interface is an interface with exactly **one abstract method** (often marked with `@FunctionalInterface`). It enables lambdas because the compiler uses the functional interface as the "target type" for the lambda expression. The lambda simply acts as a concise, inline implementation of that single abstract method.

### 9. "Effectively Final" variables in Lambdas
A variable is "effectively final" if its value is assigned once and never modified, even if the `final` keyword isn't explicitly written. Lambdas can only capture local variables that are final or effectively final. This is because lambdas execute in a different scope and thread context; they work on a *copy* of the variable, so allowing mutations would cause unpredictable concurrency bugs.

### 10. Detecting and Fixing Memory Leaks
* **Detect:** Monitor the JVM using tools like VisualVM or Java Mission Control. Look for a "sawtooth" memory graph that trends upward continuously after Garbage Collection. Take a Heap Dump (`jmap`) and analyze it using Eclipse MAT to find objects accumulating and tracing their GC roots.
* **Fix:** Close all I/O connections/streams using `try-with-resources`, remove objects from static Maps/Collections when no longer needed, ensure custom objects used as Map keys have properly overridden `equals()` and `hashCode()`, and clear `ThreadLocal` variables after use.

### 11. `Callable` vs `Runnable`
* **`Runnable`:** An interface with a `run()` method that returns `void` and cannot throw checked exceptions. It's the traditional way to represent a task intended for concurrent execution.
* **`Callable`:** Introduced in Java 5, it has a `call()` method that returns a generic value (`V`) and can throw checked exceptions. It is typically used with an `ExecutorService` to get a `Future` object representing the pending result of the task.

### 12. Java Memory Model (JMM) and Happens-Before
* **JMM:** The specification that defines how threads interact through memory, specifically governing the visibility of variables and the ordering of operations to prevent race conditions.
* **Happens-Before:** A specific JMM guarantee. If operation A "happens-before" operation B, the memory effects of A are guaranteed to be visible to B, and the JVM/CPU is forbidden from reordering them. For example, a write to a `volatile` variable happens-before any subsequent read of that same variable.

### 13. Storing Passwords in `char[]` vs `String`
Strings are immutable and stored in the String Pool. Once a password is in a String, it remains in memory until the Garbage Collector clears it, which could leave it exposed in a heap dump. A `char[]` (character array) can be explicitly overwritten (e.g., filled with zeros or empty spaces) immediately after use, drastically reducing the window of vulnerability.

### 14. Compare-and-Swap (CAS)
CAS is an atomic, lock-free CPU instruction used heavily in concurrent programming (like Java's `java.util.concurrent.atomic` package). It updates a variable only if its current value in memory matches an *expected* value. If they match, the memory is updated; if they don't (because another thread changed it), the operation fails, and the thread typically retries the operation in a loop (spinlock).

### 15. `String`, `StringBuilder`, and `StringBuffer`
* **`String`:** Immutable. Every time you modify it, a completely new object is created in memory.
* **`StringBuilder`:** Mutable and not thread-safe. It is highly efficient and should be the default choice for string manipulation within a single thread.
* **`StringBuffer`:** Mutable and thread-safe (its core methods are `synchronized`). It is slower than `StringBuilder` and is rarely used in modern Java unless thread safety for string manipulation is strictly required.

### 16. Pattern Matching Enhancements in Java 23
Java 23 (via JEP 455, as a preview feature) significantly expanded pattern matching to support **all primitive types** (like `boolean`, `long`, `double`, `float`). Previously, pattern matching and `instanceof` were restricted to reference types, and `switch` statements were limited to a small subset of primitives (int, short, byte, char). This update allows for seamless, safe primitive type checks and variable extraction within `switch` and `instanceof` blocks, as well as when deconstructing records.

### 17. Handling Distributed Transactions in Microservices
The best practice is to avoid them through bounded context design. When unavoidable, the **Saga Pattern** is the standard approach. A Saga breaks a larger transaction into a sequence of local database transactions. Each step publishes an event to trigger the next step (choreography) or is managed by a central controller (orchestration). If a step fails, the Saga executes **compensating transactions** to roll back the previous steps.

### 18. Role of API Gateway in Spring Boot
An API Gateway (like Spring Cloud Gateway) acts as the single entry point for all client requests into the microservices architecture. It handles:
* **Routing:** Forwarding requests to the correct underlying microservice.
* **Cross-Cutting Concerns:** Centralizing authentication, authorization, SSL termination, and CORS.
* **Resilience & Traffic Management:** Rate limiting, load balancing, and circuit breaking.

### 19. Service Discovery using Spring Cloud Netflix Eureka
Eureka acts as a dynamic phonebook for microservices.
* **Eureka Server:** The central registry where all microservices list their network locations (IP and port) upon startup.
* **Eureka Client:** Microservices use this to self-register and to fetch the registry. This allows services to find and communicate with each other dynamically without hardcoded URLs, enabling seamless scaling and client-side load balancing.

### 20. Spring Cloud Config Server
It provides a centralized server to manage external configuration properties (YAML/properties files) for all microservices across different environments (dev, staging, prod). Configurations are typically backed by a version control system like Git. Microservices connect to the Config Server at startup to fetch their settings, allowing you to update properties dynamically without having to rebuild or redeploy the application packages.

### 21. Circuit Breaker Pattern and Implementation
* **Concept:** It prevents cascading failures in a microservices architecture. If a downstream service fails repeatedly, the circuit breaker trips (opens), and subsequent calls immediately fail fast or return a fallback response, giving the failing service time to recover.
* **Implementation:** In Spring Boot, this is typically implemented using the **Resilience4j** library (which replaced Hystrix). You configure thresholds (e.g., failure rate) and annotate the calling method with `@CircuitBreaker(name = "serviceName", fallbackMethod = "fallback")`.

### 22. Saga Pattern for Distributed Transactions
The Saga pattern handles distributed transactions by breaking them into a sequence of local database transactions. Each local transaction updates the database and publishes an event to trigger the next step.
* **Choreography:** Services listen to each other's events directly.
* **Orchestration:** A central controller tells each service what local transaction to execute.
  If a step fails, the Saga executes **compensating transactions** to reverse the previous successful operations, maintaining eventual consistency.

### 23. Implementing Fault Tolerance in Spring Boot
Fault tolerance ensures a system remains operational despite partial failures. In Spring Boot, this is primarily achieved using **Resilience4j**, which provides:
* **Circuit Breakers:** To stop routing traffic to failing services.
* **Retries:** To automatically re-attempt transient failed requests.
* **Timeouts:** To ensure threads aren't blocked indefinitely waiting for a response.
* **Bulkheads:** To limit the number of concurrent calls to a service, preventing a single slow service from exhausting system resources.

### 24. `@RestController` vs `@Controller`
* **`@Controller`:** Used in traditional Spring MVC applications. The methods return a `String` representing the name of a view (like a JSP or Thymeleaf template) that the ViewResolver will render.
* **`@RestController`:** A convenience annotation that combines `@Controller` and `@ResponseBody`. It bypasses the view resolution phase, and the returned objects are automatically serialized directly into the HTTP response body (usually as JSON or XML).

### 25. Lazy Initialization in Spring Boot
* **Concept:** By default, Spring eagerly creates all singleton beans at startup. Lazy initialization (`spring.main.lazy-initialization=true`) delays bean creation until the bean is actually requested for the first time.
* **Impact:** It significantly reduces application startup time and initial memory footprint. However, the first HTTP request that triggers the bean creation will experience a delay (warm-up penalty), and application misconfigurations might not be discovered until runtime instead of startup.

### 26. Optimizing Spring Boot Startup Time
To optimize startup time, you can:
* Enable **Lazy Initialization** (as mentioned above).
* Use **Spring Boot 3 AOT (Ahead-of-Time) compilation** and GraalVM to compile the application into a native executable, offering near-instant startup.
* Reduce `@ComponentScan` scope to avoid unnecessary classpath scanning.
* Exclude unused auto-configuration classes using `@SpringBootApplication(exclude = {...})`.
* Unpack the executable "fat JAR" to avoid the overhead of decompressing it at runtime.

### 27. Spring Cloud Gateway vs Zuul
* **Zuul 1.x** is built on the older Servlet API, making it **synchronous and blocking**. It assigns one thread per request, which can lead to resource exhaustion under heavy load.
* **Spring Cloud Gateway** is built on Spring WebFlux and Project Reactor. It is **asynchronous and non-blocking**, making it highly efficient, capable of handling massive numbers of concurrent connections with a much smaller thread pool, and is the actively recommended standard by the Spring team (since Zuul 1 is EOL).

### 28. Purpose of Spring Boot Actuator Metrics
Actuator provides built-in, production-ready REST endpoints to monitor and manage your application.
* **Purpose:** It exposes internal metrics (like JVM memory, CPU usage, thread states, garbage collection details, and HTTP request timings) via the `/actuator/metrics` or `/actuator/prometheus` endpoints. These metrics are typically scraped by monitoring systems like Prometheus and visualized in dashboards like Grafana.

### 29. Distributed Tracing (Sleuth/Micrometer and Zipkin)
*Note: For a modern interview, mention that Spring Cloud Sleuth was deprecated in Spring Boot 3.0 and replaced by **Micrometer Tracing**.*
* **Micrometer Tracing:** Automatically generates and propagates a **Trace ID** (unique to the entire user request) and a **Span ID** (unique to each microservice hop) through HTTP headers.
* **Zipkin:** A backend system that collects these traces, aggregates them, and provides a UI to visualize the entire request journey, helping developers pinpoint latency bottlenecks and identify exactly where an error occurred in a chain of microservice calls.

### 30. Monolithic vs SOA vs Microservices
* **Monolithic Architecture:** A single, tightly coupled codebase deployed as one unit. Easy to develop initially, but hard to scale, deploy, and maintain as the team and application grow.
* **SOA (Service-Oriented Architecture):** An enterprise-wide approach where coarse-grained services share centralized resources, often communicating through a heavy Enterprise Service Bus (ESB) using protocols like SOAP. It promotes reusability across the entire enterprise.
* **Microservices Architecture:** An application-specific approach consisting of small, fine-grained, independently deployable services built around business capabilities. They manage their own databases, communicate via lightweight protocols (REST/gRPC/Messaging), and allow for decentralized, agile development.