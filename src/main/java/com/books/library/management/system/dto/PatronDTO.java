package com.books.library.management.system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatronDTO {
    private Long id;

    @NotNull(message = "firstName.NotNull")
    @NotBlank(message = "firstName.NotBlank")
    @NotEmpty(message = "firstName.NotEmpty")
    private String firstName;

    @NotNull(message = "lastName.NotNull")
    @NotBlank(message = "lastName.NotBlank")
    @NotEmpty(message = "lastName.NotEmpty")
    private String lastName;

    @NotNull(message = "email.NotNull")
    @NotBlank(message = "email.NotBlank")
    @NotEmpty(message = "email.NotEmpty")
    private String email;

    @NotNull(message = "phoneNumber.NotNull")
    @NotBlank(message = "phoneNumber.NotBlank")
    @NotEmpty(message = "phoneNumber.NotEmpty")
    private String phoneNumber;

    private String address;

    private Set<BorrowingRecordDTO> borrowingRecords;

}
