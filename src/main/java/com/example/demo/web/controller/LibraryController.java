package com.example.demo.web.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.data.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.BookService;

/**
 * Controller for the music catalog.
 */
@Controller
@RequestMapping("/library")
public class LibraryController {

    private final BookService bookService;

    @Autowired
    public LibraryController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Shows the book editor screen.
     *
     * @param model the model object to store attributes
     * @param id the id of the book to retrieve
     * @return the name of the edit view to render
     */
    @GetMapping("/{id}")
    public String getBookById(Model model, @PathVariable Long id) {
        Optional<Book> optionalBook = bookService.retrieveBookById(id);
        return optionalBook.map(book -> {
            model.addAttribute("book", book);
            return "library/edit";
        }).orElseGet(() -> {
            model.addAttribute("requestUri", "library/" + id);
            return "library/error";
        });
    }

    /**
     * Shows the book list screen.
     *
     * @param model the model object to store attributes
     * @return the name of the book list view to render
     */
    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> allBooks = bookService.retrieveAllBooks();
        model.addAttribute("books", allBooks);
        return "library/list";
    }

    /**
     * Opens the creation page.
     *
     */
    @GetMapping("/create")
    public String createBook() {
        return "library/create";
    }

    /**
     * Creates a new book.
     * Also navigates back to the editor screen.
     *
     * @param model the model object to store attributes
     * @param book the book object to create
     * @return the name of the edit view to render
     */
    @PostMapping("/create")
    public String createBook(Model model, Book book) {
        Book newBook = bookService.createBook(book);
        model.addAttribute("book", newBook);
        return "library/edit";
    }

    /**
     * Updates an existing song.
     * Also navigates back to the editor screen.
     *
     * @param model the model object to store attributes
     * @param book the book object to update
     * @return the name of the edit view to render
     */
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateBook(Model model, Book book) {
        Book updatedBook = bookService.updateBook(book);
        model.addAttribute("book", updatedBook);
        return "library/edit";
    }

    /**
     * Deletes a song by ID.
     * Also navigates back to the song list screen.
     *
     * @param model the model object to store attributes
     * @param id the id of the book to delete
     * @return the name of the book list view to render
     */
    @GetMapping("/{id}/delete")
    public String deleteBookById(Model model, @PathVariable Long id) {
        bookService.deleteBookById(id);
        List<Book> allBooks = bookService.retrieveAllBooks();
        model.addAttribute("books", allBooks);
        return "library/list";
    }

    /**
     * Brings you to the rating screen.
     *
     * @param model the model object to store attributes
     * @param id the id of the book to delete
     * @return the book that you are rating
     */
    @GetMapping("/{id}/rating")
    public String rateBookById(Model model, @PathVariable Long id) {
        Optional<Book> optionalBook = bookService.retrieveBookById(id);
        return optionalBook.map(book -> {
            model.addAttribute("book", book);
            return "library/rating";
        }).orElseGet(() -> {
            model.addAttribute("requestUri", "library/" + id);
            return "library/error";
        });
    }
}
