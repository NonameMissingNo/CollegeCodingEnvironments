package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.demo.data.model.Book;
import com.example.demo.data.repository.Repository;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Default implementation of {@link BookService}.
 */
@Service
public class DefaultBookService implements BookService {

    private final Repository<Book, Long> bookRepository;

    @Autowired
    public DefaultBookService(Repository<Book, Long> bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> retrieveBookById(Long id) {
        return bookRepository.getById(id);
    }

    @Override
    public List<Book> retrieveAllBooks() {
        return bookRepository.getAll();
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.update(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> rateBookById(Long id) {
        return bookRepository.getById(id);
    }
}
