package com.books.library.management.system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BorrowingRecordDTO {
    private Long recordId;

    @NotNull(message = "bookId.NotNull")
    private Long bookId;

    @NotNull(message = "patronId.NotNull")
    private Long patronId;

    @NotNull
    private String borrowDate;

    private String returnDate;
}

