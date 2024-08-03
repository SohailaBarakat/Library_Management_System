package com.books.library.management.system.service;

import com.books.library.management.system.dto.BookDTO;
import com.books.library.management.system.entity.Book;
import com.books.library.management.system.exception.handling.BaseException;
import com.books.library.management.system.exception.handling.enums.ErrorCode;
import com.books.library.management.system.mapper.BookMapper;
import com.books.library.management.system.repo.BookRepository;
import com.books.library.management.system.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll_Success() {
        // Arrange
        Book book = new Book(); // Initialize with values
        BookDTO bookDTO = new BookDTO(); // Initialize with values
        List<Book> books = new ArrayList<>();
        books.add(book);
        List<BookDTO> bookDTOs = new ArrayList<>();
        bookDTOs.add(bookDTO);

        when(bookRepository.findAll()).thenReturn(books);
        when(bookMapper.toDto(books)).thenReturn(bookDTOs);

        // Act
        List<BookDTO> result = bookService.getAll();

        // Assert
        assertEquals(bookDTOs, result);
    }

    @Test
    public void testGetById_Success() {
        // Arrange
        Long id = 1L;
        Book book = new Book(); // Initialize with values
        BookDTO bookDTO = new BookDTO(); // Initialize with values

        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        when(bookMapper.toDto(book)).thenReturn(bookDTO);

        // Act
        BookDTO result = bookService.getById(id);

        // Assert
        assertEquals(bookDTO, result);
    }

    @Test
    public void testGetById_NotFound() {
        // Arrange
        Long id = 1L;

        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        BaseException exception = assertThrows(BaseException.class, () -> bookService.getById(id));
        assertEquals(ErrorCode.ID_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    public void testAdd_Success() {
        // Arrange
        BookDTO bookDTO = new BookDTO(); // Initialize with values
        Book book = new Book(); // Initialize with values

        when(bookMapper.toEntity(bookDTO)).thenReturn(book);
        when(bookRepository.existsByIsbn(bookDTO.getIsbn())).thenReturn(false);

        // Act
        bookService.add(bookDTO);

        // Assert
        verify(bookRepository).save(book);
    }

    @Test
    public void testAdd_IsbnNotUnique() {
        // Arrange
        BookDTO bookDTO = new BookDTO(); // Initialize with values

        when(bookRepository.existsByIsbn(bookDTO.getIsbn())).thenReturn(true);

        // Act & Assert
        BaseException exception = assertThrows(BaseException.class, () -> bookService.add(bookDTO));
        assertEquals(ErrorCode.BOOK_ALREADY_EXIST, exception.getErrorCode());
    }

    @Test
    public void testUpdate_Success() {
        // Arrange
        Long id = 1L;
        BookDTO bookDTO = new BookDTO(); // Initialize with values
        Book existingBook = new Book(); // Initialize with values

        when(bookRepository.findById(id)).thenReturn(Optional.of(existingBook));
        when(bookRepository.existsByIsbnAndIdNot(bookDTO.getIsbn(), id)).thenReturn(false);

        // Act
        bookService.update(id, bookDTO);

        // Assert
        verify(bookRepository).save(existingBook);
    }

    @Test
    public void testDelete_Success() {
        // Arrange
        Long id = 1L;

        when(bookRepository.existsById(id)).thenReturn(true);

        // Act
        bookService.delete(id);

        // Assert
        verify(bookRepository).deleteById(id);
    }

    @Test
    public void testDelete_NotFound() {
        // Arrange
        Long id = 1L;

        when(bookRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        BaseException exception = assertThrows(BaseException.class, () -> bookService.delete(id));
        assertEquals(ErrorCode.ID_NOT_FOUND, exception.getErrorCode());
    }
}
