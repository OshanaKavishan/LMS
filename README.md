# 📚 Library Management System

A desktop-based application to manage library operations such as book issuance, returns, and member tracking. Built with **Java Swing**, connected to a **MySQL** database.

---

## 🌟 Features

### 📊 Dashboard Overview
- Real-time statistics for books and members
- Track total books, issued books, and returned books
- Monitor total member count

### 👥 Member Management
- Add, view, and update member details
- Track membership status

### 📚 Book Management
- Add new books
- Track availability
- Issue and return management
- Search functionality

---

## 🛠️ Tech Stack

| Layer        | Technology        |
|--------------|-------------------|
| Backend      | Java              |
| UI Framework | Java Swing        |
| Database     | MySQL             |
| IDE          | Apache NetBeans   |

### 📦 Dependencies
- `AbsoluteLayout-RELEASE210.jar`
- `jcalendar-1.4.jar`
- `mysql-connector-java-8.0.30.jar`
- `protobuf-java-3.19.4.jar`

---

## 📋 Prerequisites

- Java JDK 21 or above
- MySQL Server
- NetBeans IDE (recommended)
- Maven (for dependency management)

---

## 🚀 Installation & Setup

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/OshanaKavishan/Library-Management-System.git
 ```
### 2️⃣ Extract & Open
- Unzip target.zip (if exists)

- Open the project in NetBeans IDE

### 3️⃣ Database Setup
Run the following SQL in your MySQL server:
```bash
CREATE DATABASE IF NOT EXISTS lms_new;
USE lms_new;

-- Books table
CREATE TABLE IF NOT EXISTS books (
  book_id INT PRIMARY KEY AUTO_INCREMENT,
  book_title VARCHAR(255) NOT NULL,
  author VARCHAR(255),
  publication_year YEAR,
  language VARCHAR(50),
  genre VARCHAR(100),
  copies_available INT DEFAULT 1,
  format VARCHAR(50),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Students table
CREATE TABLE IF NOT EXISTS students (
  student_id INT PRIMARY KEY AUTO_INCREMENT,
  student_name VARCHAR(255) NOT NULL,
  tp_number VARCHAR(50) UNIQUE,
  address TEXT,
  course VARCHAR(100),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Issued books
CREATE TABLE IF NOT EXISTS issued_books (
  issued_id INT PRIMARY KEY AUTO_INCREMENT,
  student_id INT,
  student_name VARCHAR(255),
  book_id INT,
  book_name VARCHAR(255),
  issued_date DATE,
  due_date DATE,
  FOREIGN KEY (student_id) REFERENCES students(student_id),
  FOREIGN KEY (book_id) REFERENCES books(book_id)
);

-- Returned books
CREATE TABLE IF NOT EXISTS returned_books (
  id INT PRIMARY KEY AUTO_INCREMENT,
  student_id INT,
  student_name VARCHAR(255),
  book_id INT,
  book_name VARCHAR(255),
  issue_date DATE,
  due_date DATE,
  return_date DATE,
  condition_on_return VARCHAR(100),
  damage_fees DECIMAL(10,2) DEFAULT 0.00,
  FOREIGN KEY (student_id) REFERENCES students(student_id),
  FOREIGN KEY (book_id) REFERENCES books(book_id)
);
```
### 4️⃣ Configure Database Connection
- Edit ConnectionProvider.java and update the credentials:
```bash
String url = "jdbc:mysql://localhost:3306/lms_new";
String username = "your_username";
String password = "your_password";
```
### 5️⃣ Build & Run
- Build the project in NetBeans
  
-Run login.java

🔐 Username: admin
🔐 Password: admin

## 📁 Project Structure
```bash
  LMS_OOP/
├── Source Packages/
│   ├── ConnectionProvider.java
│   ├── controller/
│   ├── model/
│   └── view/
├── Test Packages/
└── Dependencies/
    ├── AbsoluteLayout-RELEASE210.jar
    ├── jcalendar-1.4.jar
    ├── mysql-connector-java-8.0.30.jar
    └── protobuf-java-3.19.4.jar

```
🧩 Follows the MVC (Model-View-Controller) design pattern

## 👨‍💻 Author
Oshana Kavishan
- 📧 kavishansilva@gmail.com
- 🔗 [linkedin.com/in/oshana-kavishan-9ab10b23b](https://www.linkedin.com/in/oshana-kavishan-9ab10b23b/)
- 💻 [https://github.com/OshanaKavishan](https://github.com/OshanaKavishan)
