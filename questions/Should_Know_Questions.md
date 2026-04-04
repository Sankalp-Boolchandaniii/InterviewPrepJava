# Interview Prep Notes - Java Backend Developer (3 Years Experience)

## Core Java

### Q1. Difference between HashMap, Hashtable, ConcurrentHashMap
- **HashMap**: Non-synchronized, not thread-safe, allows one null key and multiple null values. Faster in single-threaded use.
- **Hashtable**: Synchronized, thread-safe but with high contention, does not allow null keys or values. Considered legacy.
- **ConcurrentHashMap**: Provides better performance in multithreaded environments using bucket-level locking (segmenting). No null keys/values, but safe for concurrent reads/writes.

### Q2. How does Garbage Collection work in JVM?
- GC automatically reclaims memory from unreachable objects in the heap.
- JVM divides memory into Young Generation (new objects), Old Generation (long-lived objects), and Metaspace (class metadata).
- Common algorithms: Mark-and-Sweep, Copying, Generational GC.
- Developers can tune GC with JVM flags, but collection is ultimately managed by JVM.

### Q3. Volatile vs Synchronized
- **volatile**: Guarantees visibility of variable updates across threads (each read is from main memory, not CPU cache). Does not ensure atomicity.
- **synchronized**: Ensures mutual exclusion (atomicity) and visibility, only one thread can execute a synchronized block/method at a time.
- Use volatile for lightweight flag updates, synchronized for critical sections.

### Q4. Deep Copy vs Shallow Copy
- **Shallow Copy**: Copies only references for nested objects. Changes in the nested object reflect in both. (e.g., clone() default in Java).
- **Deep Copy**: Creates a completely new copy of object + nested objects. Independent from original. Can be implemented manually or via serialization libraries.

### Q5. Java Memory Model (Heap, Stack, Metaspace)
- **Heap**: Stores objects. Managed by GC. Shared across all threads.
- **Stack**: Stores method calls, local variables, references. Each thread has its own stack.
- **Metaspace**: Stores class metadata and static info, replaces PermGen.
- In Spring Boot, beans live in the heap, and Spring manages their lifecycle (init, DI, destruction).

## Spring / Spring Boot

### Q6. What is Dependency Injection?
- A design pattern where Spring injects objects (dependencies) into classes instead of manually creating them.
- Promotes loose coupling, testability, and easier configuration.
- Types: Constructor Injection (recommended), Setter Injection, Field Injection.

