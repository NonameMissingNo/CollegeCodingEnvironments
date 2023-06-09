package com.example.demo.web.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.data.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BookService;

/**
 * A REST controller for managing books in the library.
 */
@RestController
@RequestMapping("/api/v1/book")
public class LibraryRestController {

    private final BookService bookService;

    @Autowired
    public LibraryRestController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Returns a book with the given id.
     *
     * @param id the id of the book to retrieve
     * @return the book object
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getSongById(@PathVariable Long id) {
        Optional<Book> song = bookService.retrieveBookById(id);
        return song.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Returns a list of all the books in the library.
     *
     * @return the list of books
     */
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.retrieveAllBooks();
    }

    /**
     * Creates a new book and returns it.
     *
     * @param book the book object to create
     * @return the created book object
     */
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    /**
     * Updates an existing book and returns it.
     *
     * @param book the book object to update
     * @return the updated book object
     */
    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    /**
     * Deletes a book by its id.
     *
     * @param id the id of the book to delete
     */
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }
}
