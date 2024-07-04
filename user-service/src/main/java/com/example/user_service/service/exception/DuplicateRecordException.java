package com.example.user_service.service.exception;

public class DuplicateRecordException extends ServiceException{

    public DuplicateRecordException(String message) {
        super(message);
    }
}
