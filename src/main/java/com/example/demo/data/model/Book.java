package com.example.demo.data.model;
import java.util.Objects;

/**
 * Model class for books.
 */
public class Book {

    private Long id;
    private String title;
    private String writer;
    private Type type;
    private Genre genre;
    private String publisher;
    private String language;
    private String ISBN;
    private String year_of_release;
    private int rating;

    public Book() {
    }

    public Book(Long id, String title, String writer, Type type, Genre genre, String publisher, String language, String ISBN, String year_of_release, int rating) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.type = type;
        this.genre = genre;
        this.publisher = publisher;
        this.language = language;
        this.ISBN = ISBN;
        this.year_of_release = year_of_release;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String Publisher) {
        this.publisher = Publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getYear_of_release() {
        return year_of_release;
    }

    public void setYear_of_release(String year_of_release) {
        this.year_of_release = year_of_release;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Book book = (Book) o;

        if (!Objects.equals(id, book.id)) {
            return false;
        }
        if (!Objects.equals(title, book.title)) {
            return false;
        }
        if (!Objects.equals(writer, book.writer)) {
            return false;
        }
        if (!Objects.equals(type, book.type)) {
            return false;
        }
        if (!Objects.equals(genre, book.genre)) {
            return false;
        }
        if (!Objects.equals(ISBN, book.ISBN)) {
            return false;
        }
        if (!Objects.equals(language, book.language)) {
            return false;
        }
        if (!Objects.equals(publisher, book.publisher)) {
            return false;
        }
        if (!Objects.equals(year_of_release, book.year_of_release)) {
            return false;
        }
        if (!Objects.equals(rating, book.rating)) {
            return false;
        }
        return title == book.title;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 4 * result + (title != null ? title.hashCode() : 0);
        result = 4 * result + (writer != null ? writer.hashCode() : 0);
        result = 4 * result + (type != null ? type.hashCode() : 0);
        result = 4 * result + (genre != null ? genre.hashCode() : 0);
        result = 4 * result + (publisher != null ? genre.hashCode() : 0);
        result = 4 * result + (language != null ? genre.hashCode() : 0);
        result = 4 * result + (ISBN != null ? genre.hashCode() : 0);
        result = 4 * result + (year_of_release != null ? genre.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{"
                + "Id=" + id + '\''
                + ", Title='" + title + '\''
                + ", Writer='" + writer + '\''
                + ", Type='" + type + '\''
                + ", Genre=" + genre + '\''
                + ", Publisher=" + publisher + '\''
                + ", Original Language=" + language + '\''
                + ", ISBN=" + ISBN + '\''
                + ", Year of release=" + year_of_release + '\''
                + ", Rating=" + rating + '\''
                + '}';
    }
}
