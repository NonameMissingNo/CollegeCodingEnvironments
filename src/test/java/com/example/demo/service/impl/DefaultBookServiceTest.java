package com.example.demo.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.example.demo.data.model.Book;
import com.example.demo.data.model.Type;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.data.model.Genre;
import com.example.demo.data.repository.Repository;
import com.example.demo.service.BookService;

/**
 * Unit tests for {@link DefaultBookService}.
 */
class DefaultBookServiceTest {

    private static final Long DUMMY_BOOK_ID = 1L;
    private static final Book DUMMY_BOOK = new Book(DUMMY_BOOK_ID, "title", "writer", Type.Folksong, Genre.Fantasy, "publish", "english", "isbn", "1999", 0);


    @Mock
    private Repository<Book, Long> bookRepository;
    private BookService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new DefaultBookService(bookRepository);
    }

    @Test
    void createBookShouldDelegateToTheRepositoryAndReturnSavedBook() {
        // Given
        given(bookRepository.save(DUMMY_BOOK)).willReturn(DUMMY_BOOK);

        // When
        final Book actual = underTest.createBook(DUMMY_BOOK);

        // Then
        assertThat(actual, equalTo(DUMMY_BOOK));
        verify(bookRepository).save(DUMMY_BOOK);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void retrieveBookByIdShouldDelegateToTheRepositoryAndReturnFoundBook() {
        // Given
        given(bookRepository.getById(DUMMY_BOOK_ID)).willReturn(Optional.of(DUMMY_BOOK));

        // When
        final Optional<Book> actual = underTest.retrieveBookById(DUMMY_BOOK_ID);

        // Then
        assertThat(actual, equalTo(Optional.of(DUMMY_BOOK)));
        verify(bookRepository).getById(DUMMY_BOOK_ID);
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    void retrieveAllBooksShouldDelegateToTheRepositoryAndReturnAllFoundBooks() {
        // Given
        given(bookRepository.getAll()).willReturn(List.of(DUMMY_BOOK));

        // When
        final List<Book> actual = underTest.retrieveAllBooks();

        // Then
        assertThat(actual, equalTo(List.of(DUMMY_BOOK)));
        verify(bookRepository).getAll();
        verifyNoMoreInteractions(bookRepository);
    }
    @Test
    void DeleteBookById() {

        when(bookRepository.getById(DUMMY_BOOK_ID)).thenReturn(Optional.of(DUMMY_BOOK)).thenReturn(null);
        // When
        final Optional<Book> actual = underTest.retrieveBookById(DUMMY_BOOK_ID);
        underTest.deleteBookById((DUMMY_BOOK_ID));
        assertThat(underTest.retrieveBookById(DUMMY_BOOK_ID), new IsNull());
        // Then
        verify(bookRepository, times(2)).getById(DUMMY_BOOK_ID);
        verify(bookRepository).deleteById(DUMMY_BOOK_ID);
        verifyNoMoreInteractions(bookRepository);
    }
    @Test
    void UpdateBook() {
        given(bookRepository.getById(DUMMY_BOOK_ID)).willReturn(Optional.of(DUMMY_BOOK));
        given(bookRepository.update(DUMMY_BOOK)).willReturn(new Book(DUMMY_BOOK_ID, "title", "writer", Type.Folksong, Genre.Fantasy, "publish", "english", "test", "1999", 0));
        // When
        final Book actual = underTest.retrieveBookById(DUMMY_BOOK_ID).get();
        actual.setISBN("test");
        Book value = underTest.updateBook(actual);
        // Then
        assertThat(underTest.retrieveBookById(DUMMY_BOOK_ID).get().getISBN(), equalTo("test"));
        Assertions.assertEquals(actual, value);
        verify(bookRepository, times(2)).getById(DUMMY_BOOK_ID);
        verify(bookRepository).update(DUMMY_BOOK);
        verifyNoMoreInteractions(bookRepository);
    }
}
