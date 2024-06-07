## System Documentation: jwt-student-management

### Overview
This is the Spring Boot application that integrates JWT authentication with a student management system. It provides secure endpoints for user registration, authentication, and student data management, including creation, retrieval, updating, and deletion of student records.

### Architecture and Flow
#### Jwt Auth Process Overview
<img width="606" alt="jwtservicee" src="https://github.com/Jonathanpangkey/jwtauth_springboot/assets/102292312/3c646c86-c4f3-4628-8654-8ea3d201788c">.
#### Student Operations Overview
<img width="638" alt="Screenshot 2024-02-22 at 15 45 06" src="https://github.com/Jonathanpangkey/jwt-student-management/assets/102292312/80dd9934-1edc-4421-8183-1ab42315f039"> <br > <br >
The application utilizes JWT authentication to secure endpoints. The JWT authentication filter validates tokens and sets up the security context for authenticated requests. The student management system follows a three-layered architecture: the API layer handles requests and responses, the service layer contains business logic, and the data access layer interacts with the PostgreSQL database.

### Data Models
- **User:**
  - Firstname: String
  - Lastname: String
  - Email: String
  - Password: String (encoded)
  - Role: Enum (USER, ADMIN)

- **Student:**
  - Name: String
  - Email: String
  - Date of Birth (DOB): LocalDate
  - Age (Transient, calculated based on DOB): Integer

### Endpoints Overview

#### User Endpoints (Open endpoints)
1. **User Registration Endpoint**
   - URL: `/api/v1/auth/register`
   - Method: `POST`
   - Description: Registers a new user.
   - Request Body:
     ```json
     {
       "firstname": "string",
       "lastname": "string",
       "email": "string",
       "password": "string"
     }
     ```
   - Response:
     ```json
     {
       "token": "string"
     }
     ```

2. **User Authentication Endpoint**
   - URL: `/api/v1/auth/authenticate`
   - Method: `POST`
   - Description: Authenticates a user.
   - Request Body:
     ```json
     {
       "email": "string",
       "password": "string"
     }
     ```
   - Response:
     ```json
     {
       "token": "string"
     }
     ```

#### Student Endpoints (Protected endpoints)
Protected endpoints require a valid JWT token to access. These endpoints are secured using Spring Security filters that check the presence and validity of the JWT token before granting access.
1. **Retrieve All Students**
   - URL: `/api/v1/student`
   - Method: `GET`
   - Description: Retrieves all students.

2. **Retrieve a Student by ID**
   - URL: `/api/v1/student/{studentId}`
   - Method: `GET`
   - Description: Retrieves a student by ID.
   - Path Parameters:
     - `studentId`: ID of the student to retrieve.

3. **Create a New Student**
   - URL: `/api/v1/student`
   - Method: `POST`
   - Description: Creates a new student.
   - Request Body:
     ```json
     {
       "name": "string",
       "email": "string",
       "dob": "string"
     }
     ```

4. **Update a Student by ID**
   - URL: `/api/v1/student/{studentId}`
   - Method: `PUT`
   - Description: Updates a student by ID.
   - Path Parameters:
     - `studentId`: ID of the student to update.
   - Request Body (optional fields):
     ```json
     {
       "name": "string",
       "email": "string",
       "dob": "string"
     }
     ```

5. **Delete a Student by ID**
   - URL: `/api/v1/student/{studentId}`
   - Method: `DELETE`
   - Description: Deletes a student by ID.
   - Path Parameters:
     - `studentId`: ID of the student to delete.

### Clone and Run the Application
If you want to use or modify this app:

1. Clone the project repository.
2. Ensure that PostgreSQL is installed and running.
3. Update the database configuration in the `application.properties` file if necessary.
4. Run the application with your favorite IDE (preferred IntelliJ IDEA).
5. Test the application with tools like Postman or by sending HTTP requests directly from your client application.
