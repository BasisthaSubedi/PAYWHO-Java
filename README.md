# 💰 PAYWHO – Java Edition

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-17+-red.svg)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0+-green.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8+-orange.svg)](https://mysql.com/)

A **Spring Boot + MySQL** based expense-splitting web application that allows users to track and manage shared expenses during group trips, events, or hangouts.  
It automatically calculates balances and provides **settlement suggestions** to simplify who owes whom.

---

## ✨ Features

- 🏗️ **Trip Creation** – Create and manage multiple trips or groups  
- 💰 **Expense Tracking** – Add, view, and delete shared expenses  
- 🧮 **Auto Calculation** – Computes each participant’s balance  
- 📊 **Settlement Suggestions** – Shows who should pay whom  
- 💱 **Indian Currency Formatting (₹)**  
- 🌐 **Web Interface using Thymeleaf**  
- 🗄️ **Persistent Storage with MySQL**  

---

## 🛠️ Tech Stack

### Backend
- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA (Hibernate)**
- **MySQL Database**
- **RESTful APIs**

### Frontend
- **Thymeleaf (HTML Template Engine)**
- **Tailwind CSS** for styling
- **JavaScript** for interactivity

### Tools
- **Maven** for dependency management  
- **Spring Boot DevTools** for live reload  
- **Postman** for API testing  

---

## 🧱 Project Structure

```

paywho-java/
├── src/
│   ├── main/
│   │   ├── java/com/paywho/
│   │   │   ├── controller/   # REST controllers
│   │   │   ├── model/        # Entity classes
│   │   │   ├── repository/   # JPA repositories
│   │   │   ├── service/      # Business logic
│   │   │   └── PaywhoApplication.java
│   │   └── resources/
│   │       ├── templates/    # Thymeleaf HTML pages
│   │       ├── static/       # CSS, JS
│   │       └── application.properties
├── pom.xml
└── README.md

````

---

## ⚙️ Setup & Installation

### 1️⃣ Prerequisites
- Java 17 or higher  
- MySQL 8+  
- Maven installed (`mvn -v` to check)

### 2️⃣ Database Configuration

Create a new database in MySQL:

```sql
CREATE DATABASE paywho;
````

In `src/main/resources/application.properties`, configure:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/paywho
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
```

### 3️⃣ Run the Application

```bash
mvn spring-boot:run
```

Then open your browser at:
👉 **[http://localhost:8080](http://localhost:8080)**

---

## 🧮 Expense Calculation Logic

1. Each expense has:

   * Paid amount
   * Paid by (user)
   * Shared among participants
2. Total expenses are divided equally among all participants.
3. Each participant’s net balance = **Amount Paid – Share Owed**
4. Settlement algorithm suggests:

   * Who owes whom
   * How much they should pay

---


## 🧩 System Architecture

```
[Frontend - Thymeleaf UI]
          ↓
[Controller Layer]
          ↓
[Service Layer]
          ↓
[Repository Layer]
          ↓
[MySQL Database]
```

* **Controller** – Handles HTTP requests
* **Service** – Contains business logic for calculation
* **Repository** – Interacts with MySQL database

---

## 🚀 Example Workflow

1. **Create a Trip** → Add participants
2. **Add Expenses** → Enter who paid and amount
3. **View Summary** → See balances and settlements
4. **Settle Up** → Follow settlement recommendations

---

## 📝 License

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**BASISTHA SUBEDI**

* GitHub: [@BasisthaSubedi](https://github.com/BasisthaSubedi)

**This project was developed as a learning experience in Java Spring Boot, focusing on teamwork, backend logic, and full-stack integration.**
