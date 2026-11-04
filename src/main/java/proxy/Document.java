package proxy;

import java.time.LocalDate;
import java.util.Objects;

public final class Document implements DocumentInterface {
    private final String identifier;
    private final LocalDate creationDate;
    private final String content;

    public Document(String identifier, LocalDate creationDate, String content) {
        this.identifier = Objects.requireNonNull(identifier, "identifier");
        this.creationDate = Objects.requireNonNull(creationDate, "creationDate");
        this.content = Objects.requireNonNull(content, "content");
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public String getContent(User user) {
        return content;
    }
}
