package com.example.demo.exceptions;

public class BookNotFoundException extends Exception {
    private String book_id;
    public BookNotFoundException(String book_id) {
        super(String.format("Book is not found with id : '%s'", book_id));
    }
}
