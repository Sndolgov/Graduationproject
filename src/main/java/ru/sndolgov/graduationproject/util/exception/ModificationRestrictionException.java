package ru.sndolgov.graduationproject.util.exception;

import org.springframework.http.HttpStatus;

public class ModificationRestrictionException extends ApplicationException {
    public static final String EXCEPTION_MODIFICATION_RESTRICTION = "Модификация Admin/User запрещена";

    public ModificationRestrictionException() {
        super(EXCEPTION_MODIFICATION_RESTRICTION, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }
}