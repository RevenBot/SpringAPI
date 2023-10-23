package com.example.demo.resources;

import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    // Get All Notes
    @GetMapping
    public List<Book> getAllNotes() {
        return bookRepository.findAll();
    }

    // Create a new Note
    @PostMapping
    public Book createNote(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // Get a Single Note
    @GetMapping("{id}")
    public Book getNoteById(@PathVariable(value = "id") String bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

    // Update a Note
    @PutMapping("{id}")
    public Book updateNote(@PathVariable(value = "id") String bookId,
                            @RequestBody Book bookDetails) throws BookNotFoundException {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        book.setBookName(bookDetails.getBookName());
        book.setAuthorName(bookDetails.getAuthorName());
        book.setIsbn(bookDetails.getIsbn());

        Book updatedBook = bookRepository.save(book);

        return updatedBook;
    }

    // Delete a Note
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") String bookId) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        bookRepository.delete(book);

        return ResponseEntity.ok().build();
    }
}
