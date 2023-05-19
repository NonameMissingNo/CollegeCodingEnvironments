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
 * A REST controller for managing songs in the music catalog.
 */
@RestController
@RequestMapping("/api/v1/song")
public class LibraryRestController {

    private final BookService bookService;

    @Autowired
    public LibraryRestController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Returns a song with the given id.
     *
     * @param id the id of the song to retrieve
     * @return the song object
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getSongById(@PathVariable Long id) {
        Optional<Book> song = bookService.retrieveBookById(id);
        return song.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Returns a list of all the songs in the catalog.
     *
     * @return the list of songs
     */
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.retrieveAllBooks();
    }

    /**
     * Creates a new song and returns it.
     *
     * @param song the song object to create
     * @return the created song object
     */
    @PostMapping
    public Book createSong(@RequestBody Book song) {
        return bookService.createBook(song);
    }

    /**
     * Updates an existing song and returns it.
     *
     * @param song the song object to update
     * @return the updated song object
     */
    @PutMapping
    public Book updateSong(@RequestBody Book song) {
        return bookService.updateBook(song);
    }

    /**
     * Deletes a song by its id.
     *
     * @param id the id of the song to delete
     */
    @DeleteMapping("/{id}")
    public void deleteSongById(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }
}
