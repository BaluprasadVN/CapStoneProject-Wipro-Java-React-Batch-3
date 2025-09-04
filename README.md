# 🚍 Bus Ticket Reservation Management System

A full-stack **Bus Ticket Reservation System** built as a Capstone Project.  
This project solves the problem of manual ticket booking by providing a centralized, online platform with **real-time seat availability, secure payments, and role-based access** for Admin and Customers.

---

## 📌 Features

### 🔑 Authentication & Security
- JWT-based login & registration
- Role-based access (Admin & Customer)
- Password encryption (BCrypt)

### 👨‍💼 Admin
- Manage buses, routes, and schedules
- Set pricing and seat layouts
- View reports (sales, occupancy, top routes)

### 👥 Customer
- Search for trips
- Real-time seat selection
- Book & pay online
- Download e-tickets (PDF/QR)
- Cancel bookings and request refunds

### 📊 Reports
- Daily sales summary
- Occupancy performance
- Route profitability dashboard

---

## 🛠 Technology Stack

### Backend (Java + Spring Boot)
- Spring Boot (REST APIs)
- Spring Security + JWT
- Hibernate / JPA
- MySQL Database
- Swagger API Docs

### Frontend (React)
- React.js (Hooks + Functional Components)
- React Router
- Axios (API calls)
- Bootstrap 5 + Custom CSS

---

## 📂 Project Structure
CapStoneProject-Wipro-Java-React-Batch-3/
│── backend/ # Spring Boot backend
│── frontend/ # React frontend
│── Project_Report.md
│── README.md

spring.datasource.url=jdbc:mysql://localhost:3306/bus_reservation
spring.datasource.username=root
spring.datasource.password=yourpassword
Run the application:
mvn spring-boot:run

Open Swagger UI at:
👉 http://localhost:8080/swagger-ui/

Frontend (React)

Navigate to frontend folder:

cd frontend

Install dependencies:
npm install

Start React app:
npm start

Open in browser:
http://localhost:3000/

Author

V N Balu Prasad
Capstone Project – Wipro Java React Batch 3
