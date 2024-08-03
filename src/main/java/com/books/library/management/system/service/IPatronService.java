package com.books.library.management.system.service;

import com.books.library.management.system.dto.PatronDTO;

import java.util.List;

public interface IPatronService {
    Void add(PatronDTO patronDTO);
    List<PatronDTO> getAll();
    PatronDTO getById(Long id);
    PatronDTO update(Long id, PatronDTO patronDTO);
    Void delete(Long id);
}
