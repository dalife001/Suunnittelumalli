package prototype;

import java.util.Objects;

/** A book that can be copied as part of a recommendation prototype. */
public final class Book implements Cloneable {
    private final String author;
    private final String title;
    private final String genre;
    private final int publicationYear;

    public Book(String author, String title, String genre, int publicationYear) {
        this.author = requireText(author, "author");
        this.title = requireText(title, "title");
        this.genre = requireText(genre, "genre");
        if (publicationYear < 0) {
            throw new IllegalArgumentException("Publication year cannot be negative.");
        }
        this.publicationYear = publicationYear;
    }

    private static String requireText(String value, String field) {
        Objects.requireNonNull(value, field);
        if (value.isBlank()) {
            throw new IllegalArgumentException(field + " cannot be blank.");
        }
        return value.trim();
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    @Override
    public Book clone() {
        return new Book(author, title, genre, publicationYear);
    }

    @Override
    public String toString() {
        return title + " — " + author + " (" + genre + ", " + publicationYear + ")";
    }
}
