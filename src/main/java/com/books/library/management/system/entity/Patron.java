package com.books.library.management.system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patron")
    private Set<BorrowingRecord> borrowingRecords = new HashSet<>();


}
