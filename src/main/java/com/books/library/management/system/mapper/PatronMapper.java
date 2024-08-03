package com.books.library.management.system.mapper;

import com.books.library.management.system.dto.BorrowingRecordDTO;
import com.books.library.management.system.dto.PatronDTO;
import com.books.library.management.system.entity.BorrowingRecord;
import com.books.library.management.system.entity.Patron;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class PatronMapper {
    public PatronMapper(){}

    public Patron toEntity(PatronDTO patronDTO) {
        if (patronDTO == null) {
            return null;
        }

        return Patron.builder()
                .firstName(patronDTO.getFirstName())
                .lastName(patronDTO.getLastName())
                .email(patronDTO.getEmail())
                .phoneNumber(patronDTO.getPhoneNumber())
                .address(patronDTO.getAddress())
                .build();
    }

    // Convert Patron entity to PatronDTO
    public PatronDTO toDto(Patron patron) {
        if (patron == null) {
            return null;
        }

        return PatronDTO.builder()
                .id(patron.getId())
                .firstName(patron.getFirstName())
                .lastName(patron.getLastName())
                .email(patron.getEmail())
                .phoneNumber(patron.getPhoneNumber())
                .address(patron.getAddress())
                .borrowingRecords(patron.getBorrowingRecords().stream()
                        .map(this::toBorrowingRecordDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    public List<PatronDTO> toDto(List<Patron> patrons) {
        if (patrons == null) {
            return null;
        }

        List<PatronDTO> list = new ArrayList<>();
        for (Patron patron : patrons) {
            list.add(toDto(patron));
        }
        return list;
    }


    private BorrowingRecordDTO toBorrowingRecordDto(BorrowingRecord borrowingRecord) {
        if (borrowingRecord == null) {
            return null;
        }

        return BorrowingRecordDTO.builder()
                .recordId(borrowingRecord.getRecordId())
                .bookId(borrowingRecord.getBook().getId())
                .borrowDate(borrowingRecord.getBorrowDate().toString())
                .returnDate(borrowingRecord.getReturnDate() != null ? borrowingRecord.getReturnDate().toString() : null)
                .build();
    }


}
