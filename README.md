## System Documentation: jwt-student-management

### Overview
This is the Spring Boot application that integrates JWT authentication with a student management system. It provides secure endpoints for user registration, authentication, and student data management, including creation, retrieval, updating, and deletion of student records.

### Architecture and Flow
#### Jwt Auth Process Overview

<img width="606" alt="jwtservicee" src="https://github.com/Jonathanpangkey/jwt-student-management/assets/102292312/cf0a2905-aa35-4d67-99a0-b5ef53d4b8e8"> <br />

In this application, the JWT authentication filter is the initial point of execution. It validates the JWT token, starting by checking if the token is present. If missing, it sends a 403 response. Next, it fetches user details from the database based on the token's subject (user email). If the user doesn't exist, it returns a 403; otherwise, it proceeds to validate the token against the user. If the token is invalid (expired or not for that user), it sends a 403; otherwise, it updates the security context holder, marking the user as authenticated. 
Once the user is authenticated through the JWT authentication filter, they gain access to various services, including CRUD operations for student entities. This means that upon successful validation of the JWT token and confirmation of the user's existence and authorization, the application grants them privileges to perform actions such as creating, reading, updating, and deleting student records.

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
