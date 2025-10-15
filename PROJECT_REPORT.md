# Project Report: Kaiburr Assessment Task 1 - Java REST API

## 1. Introduction
This project implements a production-ready RESTful API using Spring Boot and MongoDB to manage and execute shell command oriented "tasks". Each task encapsulates a command, owner, and execution history. The system ensures safe execution by validating commands and persistently storing state in a NoSQL database. This report provides a comprehensive overview of architecture, design decisions, implementation, testing, and future improvements.

## 2. Objectives
- Provide CRUD operations for task entities
- Allow searching tasks by name substring
- Execute only safe, whitelisted shell commands and record output
- Maintain execution history with timestamps and results
- Persist all data using MongoDB for flexibility and scalability
- Ensure clean error handling and input validation

## 3. High-Level Architecture
```
+----------------------+          +-----------------------+
|  REST Client (curl, |  HTTP    |  Spring Boot          |
|  Postman, etc.)     +--------->+  Controllers          |
+----------------------+          |     |                 |
                                  |  Services            |
                                  |     |                 |
                                  |  Repositories        |
                                  +-----+----------------+
                                        |
                                        | MongoDB Driver
                                        v
                                 +---------------+
                                 |   MongoDB     |
                                 +---------------+
```

### Layers
1. Controller Layer: Exposes REST endpoints (`TaskController`).
2. Service Layer: Business logic for task creation, validation, execution (`TaskService`, `CommandExecutorService`, `CommandValidatorService`).
3. Repository Layer: Persistence abstraction using Spring Data Mongo (`TaskRepository`).
4. Model Layer: Domain objects (`Task`, `TaskExecution`).
5. Exception Handling: Centralized error handling (`GlobalExceptionHandler`).

## 4. Data Model
### Task
- `id`: Unique identifier (MongoDB ObjectId string)
- `name`: Human-readable name (must be non-empty, unique)
- `owner`: Person creating the task
- `command`: Whitelisted shell command (e.g., `echo`, `date`, `uname`)
- `taskExecutions`: List of execution entries

### TaskExecution
- `startTime`: ISO timestamp when execution begins
- `endTime`: ISO timestamp when execution ends
- `output`: Captured standard output from the executed command

### Rationale for MongoDB
- Document structure fits task + embedded execution history naturally
- Avoids joins; each task document contains its execution history
- JSON-like storage simplifies evolution (adding fields later)

## 5. Command Validation & Security
Command execution surfaces a potential security risk. To mitigate:
- Whitelisting: Only a controlled set of safe commands is permitted (basic system info or harmless output commands).
- Rejection of metacharacters: Block characters like `;`, `|`, `&&`, `>`, `<`, `*`, `$`, `(`, `)`, `rm`, `sudo` to prevent injection or destructive operations.
- Separation of concerns: Validation handled by `CommandValidatorService`; execution via `CommandExecutorService`.
- No user-provided arguments beyond allowed simple tokens.

## 6. REST API Endpoints
| Method | Endpoint | Purpose |
|--------|----------|---------|
| PUT | `/tasks` | Create or update a task |
| GET | `/tasks` | List all tasks or get one by `id` query param |
| GET | `/tasks/search?name=...` | Search tasks by name substring (case-insensitive) |
| PUT | `/tasks/{id}/execute` | Execute a task and append execution result |
| DELETE | `/tasks/{id}` | Delete a task permanently |

### Design Choices
- `PUT /tasks`: Idempotent creation/update without separate POST for simplicity in assessment context.
- `/execute` as sub-resource: Clear intent and action semantics.
- Query parameter `name` for search keeping path semantic.

## 7. Error Handling
Implemented via `GlobalExceptionHandler` to convert exceptions into structured JSON responses with fields: `timestamp`, `status`, `error`, `message`, `path`.
Common error scenarios:
- Task not found (`404`)
- Validation error (`400`)
- Unsafe command attempted (`400`)
- Internal execution failure (`500`)

## 8. Execution Flow
### Creating a Task
1. Client sends `PUT /tasks` with JSON body.
2. Controller delegates to `TaskService`.
3. Service checks if task with same name exists; returns existing or creates new.
4. Repository persists task document.
5. Response returned with `201 Created` or `200 OK` depending on semantics.

### Executing a Task
1. Client triggers `PUT /tasks/{id}/execute`.
2. Service fetches the task or throws `TaskNotFoundException`.
3. Command validated; if invalid -> error.
4. Command executed using `ProcessBuilder` (or similar) capturing stdout.
5. Execution timestamps recorded; appended to task’s `taskExecutions` list.
6. Updated task persisted and returned.

## 9. Testing & Verification
Manual testing performed with PowerShell `Invoke-RestMethod` and documented in `TESTING_GUIDE.md`.
Screenshots captured for:
1. Task creation
2. Unsafe command rejection
3. Listing tasks
4. Searching
5. Executing a task
6. Viewing execution history
7. Deleting a task

## 10. Non-Functional Considerations
- Performance: Lightweight operations; MongoDB suitable for high read/write concurrency.
- Scalability: Stateless Spring Boot; can horizontally scale behind a load balancer; MongoDB supports replication.
- Maintainability: Clear package structure and separation of concerns.
- Observability: Logging set to `INFO`; can extend with metrics (Micrometer) later.

## 11. Environment & Deployment
- Local: Docker Compose spins up MongoDB; application runs via Maven Wrapper.
- Production (future): Could containerize Spring Boot app using a Dockerfile and orchestrate both services with Kubernetes or ECS.

## 12. Potential Improvements
1. Add pagination for task listing.
2. Add authentication & authorization (e.g., Spring Security + JWT).
3. Support parameterized commands with strict schema validation.
4. Add execution timeout and resource limits.
5. Persist stderr output and exit codes.
6. Add indexing on `name` for faster search queries.
7. Provide OpenAPI/Swagger documentation.
8. Introduce automated unit/integration tests (JUnit + Testcontainers for MongoDB).
9. Track execution status (e.g., SUCCESS / FAILURE).
10. Implement soft delete or archiving.

## 13. Risks & Mitigations
| Risk | Mitigation |
|------|------------|
| Command injection | Strict whitelist & validation |
| Data loss | Use MongoDB volume + backups |
| Performance degradation at scale | Add indexing, caching layer |
| Lack of audit trail | Extend model with actor + timestamps |
| Unhandled failures during exec | Wrap process handling with try/catch and record errors |

## 14. Why These Technologies?
- **Spring Boot:** Rapid development, embedded server, mature ecosystem.
- **MongoDB:** Flexible schema for embedding execution history; easy horizontal scaling.
- **Maven Wrapper:** Ensures consistent build tool version across environments.
- **Docker Compose:** Simple orchestration of dependency (MongoDB) locally.
- **PowerShell/Invoke-RestMethod:** Native Windows testing without external tools.

## 15. Developer Information
**Author:** Adithya N Reddy  
B.Tech, Amrita School of Engineering, Bengaluru  
Branch: Electronics and Computer Engineering  
Enrollment: BL.EN.U4EAC22075  
Email: adithyasnr@gmail.com

## 16. Conclusion
The project achieves its goal of providing a safe, maintainable, and extensible REST API for creating and executing shell command tasks. It balances simplicity with security via validation and clean layering. Future enhancements can elevate it toward production-grade observability, security, and resilience.

---
*End of Report*
