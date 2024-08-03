package com.books.library.management.system.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BorrowingRecord")
public class BorrowingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BookID", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatronID", nullable = false)
    private Patron patron;

    @Column(name = "BorrowDate", nullable = false)
    private String borrowDate;

    @Column(name = "ReturnDate")
    private String returnDate;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate.format(FORMATTER);
    }

    public LocalDateTime getBorrowDate() {
        return LocalDateTime.parse(this.borrowDate, FORMATTER);
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate != null ? returnDate.format(FORMATTER) : null;
    }

    public LocalDateTime getReturnDate() {
        return returnDate != null ? LocalDateTime.parse(this.returnDate, FORMATTER) : null;
    }

}
