package com.example.demo.service;

import com.example.demo.data.model.Book;

import java.util.List;
import java.util.Optional;


/**
 * A service for managing books in the library.
 */
public interface BookService {

    /**
     * Creates a new book.
     *
     * @param book the book to create
     * @return the created book
     */
    Book createBook(Book book);

    /**
     * Retrieves a book by its id.
     *
     * @param id the id of the book to retrieve
     * @return the retrieved book or empty optional if it was not found
     */
    Optional<Book> retrieveBookById(Long id);

    /**
     * Retrieves all the books in the library.
     *
     * @return the list of found books
     */
    List<Book> retrieveAllBooks();

    /**
     * Updates an existing book.
     *
     * @param book the book to update
     * @return the updated book
     */
    Book updateBook(Book book);

    /**
     * Deletes a book by its id.
     *
     * @param id the id of the book to delete
     */
    void deleteBookById(Long id);
}
