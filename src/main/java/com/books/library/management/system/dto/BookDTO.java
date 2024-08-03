package com.books.library.management.system.dto;


import jakarta.validation.constraints.*;
import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Long id;

    @NotNull(message = "title.NotNull")
    @NotBlank(message = "title.NotBlank")
    @NotEmpty(message = "title.NotEmpty")
    private String title;

    @NotNull(message = "author.NotNull")
    @NotBlank(message = "author.NotBlank")
    @NotEmpty(message = "author.NotEmpty")
    private String author;

    @NotNull(message = "isbn.NotNull")
    @NotBlank(message = "isbn.NotBlank")
    @NotEmpty(message = "isbn.NotEmpty")
    private String isbn;

    private String publisher;

    private Integer yearPublished;

    private String genre;

    @NotNull(message = "copiesAvailable.NotNull")
    @Min(value = 0, message = "copiesAvailable must be at least 1")
    @Max(value = 2000, message = "copiesAvailable must be at most 2000")
    private Integer copiesAvailable;
}
