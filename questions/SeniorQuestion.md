Here is your definitive, fully sorted **40-Question Master Cheat Sheet**, ranked from the absolute most critical "make-or-break" topics down to the standard baseline knowledge.

---

### 🔥 Tier 1: System Design, Scalability & Production Outages

*These are the questions that determine if you get a Senior/Lead offer. They test your battle scars in production.*

**1. (Q40) What Spring Boot decision has caused you a real production issue?**

* **Core Answer:** Leaving `spring.jpa.open-in-view=true` enabled. It holds database connections open during network JSON serialization. Slow mobile clients will exhaust your DB connection pool and crash the app.

**2. (Q21) How do you control thread usage in Spring Boot applications?**

* **Core Answer:** In 2026, enable Virtual Threads (`spring.threads.virtual.enabled=true`) for I/O-bound tasks. Otherwise, strictly separate your Tomcat pool (inbound requests) from your `@Async` `ThreadPoolTaskExecutor` to prevent background tasks from starving the web server.

**3. (Q8 & Q36) One microservice goes down and others start failing—how do you handle partial failures?**

* **Core Answer:** Use the **Circuit Breaker** pattern (Resilience4j) to fail fast and return a fallback, and the **Bulkhead** pattern to isolate thread pools so a slow downstream service doesn’t consume all your main app's resources.

**4. (Q7) Database connections are getting exhausted - how do you detect and fix this?**

