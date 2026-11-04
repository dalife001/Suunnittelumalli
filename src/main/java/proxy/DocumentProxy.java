package proxy;

import java.time.LocalDate;
import java.util.Objects;

public class DocumentProxy implements DocumentInterface {
    private final Document document;
    private final AccessControlService accessControlService;

    public DocumentProxy(Document document) {
        this.document = Objects.requireNonNull(document, "document");
        this.accessControlService = AccessControlService.getInstance();
    }

    @Override
    public String getContent(User user) {
        Objects.requireNonNull(user, "user");
        if (!accessControlService.isAllowed(document.getIdentifier(), user.getUsername())) {
            throw new AccessDeniedException("User '" + user.getUsername()
                    + "' is not allowed to access document '" + document.getIdentifier() + "'");
        }
        return document.getContent(user);
    }

    @Override
    public LocalDate getCreationDate() {
        return document.getCreationDate();
    }

    public String getIdentifier() {
        return document.getIdentifier();
    }
}
