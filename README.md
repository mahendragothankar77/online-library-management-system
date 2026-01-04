# ?? Online Library Management System (Spring Boot)

A **real-world Online Library Management System** built using **Spring Boot**, **Spring Security**, **JPA (Hibernate)**, and **MySQL**.  
This project supports **role-based access (ADMIN & USER)**, **book issuing & returning with fine calculation**, **membership validation**, and **reporting APIs**.

---

## ?? Features

### ?? Authentication & Authorization
- User Registration & Login
- Role-based access using Spring Security
  - **ADMIN**
  - **USER**
- HTTP Basic Authentication
- Password encryption using BCrypt

---

### ?? User Management
- Register new users with membership duration
- Automatic membership start & expiry dates
- Membership validity check before issuing books
- View user details with:
  - Books currently issued
  - Books previously read

---

### ?? Book Management
- Add books (ADMIN only)
- View books by:
  - Category
  - Author
  - Name
  - Status (AVAILABLE / NOT_AVAILABLE)
- Maintain book quantity
- Automatic status update when books are issued/returned

---

### ?? Book Issue & Return
- Issue book for specific number of days
- Prevent issuing when:
  - Membership is expired
  - Book is out of stock
- Return book with:
  - Late fine calculation
  - Fine stored separately
- Track book issue history

---

### ?? Reporting
- Category-wise book usage report
- Percentage of books read per category
- Admin-only access for reports

---

## ?? Tech Stack

| Layer        | Technology |
|--------------|------------|
| Backend      | Java 17 |
| Framework    | Spring Boot |
| Security     | Spring Security |
| ORM          | Spring Data JPA (Hibernate) |
| Database     | MySQL |
| Build Tool   | Maven |
| API Testing  | Postman |
| Version Ctrl | Git & GitHub |

---


---

## ?? Roles & Access Rules

| API | Access |
|----|-------|
| `/auth/**` | Public |
| `/books/addBook` | ADMIN |
| `/books/**` | USER / ADMIN |
| `/users/**` | USER |
| `/reports/**` | ADMIN |

---

## ?? Configuration

### application.properties

```properties
server.port=8888

spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect


????? Author

Mahendra Gothankar

Java | Spring Boot | Backend Developer


GitHub:
https://github.com/mahendragothankar77/online-library-management-system

