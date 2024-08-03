
# Detailed API Documentation 

## Books API

### Get All Books
- **Endpoint:** `GET /api/books`

### Get Book By Id 
- **Endpoint:** `GET /api/books/{id}`
- **PathParameter:**
  - Long id

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
  - Long id
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
  - Long id





