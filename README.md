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
<img width="1315" height="289" alt="02_unsafe_command" src="https://github.com/user-attachments/assets/08cf1cd2-934b-4025-82f0-f6cb4a6a69f7" />


### 3. Get All Tasks
<img width="646" height="289" alt="03_get_all_tasks_owner" src="https://github.com/user-attachments/assets/1e432023-fd14-4175-9810-3270d59cbe43" />


### 4. Search for a Task by Name
<img width="1315" height="193" alt="04_search_tasks_owner" src="https://github.com/user-attachments/assets/5085ea5f-3613-430b-b319-e61e8f4c49a0" />


### 5. Execute a Task
<img width="646" height="289" alt="05_execute_task_owner" src="https://github.com/user-attachments/assets/b3a3f402-74d6-4814-bb54-959a5d8252ed" />


### 6. Verify Task Execution History
<img width="646" height="289" alt="06_execution_history_owner" src="https://github.com/user-attachments/assets/964ab5ca-1cde-43a2-adb2-b3db6312cd15" />



