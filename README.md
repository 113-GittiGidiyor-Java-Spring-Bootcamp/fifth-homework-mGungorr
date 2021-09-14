![hm05](https://user-images.githubusercontent.com/45206582/132606840-bcc89ab7-37f4-4bbd-a950-227b838b0b3c.PNG)

### What Is This ?

This project is a school management system has MapstructApi, Logger , DTO layer, exceptions and more...

### What Can It Does ?

- CRUD operations on Student,Courses and Instructors
- Auto Mapstruct between DTO's and Model Classes (Entities)
- Logging in every exception and you can get them from database
- Change salary, page and sort them, also retrieve from database with informations (ex. Date)

### Used Technologies

- Java 8
- Spring Boot
- Spring Web
- Lombok
- Maven
- JPA / Hibernate
- H2 Database
- MapStruct
- Swagger UI
- jUnit

### Project Structure

- Config
- Controllers
- DTO
- Exceptions
- Mappers
- Model (Entity)
    - Enumaration
- Repository (DAO)
- Service
- Util

### End Points

Main application URL: `http://localhost:8080`

Swagger URL: `http://localhost:8080/swagger-ui.html#`

h2 Database URL: `http://localhost:8080/h2-console/`   
(username: sa, password: // no password)

#### Courses
* Save a New Course : `POST /api/courses`
* Show All Courses: `GET /api/courses`
* Find by ID: `GET /api/courses/{id}`
* Update Course : `PUT /api/courses/{id}`
* Delete Course : `DELETE /api/courses/{id}`

#### Students
* Save a New Student : `POST /api/students`
* Show All Students: `GET /api/students`
* Find by ID: `GET /api/students/{id}`
* Update Student : `PUT /api/students/{id}`
* Delete Student : `DELETE /api/students/{id}`

#### Instructors
* Save a New Instructor : `POST /api/instructors`
* Show All Instructors: `GET /api/instructors`
* Find by ID: `GET /api/instructors/{id}`
* Update Instructor : `PUT /api/instructors/{id}`
* Delete Instructor : `DELETE /api/instructors/{id}`
* Change Salary : `PUT /api/instructors/salary/{id}`
* Salaries with date : `GET /salariesWithDate}`

#### Exception Logs
* Show Exception Logs : `GET /api/exception-logs`


### Exceptions
1. Course code is must be unique
2. Instructor phone number must be unique
3. Student age must be greater than 18 and less than 40
4. Number of students must be less than 20

```
- public CourseIsAlreadyExistException(String message) {}
- public InstructorIsAlreadyExistException(String message) {}
- public StudentAgeNotValidException(String message) {}
- public StudentNumberForOneCourseExceededException(String message) {}
```

### Installation

1. Clone the repo

` git clone https://github.com/113-GittiGidiyor-Java-Spring-Bootcamp/fifth-homework-mGungorr.git`

2. You can open from right console to maven pop-up or you can use the following command

` mvn install `

### Usage

1. Open it and run Main class.
2. When project is running, You can use api adresses on Controllers from web browser, you can use Postman or you can also use Swagger (enabled by default), depends on you :)
3. Feel free to ask me anything :)


## Author

**Mustafa Güngör**

* [github/mGungorr](https://github.com/mGungorr)