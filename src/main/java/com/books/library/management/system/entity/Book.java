package com.books.library.management.system.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Title", nullable = false)
    @NotNull(message = "title.NotNull")
    @NotBlank(message = "title.NotBlank")
    @NotEmpty(message = "title.NotEmpty")
    private String title;

    @Column(name = "Author", nullable = false)
    @NotNull(message = "author.NotNull")
    @NotBlank(message = "author.NotBlank")
    @NotEmpty(message = "author.NotEmpty")
    private String author;

    @Column(name = "ISBN", unique = true, nullable = false)
    @NotNull(message = "isbn.NotNull")
    @NotBlank(message = "isbn.NotBlank")
    @NotEmpty(message = "isbn.NotEmpty")
    private String isbn;

    @Column(name = "Publisher")
    private String publisher;

    @Column(name = "YearPublished")
    private Integer yearPublished;

    @Column(name = "Genre")
    private String genre;

    @Column(name = "CopiesAvailable", nullable = false)
    @NotNull(message = "copiesAvailable.NotNull")
    @Min(value = 0, message = "copiesAvailable must be at least 1")
    @Max(value = 2000, message = "copiesAvailable must be at most 2000")
    private Integer copiesAvailable;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private Set<BorrowingRecord> borrowingRecords = new HashSet<>();




}
