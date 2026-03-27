🚀 Student Management System (Java + JDBC + SQLite)

A console-based Student Management System built using Java with a layered architecture.
This project demonstrates real-world backend concepts like authentication, database integration, and CRUD operations.

---

🔐 Features

- 🔑 User Authentication (Login & Register)
- 🔒 Password Hashing (SHA-256)
- ➕ Add Student
- 📋 View Students (Table Format)
- ❌ Delete Student
- 🔍 Search Student by ID
- 🔎 Search Student by Name
- ✏ Update Student Details
- 💾 Persistent Storage (SQLite Database)

---

🧠 Technologies Used

- Java
- JDBC (Java Database Connectivity)
- SQLite Database
- OOP (Object-Oriented Programming)
- SHA-256 Password Hashing

---

📂 Project Structure

student-management-system-java/
│
├── model/
│   └── Student.java
│
├── service/
│   ├── StudentService.java
│   ├── AuthService.java
│   └── Database.java
│
├── ui/
│   └── Main.java
│
├── users.txt
├── students.db
└── README.md

---

▶️ How to Run

1. Compile all files:

javac model/*.java service/*.java ui/*.java

2. Run the application:

java ui.Main

---

🔐 Default Login

Username: admin
Password: 1234

---

💡 Key Highlights

- Layered architecture (UI → Service → Database)
- Secure password storage using hashing
- Input validation and error handling
- Clean and formatted console UI
- Real database usage (not in-memory)

---

🚀 Future Improvements

- Role-based access (Admin/User)
- GUI (JavaFX or Web UI)
- REST API using Spring Boot
- MySQL integration
- Deployment as a web application

---

👨‍💻 Author

Mohammed Noor

---

⭐ Note

This project is built for learning and demonstrates core backend development concepts in Java. It can be extended into a full-scale system like Campus Hive.
