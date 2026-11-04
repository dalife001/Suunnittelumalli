package proxy;

import java.util.HashSet;
import java.util.Set;

public final class AccessControlService {
    private static final AccessControlService INSTANCE = new AccessControlService();
    private final Set<String> permissions = new HashSet<>();

    private AccessControlService() {
    }

    public static AccessControlService getInstance() {
        return INSTANCE;
    }

    public synchronized void grantAccess(String documentIdentifier, String username) {
        permissions.add(key(documentIdentifier, username));
    }

    public synchronized void revokeAccess(String documentIdentifier, String username) {
        permissions.remove(key(documentIdentifier, username));
    }

    public synchronized boolean isAllowed(String documentIdentifier, String username) {
        return permissions.contains(key(documentIdentifier, username));
    }

    private String key(String documentIdentifier, String username) {
        return documentIdentifier + "\u0000" + username;
    }
}
