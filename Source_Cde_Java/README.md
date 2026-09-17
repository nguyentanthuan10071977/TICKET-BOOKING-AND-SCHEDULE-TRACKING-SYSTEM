# Ticket Booking and Schedule Tracking System - Java RESTful API

Java 17 / Spring Boot / Spring Data JPA / H2.

Run:
mvn spring-boot:run

Base URL: http://localhost:8080

User Story mapping:
1. Admin Register/Login/Logout: POST /api/auth/register, /login, /logout
2. Admin Account Management: GET/POST/PUT/DELETE /api/users
3. User Register + confirmation: POST /api/auth/register, /confirm
4. User Login/Logout: POST /api/auth/login, /logout
5. Search trips: GET /api/trips/search?pickup=...&destination=...&departureDate=YYYY-MM-DD
6. View trip: GET /api/trips/{id}
7. View tickets: GET /api/tickets/{id} or /api/tickets/user/{userId}
8. Online reservation: POST /api/tickets/reserve
9. Payment method: PaymentMethod ONLINE or COUNTER
10. Online payment: POST /api/payments/online?ticketId=...
11. Counter payment: POST /api/payments/counter?ticketId=...
12. Pick-up/destination/date filtering: GET /api/trips/search
13. Cancel ticket: PUT /api/tickets/{id}/cancel
14. Chatbot: POST /api/chatbot/{userId}/ask
15. Edit profile: PUT /api/users/{id}
16. Change password: PUT /api/users/{id}/password

The document does not define JWT/session implementation, real email delivery, payment-gateway credentials, seat-layout constraints, or an actual chatbot provider. Those are left as integration points rather than invented requirements.
