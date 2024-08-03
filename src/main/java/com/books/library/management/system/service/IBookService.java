package com.books.library.management.system.service;

import com.books.library.management.system.dto.BookDTO;

import java.util.List;

public interface IBookService {
    Void add(BookDTO bookDTO);
    List<BookDTO> getAll();
    BookDTO getById(Long id);
    BookDTO update(Long id, BookDTO bookDTO);
    Void delete(Long id);
}
