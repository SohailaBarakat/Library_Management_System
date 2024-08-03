package com.books.library.management.system.service;

import com.books.library.management.system.dto.PatronDTO;
import com.books.library.management.system.entity.Patron;
import com.books.library.management.system.exception.handling.BaseException;
import com.books.library.management.system.exception.handling.enums.ErrorCode;
import com.books.library.management.system.mapper.PatronMapper;
import com.books.library.management.system.repo.PatronRepository;
import com.books.library.management.system.service.impl.PatronServiceImpl;
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

public class PatronServiceImplTest {

    @Mock
    private PatronRepository patronRepository;

    @Mock
    private PatronMapper patronMapper;

    @InjectMocks
    private PatronServiceImpl patronService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAll_Success() {
        // Arrange
        Patron patron = new Patron(); // Initialize with values
        PatronDTO patronDTO = new PatronDTO(); // Initialize with values
        List<Patron> patrons = new ArrayList<>();
        patrons.add(patron);
        List<PatronDTO> patronDTOs = new ArrayList<>();
        patronDTOs.add(patronDTO);

        when(patronRepository.findAll()).thenReturn(patrons);
        when(patronMapper.toDto(patrons)).thenReturn(patronDTOs);

        // Act
        List<PatronDTO> result = patronService.getAll();

        // Assert
        assertEquals(patronDTOs, result);
    }

    @Test
    public void testGetById_Success() {
        // Arrange
        Long id = 1L;
        Patron patron = new Patron(); // Initialize with values
        PatronDTO patronDTO = new PatronDTO(); // Initialize with values

        when(patronRepository.findById(id)).thenReturn(Optional.of(patron));
        when(patronMapper.toDto(patron)).thenReturn(patronDTO);

        // Act
        PatronDTO result = patronService.getById(id);

        // Assert
        assertEquals(patronDTO, result);
    }

    @Test
    public void testGetById_NotFound() {
        // Arrange
        Long id = 1L;

        when(patronRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        BaseException exception = assertThrows(BaseException.class, () -> patronService.getById(id));
        assertEquals(ErrorCode.ID_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    public void testAdd_Success() {
        // Arrange
        PatronDTO patronDTO = new PatronDTO(); // Initialize with values
        Patron patron = new Patron(); // Initialize with values

        when(patronMapper.toEntity(patronDTO)).thenReturn(patron);
        when(patronRepository.existsByEmail(patronDTO.getEmail())).thenReturn(false);

        // Act
        patronService.add(patronDTO);

        // Assert
        verify(patronRepository).save(patron);
    }

    @Test
    public void testAdd_EmailNotUnique() {
        // Arrange
        PatronDTO patronDTO = new PatronDTO(); // Initialize with values

        when(patronRepository.existsByEmail(patronDTO.getEmail())).thenReturn(true);

        // Act & Assert
        BaseException exception = assertThrows(BaseException.class, () -> patronService.add(patronDTO));
        assertEquals(ErrorCode.EMAIL_ALREADY_EXIST, exception.getErrorCode());
    }

    @Test
    public void testUpdate_Success() {
        // Arrange
        Long id = 1L;
        PatronDTO patronDTO = new PatronDTO(); // Initialize with values
        Patron existingPatron = new Patron(); // Initialize with values

        when(patronRepository.findById(id)).thenReturn(Optional.of(existingPatron));
        when(patronRepository.existsByEmailAndIdNot(patronDTO.getEmail(), id)).thenReturn(false);

        // Act
        patronService.update(id, patronDTO);

        // Assert
        verify(patronRepository).save(existingPatron);
    }

    @Test
    public void testDelete_Success() {
        // Arrange
        Long id = 1L;

        when(patronRepository.existsById(id)).thenReturn(true);

        // Act
        patronService.delete(id);

        // Assert
        verify(patronRepository).deleteById(id);
    }

    @Test
    public void testDelete_NotFound() {
        // Arrange
        Long id = 1L;

        when(patronRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        BaseException exception = assertThrows(BaseException.class, () -> patronService.delete(id));
        assertEquals(ErrorCode.ID_NOT_FOUND, exception.getErrorCode());
    }
}
