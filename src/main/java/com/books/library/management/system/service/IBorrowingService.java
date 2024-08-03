package com.books.library.management.system.service;

public interface IBorrowingService {
    public void borrowBook(Long bookId, Long patronId);
    public void returnBook(Long bookId, Long patronId);
}