### Q7. Difference: @Component, @Service, @Repository
- **@Component**: Generic stereotype for a Spring bean.
- **@Service**: A specialization of @Component, indicates business logic service classes.
- **@Repository**: Also a specialization, specifically for DAO layer, adds persistence exception translation (converts DB exceptions to Spring's DataAccessException).

### Q8. How does Spring Boot Auto-Configuration work?
- Spring Boot uses @EnableAutoConfiguration + spring.factories.
- It scans the classpath and applies default bean configurations based on present dependencies (e.g., if spring-webmvc is found, DispatcherServlet auto-configures).
- Developers can override defaults by defining their own beans or disabling specific auto-config.

### Q9. Exception Handling Best Practices in REST APIs
- Use @ControllerAdvice with @ExceptionHandler to centralize error handling.
- Always return consistent error responses with details like timestamp, error code, message.
- Avoid exposing stack traces or DB errors.
- For validation, use @Valid + @ExceptionHandler(MethodArgumentNotValidException.class).

### Q10. How to secure REST APIs?
- **JWT**: Encoded token passed in Authorization header, stateless and scalable.
- **OAuth2**: Provides delegated authorization (used in login with Google, GitHub, etc.).
- Spring Security provides filters for authentication & authorization.
- For production, always use HTTPS and rotate/expire tokens.

## JPA / Hibernate

### Q11. Lazy vs Eager Loading
- **Lazy**: Associations are loaded only when accessed, saving performance if data is not always needed. (Default in Hibernate).
- **Eager**: Associations are fetched immediately with the parent entity, which can cause performance issues with large graphs.
- Best practice: prefer Lazy and explicitly fetch only what's needed.

### Q12. N+1 Query Problem
- Occurs when fetching parent entities triggers one query for parents + multiple queries (N) for each child collection.
- Example: Fetching 100 users → 1 query for users + 100 queries for addresses.
- Fix: Use JOIN FETCH, EntityGraph, or batch fetching to load children in fewer queries.

### Q13. How does Hibernate manage transactions?
- Hibernate integrates with JPA and Spring.
- Uses EntityManager/Session to track changes, and flushes them on commit.
- Transactions can be declarative via @Transactional in Spring.
- Supports rollback on exceptions and propagation levels.

### Q14. Entity Lifecycle States
- **Transient**: Newly created, not saved in DB.
- **Persistent**: Managed by EntityManager, changes auto-synced.
- **Detached**: Was persistent, but EntityManager closed/cleared.
- **Removed**: Marked for deletion, removed on transaction commit.

## SQL & Database

### Q15. Query – 2nd Highest Salary
```sql
SELECT MAX(salary)
FROM employees
WHERE salary < (SELECT MAX(salary) FROM employees);
```

### Q16. INNER vs LEFT vs RIGHT JOIN
- **INNER**: Common records.
- **LEFT**: All from left + matching right.
- **RIGHT**: All from right + matching left.

### Q17. Indexes (Clustered vs Non-clustered)
- **Clustered**: Alters table's physical order (1 per table).
- **Non-clustered**: Separate structure pointing to table rows (many allowed).

### Q18. Optimize slow queries
- Add indexes, avoid SELECT *, use proper joins, analyze execution plan.

## DSA / Problem Solving (Concepts)

- **LRU Cache**: Use LinkedHashMap or Deque + HashMap.
- **Cycle Detection**: Floyd's slow/fast pointer.
- **Longest Substring w/o repeating**: Sliding window + HashSet/Map.
- **Merge Intervals**: Sort + iterate merging overlapping ones.
- **Rate Limiter**: Token bucket / Leaky bucket / Redis counters.

## Microservices

### Q19. Advantages of Microservices over Monolith
- Independent deployment and scaling of services.
- Easier fault isolation (failure in one service doesn't break entire app).
- Teams can work independently on services with different tech stacks.
- Faster release cycles and CI/CD adoption.

### Q20. Inter-service Communication
- **REST/gRPC**: Synchronous, useful when real-time response is required.
- **Messaging (Kafka, RabbitMQ)**: Asynchronous, better for decoupling and handling spikes in traffic.
- Often systems use hybrid approaches depending on business needs.

### Q21. Synchronous vs Asynchronous Communication
- **Synchronous**: Caller waits for response (e.g., REST API call). Easier but may cause latency.
- **Asynchronous**: Caller sends message/event and continues processing, response handled later (e.g., Kafka). Improves scalability but adds complexity.

### Q22. Ensuring Idempotency in APIs
- Idempotent APIs return the same result even if called multiple times.
- Achieved using unique request IDs, PUT for updates, check-before-insert logic, or using tokens for one-time operations.

### Q23. Spring Cloud Config
- Centralized external configuration, versioned in Git, fetched at runtime.

### Q24. Circuit Breaker (Resilience4j/Hystrix)
- Prevents continuous retries to a failing service.
- Monitors calls; if failure rate crosses threshold, opens circuit → fails fast instead of overloading.
- Supports auto-recovery (half-open state). Ensures system resilience.

### Q25. FeignClient vs RestTemplate vs WebClient
- **FeignClient**: Declarative REST client.
- **RestTemplate**: Synchronous blocking calls.
- **WebClient**: Reactive, async non-blocking.

### Q26. Centralized Logging
- Use ELK stack (Elasticsearch, Logstash, Kibana) or Zipkin/Jaeger for tracing.

### Q27. Distributed Transactions
- Use Saga pattern, 2PC (rare), Outbox pattern.

### Q28. Fault Tolerance
- Circuit breakers, retries, bulkheads, fallback logic.

### Q29. Data Consistency Across Services
- In microservices, distributed transactions are avoided.
- Use eventual consistency via events, Saga pattern, or Outbox pattern.
- For critical operations, implement compensating transactions.

### Q30. Service Discovery
- Eureka, Consul, or K8s DNS for locating services dynamically.

## Security

### Q31. JWT
- Token with header + payload + signature, validates user identity without session.

### Q32. OAuth2
- Delegated authorization framework (access tokens + refresh tokens).

### Q33. API Gateway + Auth
- API Gateway handles auth, rate limiting, routing. JWT/OAuth validated at gateway.

## Data & Messaging

### Q34. Database per Service
- Each service has its own DB to avoid tight coupling.
- Prevents one service from blocking others and allows polyglot persistence.
- Data consistency is handled at the service level using messaging/events.

### Q35. Schema Migrations
- Tools like Flyway, Liquibase.

### Q36. Kafka vs RabbitMQ
- **Kafka**: Distributed log, high throughput, best for event streaming & real-time analytics.
- **RabbitMQ**: Queue-based, message broker with routing and reliability features. Better for task distribution.
- Kafka is pull-based, RabbitMQ is push-based.

### Q37. Kafka Ordering & Delivery Guarantees
- Ordering per partition.
- Guarantees: at-most-once, at-least-once, exactly-once (via configs).

## Miscellaneous Spring & Java

- **Circuit Breaker**: Stops repeated failing calls.
- **Bean Scopes**: Singleton, Prototype, Request, Session, Application.
- **Profiles**: Environment-specific beans/configs.
- **@SpringBootApplication**: Combines @Configuration, @EnableAutoConfiguration, @ComponentScan.
- **Serialization**: Converting object → byte stream (implements Serializable).
- **transient keyword**: Skips field during serialization.
- **Exception Handling in Spring Boot**: @ControllerAdvice + ResponseEntityExceptionHandler.
- **Database annotations**: @Entity, @Table, @Id, @GeneratedValue, @Column.
- **HashMap internal**: Array of buckets + linked list/tree (hashing + resizing).
- **ArrayList vs LinkedList**: ArrayList – random access, better for reads. LinkedList – faster inserts/deletes.
- **Optional**: Avoids null checks, better readability.
- **Java 8 features**: Streams, Lambdas, Optional, Date/Time API, default methods.
- **Default methods in interfaces**: Allow method body in interfaces without breaking old code.
- **@RestController vs @Controller**: @RestController = @Controller + @ResponseBody.
- **@PostMapping vs @PutMapping**: POST = create resource, PUT = update/replace resource.
- **GROUP BY vs ORDER BY**: GROUP BY aggregates, ORDER BY sorts.
- **Securing Spring Boot**: Use Spring Security, JWT/OAuth2, CSRF protection.

### Q38. Fail Fast & Fail Safe
- Fail-Fast is about immediate alerting and stopping (high consistency).
- Fail-Safe is about continued operation via alternatives (high availability)

### Q: Stream expression to print square of even numbers
```java
list.stream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * n)
    .forEach(System.out::println);
```
