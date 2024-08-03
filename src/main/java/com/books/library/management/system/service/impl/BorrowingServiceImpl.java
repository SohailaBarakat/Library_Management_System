package com.books.library.management.system.service.impl;

import com.books.library.management.system.entity.Book;
import com.books.library.management.system.entity.BorrowingRecord;
import com.books.library.management.system.entity.Patron;
import com.books.library.management.system.exception.handling.BaseException;
import com.books.library.management.system.exception.handling.enums.ErrorCode;
import com.books.library.management.system.repo.BookRepository;
import com.books.library.management.system.repo.BorrowingRecordRepository;
import com.books.library.management.system.repo.PatronRepository;
import com.books.library.management.system.service.IBorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements IBorrowingService {

    private final PatronRepository patronRepository;
    private final BookRepository bookRepository;
    private final BorrowingRecordRepository borrowingRecordRepository;

    @Override
    public void borrowBook(Long bookId, Long patronId) {
        Book book = findBookById(bookId);
        Patron patron = findPatronById(patronId);

        if (book.getCopiesAvailable() <= 0) {
            throw new BaseException(ErrorCode.NO_COPIES_AVAILABLE, "No Available Copies for book: " + book.getTitle());
        }

        BorrowingRecord record = createBorrowingRecord(book, patron);
        borrowingRecordRepository.save(record);

        updateBookCopiesAvailable(book, -1);
    }

    @Override
    public void returnBook(Long bookId, Long patronId) {
        Book book = findBookById(bookId);
        BorrowingRecord record = findBorrowingRecord(bookId, patronId);

        record.setReturnDate(LocalDateTime.now());
        borrowingRecordRepository.save(record);

        updateBookCopiesAvailable(book, 1);
    }

    private Book findBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BaseException(ErrorCode.ID_NOT_FOUND, "A book with ID " + bookId + " not found"));
    }

    private Patron findPatronById(Long patronId) {
        return patronRepository.findById(patronId)
                .orElseThrow(() -> new BaseException(ErrorCode.ID_NOT_FOUND, "A patron with ID " + patronId + " not found"));
    }

    private BorrowingRecord findBorrowingRecord(Long bookId, Long patronId) {
        return borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND, "Borrowing record not found"));
    }

    private BorrowingRecord createBorrowingRecord(Book book, Patron patron) {
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBorrowDate(LocalDateTime.now());
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        return borrowingRecord;
    }

    private void updateBookCopiesAvailable(Book book, int delta) {
        int newCopiesAvailable = book.getCopiesAvailable() + delta;
        if (newCopiesAvailable < 0) {
            throw new BaseException(ErrorCode.NO_COPIES_AVAILABLE, "Cannot have less than 0 copies for book: " + book.getTitle());
        }
        book.setCopiesAvailable(newCopiesAvailable);
        bookRepository.save(book);
    }
}
