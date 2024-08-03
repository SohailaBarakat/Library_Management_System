-- Create the Book table
CREATE TABLE Book (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(255) NOT NULL,
    Author VARCHAR(255) NOT NULL,
    ISBN VARCHAR(13) UNIQUE NOT NULL,
    Publisher VARCHAR(255),
    YearPublished YEAR,
    Genre VARCHAR(100),
    CopiesAvailable INT NOT NULL
);

-- Create the Patron table
CREATE TABLE Patron (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(100) NOT NULL,
    LastName VARCHAR(100) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(15),
    Address VARCHAR(255)
);

-- Create the BorrowingRecord table
CREATE TABLE BorrowingRecord (
    RecordID BIGINT AUTO_INCREMENT PRIMARY KEY,
    BookID BIGINT NOT NULL,
    PatronID BIGINT NOT NULL,
    BorrowDate TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    ReturnDate TIMESTAMP(6),
    FOREIGN KEY (BookID) REFERENCES Book(ID),
    FOREIGN KEY (PatronID) REFERENCES Patron(ID)
);
