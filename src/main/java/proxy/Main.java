package proxy;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        User alice = new User("alice");
        User bob = new User("bob");

        Document publicDocument = new Document(
                "public-1", java.time.LocalDate.of(2026, 1, 15), "Public content");
        Document protectedDocument = new Document(
                "private-1", java.time.LocalDate.of(2026, 1, 16), "Private content");

        library.addDocument(publicDocument);
        library.addProtectedDocument(protectedDocument);
        AccessControlService.getInstance().grantAccess("private-1", "alice");

        System.out.println(library.getDocument("public-1").getContent(bob));
        System.out.println(library.getDocument("private-1").getContent(alice));
        try {
            library.getDocument("private-1").getContent(bob);
        } catch (AccessDeniedException exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println(library.getDocument("private-1").getCreationDate());
    }
}
