package mediator;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** JavaFX view for one client. All chat behavior is delegated to its controller. */
public final class ChatWindow {
    private final ChatClientController controller;
    private final Stage stage;
    private final TextArea conversation = new TextArea();
    private final TextField messageField = new TextField();
    private final ComboBox<String> recipientSelector = new ComboBox<>();

    public ChatWindow(ChatClientController controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
        createWindow();
    }

    private void createWindow() {
        stage.setTitle("Chat - " + controller.getUsername());

        conversation.setEditable(false);
        conversation.setWrapText(true);
        conversation.setPromptText("Messages will appear here");

        recipientSelector.getItems().setAll(controller.getOtherUsernames());
        if (!recipientSelector.getItems().isEmpty()) {
            recipientSelector.getSelectionModel().selectFirst();
        }

        messageField.setPromptText("Type a message");
        Button sendButton = new Button("Send");
        sendButton.setOnAction(event -> sendMessage());
        messageField.setOnAction(event -> sendMessage());

        HBox composeRow = new HBox(8, new Label("To:"), recipientSelector, messageField, sendButton);
        HBox.setHgrow(messageField, Priority.ALWAYS);

        VBox root = new VBox(10, conversation, composeRow);
        root.setPadding(new Insets(12));
        VBox.setVgrow(conversation, Priority.ALWAYS);

        stage.setScene(new Scene(root, 560, 380));
        controller.setMessageDisplay(conversation::appendText);
    }

    public void show() {
        stage.show();
    }

    private void sendMessage() {
        try {
            controller.sendMessage(recipientSelector.getValue(), messageField.getText());
            messageField.clear();
        } catch (IllegalArgumentException exception) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Chat message");
            alert.setHeaderText(null);
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
        }
    }
}
