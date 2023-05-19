package com.example.demo.data.repository.impl;

import com.example.demo.data.model.Book;
import com.example.demo.data.repository.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Map-based in-memory implementation of {@link Repository}.
 */
@org.springframework.stereotype.Repository
public class BookRepository implements Repository<Book, Long> {

    private static final Map<Long, Book> STORAGE = new HashMap<>();

    @Override
    public Book save(Book book) {
        Long id = STORAGE.size() + 1L;
        book.setId(id);
        STORAGE.put(id, book);
        return STORAGE.get(id);
    }

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.ofNullable(STORAGE.get(id));
    }

    @Override
    public List<Book> getAll() {
        return STORAGE.values().stream().toList();
    }

    @Override
    public Book update(Book book) {
        Long id = book.getId();
        STORAGE.put(id, book);
        return STORAGE.get(id);
    }

    @Override
    public void deleteById(Long id) {
        STORAGE.remove(id);
    }
}
