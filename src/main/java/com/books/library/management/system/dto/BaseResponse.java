package com.books.library.management.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
public class BaseResponse<T> {
    private T response;
    private boolean status = true;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date currentDate = new Date();

    public BaseResponse(T response) {
        this.response = response;
    }
}

