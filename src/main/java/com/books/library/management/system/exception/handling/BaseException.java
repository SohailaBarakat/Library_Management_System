package com.books.library.management.system.exception.handling;

import com.books.library.management.system.exception.handling.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;


@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@Data
public class BaseException extends RuntimeException {
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus = HttpStatus.NOT_ACCEPTABLE;
    private final String details;

}
