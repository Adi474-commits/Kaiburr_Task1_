# Kaiburr Assessment Task 1: Java REST API

This project is a production-ready Java Spring Boot application that exposes REST endpoints to create, manage, and execute shell command "tasks". It uses MongoDB for data persistence and includes robust validation and error handling.

**Submitted by: Adithya N Reddy**  
**Program:** B.Tech, Amrita School of Engineering, Bengaluru  
**Branch:** Electronics and Computer Engineering  
**Enrollment:** BL.EN.U4EAC22075  
**Email:** adithyasnr@gmail.com

---

## Features

-   **CRUD Operations:** Full Create, Read, Update, Delete functionality for tasks.
-   **Task Search:** Search for tasks by name (substring matching).
-   **Secure Command Execution:** Executes whitelisted shell commands (`echo`, `date`, `uname`, etc.) and records the output and timestamps.
-   **Command Validation:** Prevents execution of unsafe commands or commands with shell metacharacters.
-   **MongoDB Persistence:** Uses an embedded document model to store task execution history.
-   **RESTful API:** Follows standard REST principles with appropriate HTTP verbs and status codes.

---

## Tech Stack

-   **Backend:** Java 17, Spring Boot 3
-   **Database:** MongoDB
-   **Build Tool:** Apache Maven
-   **Containerization:** Docker

---

## Local Setup

1.  **Clone the repository:**
    ```bash
    git clone <your-repo-url>
    cd <your-repo-folder>
    ```

2.  **Start the MongoDB database using Docker:**
    Open a terminal in the project root and run:
    ```bash
    docker-compose up -d
    ```
    This will start a MongoDB container and expose it on port `27017`.

3.  **Run the Spring Boot Application:**
    In the same terminal, run the application using Maven Wrapper:
    ```bash
    .\mvnw.cmd spring-boot:run
    ```
    The API server will start on `http://localhost:8081`.

---

## API Reference

The base URL for all endpoints is `http://localhost:8081`.

### 1. Create or Update a Task
-   **Endpoint:** `PUT /tasks`
-   **`curl` Example:**
    ```bash
    curl -X PUT http://localhost:8081/tasks -H "Content-Type: application/json" -d '{"name": "System Info Task", "owner": "Your Name", "command": "uname -a"}'
    ```

### 2. Get All Tasks or a Single Task
-   **Endpoint:** `GET /tasks` or `GET /tasks?id={taskId}`
-   **`curl` Examples:**
    ```bash
    # Get all tasks
    curl http://localhost:8081/tasks

    # Get a single task by its ID
    curl http://localhost:8081/tasks?id=<your_task_id>
    ```

### 3. Search Tasks by Name
-   **Endpoint:** `GET /tasks/search?name={substring}`
-   **`curl` Example:**
    ```bash
    curl "http://localhost:8081/tasks/search?name=Info"
    ```

### 4. Execute a Task
-   **Endpoint:** `PUT /tasks/{id}/execute`
-   **`curl` Example:**
    ```bash
    curl -X PUT http://localhost:8081/tasks/<your_task_id>/execute
    ```

### 5. Delete a Task
-   **Endpoint:** `DELETE /tasks/{id}`
-   **`curl` Example:**
    ```bash
    curl -X DELETE http://localhost:8081/tasks/<your_task_id>
    ```

---

## API Demonstration (Screenshots)

*All screenshots include the system date/time and task owner name for verification.*


### 1. Create a New Task
<img width="1315" height="289" alt="01_create_task" src="https://github.com/user-attachments/assets/b533de44-0b5d-4249-80a5-80ed0dd691af" />


### 2. Attempt to Create a Task with an Unsafe Command
<img width="1315" height="289" alt="ADITHYA REDDY adithyasnr@gmail com (1)" src="https://github.com/user-attachments/assets/6771c833-e93c-490f-8709-6db6227af82d" />


### 3. Get All Tasks
<img width="646" height="289" alt="T1_s3" src="https://github.com/user-attachments/assets/30544ee2-1f80-4978-b0ca-75c2ab9322a4" />


### 4. Search for a Task by Name
<img width="1315" height="193" alt="Task1_4" src="https://github.com/user-attachments/assets/0ff63f71-9baa-4058-945f-63f3dcdb61d7" />


### 5. Execute a Task
<img width="1221" height="277" alt="ADITHYA REDDY adithyasnr@gmail com (2)" src="https://github.com/user-attachments/assets/96b71e87-1a8a-4284-b464-0569895bf9d3" />


### 6. Verify Task Execution History
<img width="1220" height="282" alt="ADITHYA REDDY adithyasnr@gmail com (3)" src="https://github.com/user-attachments/assets/0a06f399-7994-47da-983d-da12ccef2f71" />



