package com.example.user_service.service.exception;

public class NotFoundException extends ServiceException{

    public NotFoundException(String message) {
        super(message);
    }
}
