# Library Management System
The **Library Management System** is a comprehensive solution designed to manage books, patrons, and borrowing records in a library setting. This Spring Boot application allows users to efficiently handle various library operations through a RESTful API.



## Technologies Used
- **Java** 
- **Spring Boot** 
- **Spring Data JPA** 
- **MySQL** 
- **Maven** 
- **Postman** 
- **Lombok** 


## Getting Started
- To run the application you will need to have Java 17 and MySQL installed on your machine.

### Clone the Repository
- Start by cloning the repository to your local machine:

```
git clone https://github.com/SohailaBarakat/library_management_system.git
```

### Database Setup
- Create db named library_management_system and set the username and password values in the application.properties file.

### Build and Run the Application
- To build and run the application, navigate to the project directory and execute the following command:

```
mvn spring-boot: run
```

- The application should now be running on http://localhost:8080.


## APIs 
- **Books API**
  - Retrieve, add, update, and delete books.
- **Patrons API**
  - Retrieve, add, update, and delete patrons.
- **Borrowing API**
  - Borrow and return books.

### Accessing the API Documentation
For detailed API documentation, including endpoint URLs, request/response examples, and more, please refer to the [API Documentation](https://github.com/SohailaBarakat/library_management_system/blob/master/API_DOCUMENTATION.md)
