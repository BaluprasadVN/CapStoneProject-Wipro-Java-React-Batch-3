# Bus Ticket Reservation Management

## 1. Introduction
This project solves the problems of manual bus ticketing (overbooking, no real-time seat visibility, long queues) by providing a centralized online system.

## 2. Objectives
- Real-time seat availability.
- Online booking, payments, and cancellations.
- Role-based access (Admin & Customer).
- Secure authentication (JWT).
- Reports & dashboards for admins.

## 3. Technology Stack
- **Backend:** Spring Boot, Spring Security + JWT, MySQL, JPA/Hibernate, Swagger.
- **Frontend:** React.js, Bootstrap 5, Axios.
- **Other:** Git, Postman for API testing.

## 4. Features
- **Admin:** Manage buses, routes, trips, pricing, reports.
- **Customer:** Search buses, book seats, make payments, download tickets (PDF/QR), cancel tickets.
- **Security:** JWT authentication and authorization.
- **Reports:** Sales summary, occupancy, route performance.

## 5. System Architecture
- React frontend calls Spring Boot REST APIs.
- APIs secured with JWT.
- MySQL stores users, buses, routes, trips, bookings, payments.

## 6. Database Design
Entities: User, Bus, Route, Trip, Seat, Booking, Payment.

## 7. API Documentation
Swagger available at: `http://localhost:8080/swagger-ui/`

## 8. Challenges Faced
- Integrating JWT authentication and handling token expiry.
- Managing real-time seat availability (avoiding double booking).
- Designing normalized database with proper relationships.
- Handling payment failure and refunds gracefully.
- UI/UX consistency with React + Bootstrap.

## 9. Learnings
- Full-stack development using Spring Boot and React.
- Secure authentication with JWT.
- Database normalization and entity relationships.
- API documentation using Swagger.
- Git version control and project collaboration.

## 10. Future Enhancements
- Add wallet/promo code support.
- Implement microservices for scalability.
- Integrate SMS/email notifications.
- Support multi-operator booking.
