package com.books.library.management.system.exception.handling;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ApiFieldError {

    private String field;
    private String code;
    private Object rejectedValue;

    public ApiFieldError() {
    }

    public ApiFieldError(String field, String code, Object rejectedValue) {
        super();
        this.field = field;
        this.code = code;
        this.rejectedValue = rejectedValue;
    }


}
