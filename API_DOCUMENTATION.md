
# Detailed API Documentation 

## Authentication

### Sign Up
- **Endpoint:** `POST /api/auth/signup`
- **Description:** Registers a new user in the system with a **unique** email.
- *Request Body:**
  ```json
  {
    "email" : "string",
    "password" : "string",
    "firstName" :"string",
    "secondName":"string"
  }

### Sign In
- **Endpoint:** `POST /api/auth/signin`
- **Description:** sign in to generate a token
- *Request Body:**
  ```json
  {
    "email" : "string",
    "password" : "string",
  }

> **Note:** All API endpoints require authentication. To access the APIs, you must first sign in using the `POST /api/auth/signin` endpoint to obtain an authentication token, and include the token in the `Authorization` header of your requests. 
***


## Books API

### Get All Books
- **Endpoint:** `GET /api/books`

### Get Book By Id 
- **Endpoint:** `GET /api/books/{id}`
- **PathParameter:**
  - Long id -> (book id)

### Add Book
- **Endpoint:** `POST /api/books`
- *Request:**
  ```json
  {
    "title": "new title",
    "author" :"new author",
    "isbn":"12345",
    "publisher": "new publisher",
    "yearPublished":"2020",
    "genre":"new gerne",
    "copiesAvailable": 10
  }


### Update Book
- **Endpoint:** `PUT /api/books/{id}`
- **PathParameter:**
  - Long id -> (book id)
- *Request:**
  ```json
  {
    "title": "new title",
    "author" :"new author",
    "isbn":"12345",
    "publisher": "new publisher",
    "yearPublished":"2020",
    "genre":"new gerne",
    "copiesAvailable": 10
  }


### Delete Book
- **Endpoint:** `DELETE /api/books/{id}`
- **PathParameter:**
  - Long id -> (book id)

***

## Patrons API

### Get All Patrons
- **Endpoint:** `GET /api/patrons`

### Get Patron By Id 
- **Endpoint:** `GET /api/patrons/{id}`
- **PathParameter:**
  - Long id -> (patron id)

### Add Patron
- **Endpoint:** `POST /api/patrons`
- *Request:**
  ```json
  {
    "firstName" : "PatronFirstName",
    "lastName":"PatronSecondName",
    "email" : "name@gmail.com",
    "phoneNumber" : "0123456789",
    "address" : "Cairo, Egypt"
  }


### Update Patron
- **Endpoint:** `PUT /api/patrons/{id}`
- **PathParameter:**
  - Long id -> (patron id)
- *Request:**
  ```json
  {
    "firstName" : "PatronFirstName",
    "lastName":"PatronSecondName",
    "email" : "patron@gmail.com",
    "phoneNumber" : "0123456789",
    "address" : "Cairo, Madinaty"
}


### Delete Patron
- **Endpoint:** `DELETE /api/patrons/{id}`
- **PathParameter:**
  - Long id -> (patron id)

***


## Borrowing API

### Borrow Book
- **Endpoint:** `POST /api/borrow/{bookId}/patron/{patronId}`
- **PathParameter:**
  - Long bookId
  - Long patronId

### Return Book
- **Endpoint:** `PUT /api/return/{bookId}/patron/{patronId}`
- **PathParameter:**
  - Long bookId
  - Long patronId



