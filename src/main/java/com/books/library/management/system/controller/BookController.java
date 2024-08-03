package com.books.library.management.system.controller;


import com.books.library.management.system.dto.BookDTO;
import com.books.library.management.system.dto.BaseResponse;
import com.books.library.management.system.service.IBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final IBookService bookService;


    @GetMapping
    public ResponseEntity<BaseResponse<List<BookDTO>>> getAll(){
        return new ResponseEntity<>(new BaseResponse<>(bookService.getAll()),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<BookDTO>> getById(@PathVariable Long id){
        return new ResponseEntity<>(new BaseResponse<>(bookService.getById(id)),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> add(@Valid @RequestBody BookDTO bookDTO){
        return new ResponseEntity<>(new BaseResponse<>(bookService.add(bookDTO)),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<BookDTO>> update(@PathVariable Long id,@Valid @RequestBody BookDTO bookDTO){
        return new ResponseEntity<>(new BaseResponse<>(bookService.update(id,bookDTO)),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Long id){
        return new ResponseEntity<>(new BaseResponse<>(bookService.delete(id)),HttpStatus.OK);

    }



}