* **Core Answer:** Monitor HikariCP metrics (`hikaricp.connections.pending`). Fixes include disabling OSIV, shrinking transaction boundaries (don't do network calls inside `@Transactional`), and tuning the pool size.

**5. (Q4) Under heavy traffic, your service starts crashing - what are the likely bottlenecks?**

* **Core Answer:** Thread exhaustion (Tomcat pool full), Connection exhaustion (DB pool full), or Memory exhaustion (Heap filling up leading to GC "Stop-the-World" pauses).

**6. (Q20) Why does your API return correct data but response time fluctuates wildly?**

* **Core Answer:** "Jitter" is caused by waiting. Look for **Garbage Collection Pauses**, **Connection Pool Contention** (waiting in line for a DB connection), or **Database Row Locks**.

**7. (Q2) An API is fast in dev but painfully slow in prod - how do you trace the root cause?**

* **Core Answer:** Use Distributed Tracing (Micrometer Tracing/OpenTelemetry) to inject `traceId` and `spanId` into logs. This visualizes exactly where the time is spent (e.g., a slow DB query vs a slow downstream API).

**8. (Q9 & Q11) Memory usage keeps growing over time - what do you immediately suspect?**

* **Core Answer:** A Memory Leak. Common culprits: `ThreadLocal` variables not being cleared, unbounded caching (using `HashMap` instead of Caffeine), or unclosed I/O streams. Take a Heap Dump (`-XX:+HeapDumpOnOutOfMemoryError`) to find the retained objects.

**9. (Q25) Why does Hibernate generate unexpected queries?**

* **Core Answer:** The **N+1 Select Problem** (looping through lazy-loaded entities; fix with `JOIN FETCH`) or **Implicit Dirty Checking** (Hibernate issuing `UPDATE` statements because a managed entity was modified inside a transaction).

**10. (Q26) How do you debug a deadlock in a Spring Boot service?**

* **Core Answer:** Java-level deadlocks require a Thread Dump (`jstack`) to find the cycle. DB-level deadlocks require checking DB logs (`SHOW ENGINE INNODB STATUS`) and ensuring consistent row-locking order.

**11. (Q38) How do you prevent breaking changes during deployments?**

* **Core Answer:** The **Expand and Contract (Parallel Change)** pattern. Never rename or drop a DB column/API field in one step. Add the new one, write to both, read from the new, then eventually deprecate and drop the old.

**12. (Q17) Why does your app behave differently after scaling pods?**

* **Core Answer:** The app is stateful. It might be storing sessions in RAM (`HttpSession`) instead of a distributed cache like Redis, or running scheduled jobs concurrently across all pods (fix with ShedLock).

**13. (Q24) How do you handle large payloads without killing performance?**

* **Core Answer:** Never load large files into a `byte[]` (Buffering). **Stream** inputs using `InputStream` and stream database outputs using JDBC Cursors (`Stream<T>`) to keep memory usage at O(1).

---

### 🚀 Tier 2: Internals, Framework "Gotchas", & Lifecycle

*These questions expose whether you truly understand how Spring uses proxies, reflection, and bean lifecycles under the hood.*

**14. (Q31) Why does @Cacheable (or @Transactional) sometimes not trigger?**

* **Core Answer:** The **Self-Invocation Trap**. Calling a method from within the *same class* bypasses the Spring Proxy. These annotations only work when called from an external bean.

**15. (Q23) Why do custom exception handlers (@ControllerAdvice) sometimes not trigger?**

* **Core Answer:** The exception happened outside the `DispatcherServlet`. If an error occurs in a Servlet Filter (e.g., JWT validation) or Spring Security, `@ControllerAdvice` will never see it.

**16. (Q27) What happens if a BeanFactoryPostProcessor fails?**

* **Core Answer:** The application **crashes immediately** during startup. BFPPs modify the bean definitions (the blueprints). If they fail, Spring refuses to build the context.

**17. (Q13) What happens if @PostConstruct throws an exception?**

* **Core Answer:** The specific bean fails to initialize, which bubbles up and halts the entire ApplicationContext startup. The app crashes.

**18. (Q12) How do you detect bean initialization issues in large applications?**

* **Core Answer:** Enable the `BufferingApplicationStartup` metric and check the `/actuator/startup` endpoint to pinpoint exactly which beans are taking the longest to initialize.

**19. (Q15) How does Spring Boot decide the order of auto-configurations?**

* **Core Answer:** Using `@AutoConfigureBefore`, `@AutoConfigureAfter`, `@AutoConfigureOrder`, and topological sorting in the background.

**20. (Q18 & Q33) How does Spring handle classpath scanning and fat JAR classloaders?**

* **Core Answer:** Spring uses a custom `LaunchedURLClassLoader` to read nested JARs (`BOOT-INF/lib`). Libraries relying on standard `File` I/O will crash; you must use `classLoader.getResourceAsStream()`.

**21. (Q19) What causes duplicate bean registration in multi-module projects?**

* **Core Answer:** Package overlap. A blanket `@ComponentScan` from the main app picks up a shared module, and an explicit `@Import` tries to load it a second time.

**22. (Q37) What is the real impact of using too many interceptors?**

* **Core Answer:** Latency multiplication. If interceptors make DB calls for auth/logging, you multiply your DB load before the Controller even begins processing.

**23. (Q34) How do you safely reload configs without restarting?**

* **Core Answer:** Annotate the bean with `@RefreshScope` and trigger a `POST /actuator/refresh`. Spring destroys the old bean and creates a new one (wiping its internal state in the process).

**24. (Q6) Your API randomly returns 401 even though logic hasn't changed - where do you look?**

* **Core Answer:** Check the Spring Security `SecurityContextHolder`. This is often a context propagation issue where a spawned background thread loses the security context of the parent HTTP request.

---

### 🛠️ Tier 3: Configuration, Environment, & Tooling

*This is the baseline knowledge. Interviewers expect quick, accurate answers here.*

**25. (Q1) Your app runs perfectly on local, but fails after deployment - what do you verify first?**

* **Core Answer:** Check the active **Profiles** (`SPRING_PROFILES_ACTIVE`) and ensure environment variables (DB credentials, external URLs) are injected correctly in the host environment.

**26. (Q16) What are the risks of enabling too many Actuator endpoints?**

* **Core Answer:** Severe security risks. Endpoints like `/env` can leak passwords/API keys, and `/heapdump` can leak PII and user tokens currently stored in memory.

**27. (Q30) How do you manage feature toggles safely?**

* **Core Answer:** Use `@ConditionalOnProperty` for startup-level architectural swaps, or dynamic tools (LaunchDarkly) for runtime UI features. Always delete old toggles to avoid "Toggle Hell."

**28. (Q10 & Q35) Why does logging behave differently in prod vs local?**

* **Core Answer:** Different Profiles trigger different Appenders. Local uses synchronous `ConsoleAppender` (colorful, readable). Prod uses Asynchronous JSON Appenders for high-throughput log aggregation (ELK).

**29. (Q3 & Q22) You updated application.properties but nothing changed, or you have both yml and properties—what happens?**

* **Core Answer:** `application.properties` has higher precedence and will overwrite `application.yml`. Profile-specific files (`application-prod.yml`) overwrite general files.

**30. (Q14 & Q39) Why does @Value or @ConfigurationProperties fail silently?**

* **Core Answer:** `@ConfigurationProperties` is lenient by default; you must add `@Validated` and `@NotNull` to force a crash on missing configs. For `@Value`, use defaults (`${key:default}`) to prevent startup crashes.

**31. (Q28) How do you avoid startup failure due to missing configs?**

* **Core Answer:** Provide inline defaults, map configs to a POJO with pre-set default fields, or use `Optional<T>` in the constructor injection.

**32. (Q5) Spring complains about multiple beans of the same type - how do you resolve it?**

* **Core Answer:** Use `@Qualifier("beanName")` to specify exactly which one you want, or mark one of the implementations with `@Primary`.

**33. (Q32) How does Spring Boot isolate environment-specific configs?**

* **Core Answer:** Using the `@Profile` annotation to conditionally load beans, and `application-{profile}.yml` files to overlay environment variables.

**34. (Q29) Why does Spring Boot retry DB connections on startup?**

* **Core Answer:** To survive container orchestration race conditions (e.g., K8s or Docker Compose) where the app boots faster than the database initializes.

---