package com.books.library.management.system.service;

import com.books.library.management.system.entity.Book;
import com.books.library.management.system.entity.BorrowingRecord;
import com.books.library.management.system.entity.Patron;
import com.books.library.management.system.exception.handling.BaseException;
import com.books.library.management.system.exception.handling.enums.ErrorCode;
import com.books.library.management.system.repo.BookRepository;
import com.books.library.management.system.repo.BorrowingRecordRepository;
import com.books.library.management.system.repo.PatronRepository;
import com.books.library.management.system.service.impl.BorrowingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class BorrowingServiceImplTest {

    @Mock
    private PatronRepository patronRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

    @InjectMocks
    private BorrowingServiceImpl borrowingService;

    @Test
    public void testBorrowBook_Success() {
        // Arrange
        Long bookId = 1L;
        Long patronId = 1L;
        Book book = new Book();
        book.setCopiesAvailable(5); // Set available copies
        Patron patron = new Patron();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(patronRepository.findById(patronId)).thenReturn(Optional.of(patron));
        when(borrowingRecordRepository.save(any(BorrowingRecord.class))).thenReturn(new BorrowingRecord());

        // Act
        borrowingService.borrowBook(bookId, patronId);

        // Assert
        verify(borrowingRecordRepository).save(any(BorrowingRecord.class));
        verify(bookRepository).save(book);
        assertEquals(4, book.getCopiesAvailable()); // Assert that copies available decreased by 1
    }

    @Test
    public void testBorrowBook_NoCopiesAvailable() {
        // Arrange
        Long bookId = 1L;
        Long patronId = 1L;
        Book book = new Book();
        book.setCopiesAvailable(0); // No available copies
        Patron patron = new Patron();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(patronRepository.findById(patronId)).thenReturn(Optional.of(patron));

        // Act & Assert
        BaseException exception = assertThrows(BaseException.class, () -> borrowingService.borrowBook(bookId, patronId));
        assertEquals(ErrorCode.NO_COPIES_AVAILABLE, exception.getErrorCode());
    }

    @Test
    public void testReturnBook_Success() {
        // Arrange
        Long bookId = 1L;
        Long patronId = 1L;
        Book book = new Book();
        book.setCopiesAvailable(4); // Set copies available
        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(new Patron());
        record.setBorrowDate(LocalDateTime.now());

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId)).thenReturn(Optional.of(record));
        when(borrowingRecordRepository.save(any(BorrowingRecord.class))).thenReturn(record);

        // Act
        borrowingService.returnBook(bookId, patronId);

        // Assert
        verify(borrowingRecordRepository).save(any(BorrowingRecord.class));
        verify(bookRepository).save(book);
        assertEquals(5, book.getCopiesAvailable()); // Assert that copies available increased by 1
    }

    @Test
    public void testReturnBook_RecordNotFound() {
        // Arrange
        Long bookId = 1L;
        Long patronId = 1L;
        Book book = new Book();
        book.setCopiesAvailable(4);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId)).thenReturn(Optional.empty());

        // Act & Assert
        BaseException exception = assertThrows(BaseException.class, () -> borrowingService.returnBook(bookId, patronId));
        assertEquals(ErrorCode.NOT_FOUND, exception.getErrorCode());
    }

    @Test
    public void testFindBookById_NotFound() {
        // Arrange
        Long bookId = 1L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // Act & Assert
        BaseException exception = assertThrows(BaseException.class, () -> borrowingService.borrowBook(bookId, 1L));
        assertEquals(ErrorCode.ID_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    public void testFindPatronById_NotFound() {
        // Arrange
        Long bookId = 1L;
        Long patronId = 1L;

        when(patronRepository.findById(patronId)).thenReturn(Optional.empty());

        // Act & Assert
        BaseException exception = assertThrows(BaseException.class, () -> borrowingService.borrowBook(bookId, patronId));
        assertEquals(ErrorCode.ID_NOT_FOUND, exception.getErrorCode());

    }
}
