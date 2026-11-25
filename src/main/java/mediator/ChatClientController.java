package mediator;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;

/** Application logic for one chat client; it routes messages through the mediator. */
public final class ChatClientController {
    private final String username;
    private final ChatMediator mediator;
    private Consumer<String> messageDisplay = ignored -> { };

    public ChatClientController(String username, ChatMediator mediator) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank.");
        }
        this.username = username;
        this.mediator = Objects.requireNonNull(mediator, "mediator");
        mediator.register(this);
    }

    public String getUsername() {
        return username;
    }

    public Collection<String> getOtherUsernames() {
        return mediator.getUsernames().stream()
                .filter(name -> !name.equals(username))
                .toList();
    }

    public void setMessageDisplay(Consumer<String> messageDisplay) {
        this.messageDisplay = Objects.requireNonNull(messageDisplay, "messageDisplay");
    }

    public void sendMessage(String recipientUsername, String message) {
        if (recipientUsername == null || recipientUsername.isBlank()) {
            throw new IllegalArgumentException("Select a recipient first.");
        }
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("Type a message first.");
        }

        String cleanMessage = message.trim();
        mediator.sendMessage(this, recipientUsername, cleanMessage);
        messageDisplay.accept("To " + recipientUsername + ": " + cleanMessage + System.lineSeparator());
    }

    void receiveMessage(String senderUsername, String message) {
        messageDisplay.accept("From " + senderUsername + ": " + message + System.lineSeparator());
    }
}
