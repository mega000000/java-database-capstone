# Architecture Design Document - Smart Clinic Management System

## Section 1: Architecture Summary

The Smart Clinic Management System is a three-tier web application built using Spring Boot that combines server-side view rendering with stateless RESTful APIs. For administrative and clinical workflows, Spring MVC with Thymeleaf templates serves server-rendered HTML dashboards for Admins and Doctors. For external clients, mobile applications, and interactive patient portals (such as Appointments, Patient Dashboard, and Patient Records), REST Controllers expose lightweight HTTP/JSON endpoints.

The application utilizes a dual-database architecture managed through a unified Service Layer to ensure a clear separation of business logic and data persistence. Relational and structured data—including Patients, Doctors, Appointments, and Admin accounts—are persisted in MySQL using Spring Data JPA. Unstructured, document-based data—specifically Prescription records—are stored in MongoDB using Spring Data MongoDB. Requests flow sequentially through Controllers, the Service Layer, Repositories, and standard Data Models before being bound and returned as either rendered views or serialized JSON payloads.

---

## Section 2: Numbered Flow of Data and Control

1. **User Interface Layer:** The user initiates an action via a client interface. Requests originate either from server-rendered Thymeleaf web pages (such as the `AdminDashboard` or `DoctorDashboard`) or from REST API clients sending HTTP requests (such as `Appointments`, `PatientDashboard`, or `PatientRecord`).

2. **Controller Layer:** The incoming request is routed to a specific Spring Boot controller based on the URL path and HTTP method. Server-side rendering requests are directed to **Thymeleaf Controllers**, while API-driven client requests are routed to **REST Controllers** for JSON processing and payload validation.

3. **Service Layer:** The controller delegates the core business logic to the **Service Layer**. This central layer validates input, executes business rules, and manages operations that span multiple entities (such as checking doctor schedules before booking an appointment).

4. **Repository Layer:** The Service Layer calls the appropriate persistence abstraction. Queries for relational, structured entities are dispatched to **MySQL Repositories** (via Spring Data JPA), while document-based queries are sent to the **MongoDB Repository** (via Spring Data MongoDB).

5. **Database Access:** The repositories interact directly with their respective storage engines. **MySQL** executes SQL queries against normalized relational tables (`Patient`, `Doctor`, `Appointment`, `Admin`), while **MongoDB** executes query operations against unstructured BSON/JSON document collections (`Prescription`).

6. **Model Binding:** Data returned from the databases is mapped directly into Java model objects. Relational records are converted into JPA entities annotated with `@Entity`, while MongoDB document collections are hydrated into Java objects annotated with `@Document`.

7. **Application Models in Use:** The populated Java models are returned back through the Service Layer to the Controllers for client presentation:
   - **Thymeleaf Controllers** bind models to dynamic HTML templates and render full web pages to the browser.
   - **REST Controllers** serialize models into JSON objects and send them back via HTTP responses to the requesting API client.
