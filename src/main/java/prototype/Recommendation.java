package prototype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/** A mutable recommendation prototype with independently cloned books. */
public final class Recommendation implements Cloneable {
    private String targetAudience;
    private final List<Book> books;

    public Recommendation(String targetAudience) {
        this(targetAudience, List.of());
    }

    public Recommendation(String targetAudience, List<Book> books) {
        setTargetAudience(targetAudience);
        this.books = new ArrayList<>();
        for (Book book : Objects.requireNonNull(books, "books")) {
            this.books.add(Objects.requireNonNull(book, "book").clone());
        }
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        Objects.requireNonNull(targetAudience, "targetAudience");
        if (targetAudience.isBlank()) {
            throw new IllegalArgumentException("Target audience cannot be blank.");
        }
        this.targetAudience = targetAudience.trim();
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public void addBook(Book book) {
        books.add(Objects.requireNonNull(book, "book"));
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    @Override
    public Recommendation clone() {
        return new Recommendation(targetAudience, books);
    }

    @Override
    public String toString() {
        return targetAudience + " (" + books.size() + " books)";
    }
}
