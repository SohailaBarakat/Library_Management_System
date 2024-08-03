package com.books.library.management.system.controller;


import com.books.library.management.system.dto.BaseResponse;
import com.books.library.management.system.service.IBorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BorrowingController {

    private final IBorrowingService borrowingService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BaseResponse<String>> borrowBook(
            @PathVariable Long bookId,
            @PathVariable Long patronId) {
        borrowingService.borrowBook(bookId, patronId);
        return new ResponseEntity<>(new BaseResponse<>("Book borrowed successfully"), HttpStatus.OK);

    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BaseResponse<String>> returnBook(
            @PathVariable Long bookId,
            @PathVariable Long patronId) {
        borrowingService.returnBook(bookId, patronId);
        return new ResponseEntity<>(new BaseResponse<>("Book returned successfully"),HttpStatus.OK);
    }


}
