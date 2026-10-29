package memento;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;

public class Gui extends Application {
    private Controller controller;
    private ColorBox colorBox1;
    private ColorBox colorBox2;
    private ColorBox colorBox3;
    private CheckBox checkBox;

    @Override
    public void start(Stage stage) {
        controller = new Controller(this);
        Insets insets = new Insets(10);

        colorBox1 = new ColorBox(1, controller);
        colorBox2 = new ColorBox(2, controller);
        colorBox3 = new ColorBox(3, controller);
        checkBox = new CheckBox("Click me!");
        checkBox.setPadding(insets);

        HBox boxes = new HBox(colorBox1.getRectangle(), colorBox2.getRectangle(), colorBox3.getRectangle());
        boxes.setSpacing(10);
        HBox.setMargin(colorBox1.getRectangle(), insets);
        HBox.setMargin(colorBox2.getRectangle(), insets);
        HBox.setMargin(colorBox3.getRectangle(), insets);

        checkBox.setOnAction(event -> controller.setIsSelected(checkBox.isSelected()));
        Button historyButton = new Button("History");
        historyButton.setOnAction(event -> showHistoryWindow());
        Label instructions = new Label("Ctrl-Z: undo    Ctrl-Y: redo");
        instructions.setPadding(insets);

        VBox root = new VBox(boxes, checkBox, new HBox(historyButton), instructions);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.Z) {
                controller.undo();
            } else if (event.isControlDown() && event.getCode() == KeyCode.Y) {
                controller.redo();
            }
        });

        stage.setScene(scene);
        stage.setTitle("Memento Pattern");
        stage.show();
    }

    private void showHistoryWindow() {
        ListView<IMemento> historyList = new ListView<>();
        historyList.getItems().setAll(controller.getHistory());
        historyList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, selectedState) -> controller.restoreFromHistory(selectedState));

        Stage historyStage = new Stage();
        historyStage.setTitle("Model history");
        historyStage.setScene(new Scene(historyList, 520, 300));
        historyStage.show();
    }

    public void updateGui() {
        colorBox1.setColor(controller.getOption(1));
        colorBox2.setColor(controller.getOption(2));
        colorBox3.setColor(controller.getOption(3));
        checkBox.setSelected(controller.getIsSelected());
    }
}
