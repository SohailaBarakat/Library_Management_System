package com.books.library.management.system.service;

public interface IBorrowingService {
     void borrowBook(Long bookId, Long patronId);
     void returnBook(Long bookId, Long patronId);
}
