package proxy;

import java.util.HashMap;
import java.util.Map;

public class Library {
    private final Map<String, DocumentInterface> documents = new HashMap<>();

    public void addDocument(Document document) {
        documents.put(document.getIdentifier(), document);
    }

    public void addProtectedDocument(Document document) {
        DocumentProxy proxy = new DocumentProxy(document);
        documents.put(proxy.getIdentifier(), proxy);
    }

    public DocumentInterface getDocument(String identifier) {
        return documents.get(identifier);
    }
}
