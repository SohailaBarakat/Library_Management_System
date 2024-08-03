package com.books.library.management.system.exception.handling.enums;

import lombok.Getter;


@Getter
public enum ErrorCode  {

    BOOK_ALREADY_EXIST("book.exist","Book ISBN Already Exist"),
    ID_NOT_FOUND("not.exist","ID Not Found"),
    EMAIL_ALREADY_EXIST("email.exist","Email Already Exist"),
    NO_COPIES_AVAILABLE("no.copies.available","No Copies Available"),
    NOT_FOUND("not.found", "Not Found")
    ;

    private final String messageCode;
    private final String description;

    ErrorCode(String messageCode, String description) {
        this.messageCode = messageCode;
        this.description = description;
    }
}
