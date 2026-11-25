package mediator;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/** Central message router. Chat clients communicate only through this mediator. */
public final class ChatMediator {
    private final Map<String, ChatClientController> clients = new LinkedHashMap<>();

    public void register(ChatClientController client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null.");
        }
        ChatClientController existing = clients.putIfAbsent(client.getUsername(), client);
        if (existing != null) {
            throw new IllegalArgumentException("Username is already registered: " + client.getUsername());
        }
    }

    public Collection<String> getUsernames() {
        return java.util.List.copyOf(clients.keySet());
    }

    public void sendMessage(ChatClientController sender, String recipientUsername, String message) {
        ChatClientController recipient = clients.get(recipientUsername);
        if (recipient == null) {
            throw new IllegalArgumentException("Unknown recipient: " + recipientUsername);
        }
        recipient.receiveMessage(sender.getUsername(), message);
    }
}
