package com.books.library.management.system.service.impl;

import com.books.library.management.system.dto.PatronDTO;
import com.books.library.management.system.entity.Patron;
import com.books.library.management.system.exception.handling.BaseException;
import com.books.library.management.system.exception.handling.enums.ErrorCode;
import com.books.library.management.system.mapper.PatronMapper;
import com.books.library.management.system.repo.PatronRepository;
import com.books.library.management.system.service.IPatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatronServiceImpl implements IPatronService {

    private final PatronRepository patronRepository;
    private final PatronMapper patronMapper;


    @Override
    public List<PatronDTO> getAll() {
        return patronMapper.toDto(patronRepository.findAll());
    }


    @Override
    public PatronDTO getById(Long id) {
        return patronRepository.findById(id)
                .map(patronMapper::toDto)
                .orElseThrow(() -> createNotFoundException(id));
    }


    @Override
    public Void add(PatronDTO patronDTO) {
        validateEmailUniqueness(patronDTO.getEmail());
        Patron patron = patronMapper.toEntity(patronDTO);
        patronRepository.save(patron);
        return null;
    }


    @Override
    public PatronDTO update(Long id, PatronDTO patronDTO) {
        Patron existingPatron = patronRepository.findById(id)
                .orElseThrow(() -> createNotFoundException(id));

        if(patronRepository.existsByEmailAndIdNot(patronDTO.getEmail(), id)){
            validateEmailUniqueness(patronDTO.getEmail());
        }

        updatePatronFields(existingPatron, patronDTO);
        patronRepository.save(existingPatron);
        return patronMapper.toDto(existingPatron);
    }


    @Override
    public Void delete(Long id) {
        if (!patronRepository.existsById(id)){
            throw createNotFoundException(id);
        }
        patronRepository.deleteById(id);
        return null;
    }


    private BaseException createNotFoundException(Long id) {
        return new BaseException(ErrorCode.ID_NOT_FOUND, "A patron with ID " + id + " not found");
    }


    private void validateEmailUniqueness(String email) {
        if (patronRepository.existsByEmail(email)) {
            throw BaseException.builder()
                    .errorCode(ErrorCode.EMAIL_ALREADY_EXIST)
                    .details("Email: " + email + " already added before.")
                    .build();
        }
    }

    private void updatePatronFields(Patron existingPatron, PatronDTO patronDTO) {
        existingPatron.setFirstName(patronDTO.getFirstName());
        existingPatron.setLastName(patronDTO.getLastName());
        existingPatron.setEmail(patronDTO.getEmail());
        existingPatron.setPhoneNumber(patronDTO.getPhoneNumber());
        existingPatron.setAddress(patronDTO.getAddress());
    }

}
