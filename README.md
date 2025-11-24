````
# Student Course Portal (Microservices Project)  
**Submitted by:** Sidhdhika Banu (ICT/21/814)  

This project implements a **Student Course Portal** using a **Microservices Architecture**.  
Each microservice runs independently, has its own database, and communicates with others via **REST APIs**.

---

## Microservices List

| Service Name        | Port |
|--------------------|------|
| Student Service      | 8081 |
| Course Service       | 8082 |
| Enrollment Service   | 8083 |
| Result Service       | 8084 |
| Notification Service | 8085 |

---

## Prerequisites
Make sure the following are installed and working:

1. **Java 17**  
   Check version:
   ```bash
   java -version
````

2. **Maven**
   Check version:

   ```bash
   mvn -version
   ```

3. **VS Code Extensions** (Recommended)

   * Spring Boot Extension Pack
   * Maven for Java
   * Java Developer Pack

---

## H2 Database

Each microservice uses its **own H2 in-memory database**.
Access the H2 console via:

| Service      | H2 Console URL                                                       |
| ------------ | -------------------------------------------------------------------- |
| Student      | [http://localhost:8081/h2-console](http://localhost:8081/h2-console) |
| Course       | [http://localhost:8082/h2-console](http://localhost:8082/h2-console) |
| Enrollment   | [http://localhost:8083/h2-console](http://localhost:8083/h2-console) |
| Result       | [http://localhost:8084/h2-console](http://localhost:8084/h2-console) |
| Notification | [http://localhost:8085/h2-console](http://localhost:8085/h2-console) |

---

## Running Each Microservice

⚠ **Important:** Open each microservice in its **own VS Code terminal** and run separately.

1. **Student Service (8081)**
   Folder:

   ```
   student-service/
   ```

   Run:

   ```bash
   mvn spring-boot:run
   ```

2. **Course Service (8082)**
   Folder:

   ```
   course-service/
   ```

   Run:

   ```bash
   mvn spring-boot:run
   ```

3. **Enrollment Service (8083)**
   Folder:

   ```
   enrollment-service/
   ```

   Run:

   ```bash
   mvn spring-boot:run
   ```

   ⚠ Must start Student (8081) and Course (8082) first because Enrollment calls them.

4. **Result Service (8084)**
   Folder:

   ```
   result-service/
   ```

   Run:

   ```bash
   mvn spring-boot:run
   ```

5. **Notification Service (8085)**
   Folder:

   ```
   notification-service/
   ```

   Run:

   ```bash
   mvn spring-boot:run
   ```

---

## Testing With Postman

### Student Service

* `GET /students` – Get all students
* `POST /students` – Add a student
* `GET /students/{id}` – Get a student by ID

### Course Service

* `GET /courses` 
* `POST /courses` 
* `GET /courses/{id}` 

### Enrollment Service

* `POST /enrollments` – Enroll a student

* `GET /enrollments/student/{id}`

### Result Service

* `POST /results` – Add a result

* `GET /results/student/{id}` 

### Notification Service

* `POST /notify/enrollment`

```

