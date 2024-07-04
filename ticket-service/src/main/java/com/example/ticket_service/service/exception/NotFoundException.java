package com.example.ticket_service.service.exception;

public class NotFoundException extends ServiceException{

    public NotFoundException(String message) {
        super(message);
    }
}
