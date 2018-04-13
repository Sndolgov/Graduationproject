package ru.sndolgov.graduationproject.util.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApplicationException {
    public static final String NOT_FOUND_EXCEPTION = "Не найдена запись с {0}";

    //  http://stackoverflow.com/a/22358422/548473
    public NotFoundException(String arg) {
        super(NOT_FOUND_EXCEPTION, HttpStatus.UNPROCESSABLE_ENTITY, arg);
    }
}