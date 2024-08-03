package com.books.library.management.system.controller;


import com.books.library.management.system.dto.BaseResponse;
import com.books.library.management.system.dto.PatronDTO;
import com.books.library.management.system.service.IPatronService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor
public class PatronController {
    private final IPatronService patronService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<PatronDTO>>> getAll(){
        return new ResponseEntity<>(new BaseResponse<>(patronService.getAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<PatronDTO>> getById(@PathVariable Long id){
        return new ResponseEntity<>(new BaseResponse<>(patronService.getById(id)),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> add(@Valid @RequestBody PatronDTO patronDTO) {
        return new ResponseEntity<>(new BaseResponse<>(patronService.add(patronDTO)), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<PatronDTO>> update(@PathVariable Long id,@Valid  @RequestBody PatronDTO patronDTO){
        return new ResponseEntity<>(new BaseResponse<>(patronService.update(id,patronDTO)),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable Long id){
        return new ResponseEntity<>(new BaseResponse<>(patronService.delete(id)),HttpStatus.OK);

    }

}
