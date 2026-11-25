package mediator;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/** Launches three local chat windows connected by one mediator. */
public final class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        ChatMediator mediator = new ChatMediator();
        List<ChatClientController> clients = new ArrayList<>();
        clients.add(new ChatClientController("Alice", mediator));
        clients.add(new ChatClientController("Bob", mediator));
        clients.add(new ChatClientController("Charlie", mediator));

        for (int i = 0; i < clients.size(); i++) {
            Stage stage = i == 0 ? primaryStage : new Stage();
            new ChatWindow(clients.get(i), stage).show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
