package com.books.library.management.system.service.impl;

import com.books.library.management.system.dto.BookDTO;
import com.books.library.management.system.entity.Book;
import com.books.library.management.system.exception.handling.BaseException;
import com.books.library.management.system.exception.handling.enums.ErrorCode;
import com.books.library.management.system.mapper.BookMapper;
import com.books.library.management.system.repo.BookRepository;
import com.books.library.management.system.service.IBookService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    @Override
    @Transactional(readOnly = true)
    @Cacheable("books")
    public List<BookDTO> getAll() {
        return bookMapper.toDto(bookRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "books", key = "#id")
    public BookDTO getById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> createNotFoundException(id));
    }

    @Override
    @Transactional
    @CacheEvict(value = "books", allEntries = true)
    public Void add(BookDTO bookDTO) {
        validateIsbnUniqueness(bookDTO.getIsbn());
        Book book = bookMapper.toEntity(bookDTO);
        bookRepository.save(book);
        return null;
    }

    @Override
    @Transactional
    @CacheEvict(value = "books", key = "#id")
    public BookDTO update(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> createNotFoundException(id));

        // Check if the new ISBN already exists and is not associated with the current book
        if (bookRepository.existsByIsbnAndIdNot(bookDTO.getIsbn(), id)) {
            validateIsbnUniqueness(bookDTO.getIsbn());
        }

        updateBookFields(existingBook, bookDTO);

        bookRepository.save(existingBook);
        return bookMapper.toDto(existingBook);
    }

    @Override
    @Transactional
    @CacheEvict(value = "books", key = "#id")
    public Void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw createNotFoundException(id);
        }
        bookRepository.deleteById(id);
        return null;
    }

    // Helper Methods

    private BaseException createNotFoundException(Long id) {
        return new BaseException(ErrorCode.ID_NOT_FOUND, "A book with ID " + id + " not found");
    }

    private void validateIsbnUniqueness(String isbn) {
        if (bookRepository.existsByIsbn(isbn)) {
            throw BaseException.builder()
                    .errorCode(ErrorCode.BOOK_ALREADY_EXIST)
                    .details("A book with ISBN " + isbn + " already exists.")
                    .build();
        }
    }

    private void updateBookFields(Book existingBook, BookDTO bookDTO) {
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setIsbn(bookDTO.getIsbn());
        existingBook.setGenre(bookDTO.getGenre());
        existingBook.setPublisher(bookDTO.getPublisher());
        existingBook.setYearPublished(bookDTO.getYearPublished());
    }
}
