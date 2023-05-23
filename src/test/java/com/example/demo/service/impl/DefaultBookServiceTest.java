package com.example.demo.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.example.demo.data.model.Book;
import com.example.demo.data.model.Type;
import org.hamcrest.core.IsNull;
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
    void updateBook() {
        given(bookRepository.getById(DUMMY_BOOK_ID)).willReturn(Optional.of(DUMMY_BOOK));
        Optional<Book> book = underTest.retrieveBookById(DUMMY_BOOK_ID);
        DUMMY_BOOK.setISBN("Something");
        Book bookedited = underTest.updateBook(DUMMY_BOOK);
        verify(bookedited.getISBN().equals("Something"));
    }

    @Test
    void deleteBookById() {
        given(bookRepository.getById(DUMMY_BOOK_ID)).willReturn(Optional.of(DUMMY_BOOK));
        underTest.deleteBookById(DUMMY_BOOK_ID);
        verify(DUMMY_BOOK.equals(null));
    }
}
