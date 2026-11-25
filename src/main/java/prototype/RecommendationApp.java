package prototype;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public final class RecommendationApp extends Application {
    private final ObservableList<Recommendation> recommendations = FXCollections.observableArrayList();
    private final ListView<Recommendation> recommendationList = new ListView<>(recommendations);
    private final ListView<Book> bookList = new ListView<>();
    private final TextField audienceField = new TextField();
    private final TextField titleField = new TextField();
    private final TextField authorField = new TextField();
    private final TextField genreField = new TextField();
    private final TextField yearField = new TextField();
    private Recommendation workingCopy;

    @Override
    public void start(Stage stage) {
        addSampleRecommendations();
        recommendationList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        recommendationList.getSelectionModel().selectedItemProperty().addListener(
                (observable, previous, selected) -> showRecommendation(selected));

        Button newButton = new Button("New list");
        newButton.setOnAction(event -> startNewRecommendation());
        Button cloneButton = new Button("Clone selected");
        cloneButton.setOnAction(event -> cloneSelectedRecommendation());
        Button saveButton = new Button("Save as new list");
        saveButton.setDefaultButton(true);
        saveButton.setOnAction(event -> saveRecommendation());

        Button addBookButton = new Button("Add book to draft");
        addBookButton.setOnAction(event -> addBook());
        Button removeBookButton = new Button("Remove selected book");
        removeBookButton.setOnAction(event -> removeBook());

        GridPane bookForm = new GridPane();
        bookForm.setHgap(8);
        bookForm.setVgap(8);
        bookForm.addRow(0, new Label("Title"), titleField);
        bookForm.addRow(1, new Label("Author"), authorField);
        bookForm.addRow(2, new Label("Genre"), genreField);
        bookForm.addRow(3, new Label("Publication year"), yearField);
        GridPane.setHgrow(titleField, Priority.ALWAYS);
        GridPane.setHgrow(authorField, Priority.ALWAYS);
        GridPane.setHgrow(genreField, Priority.ALWAYS);
        GridPane.setHgrow(yearField, Priority.ALWAYS);

        VBox left = new VBox(10, new Label("Saved recommendations"), recommendationList,
                new HBox(8, newButton, cloneButton));
        left.setPadding(new Insets(12));
        left.setPrefWidth(280);
        VBox.setVgrow(recommendationList, Priority.ALWAYS);

        VBox right = new VBox(10,
                new Label("Draft target audience"), audienceField,
                new Label("Books in draft"), bookList,
                new Label("Add a book"), bookForm,
                new HBox(8, addBookButton, removeBookButton), saveButton);
        right.setPadding(new Insets(12));
        VBox.setVgrow(bookList, Priority.ALWAYS);

        BorderPane root = new BorderPane();
        root.setLeft(left);
        root.setCenter(right);
        root.setPrefSize(820, 580);
        startNewRecommendation();

        stage.setTitle("Book Recommendations — Prototype Pattern");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void addSampleRecommendations() {
        recommendations.add(new Recommendation("Fantasy readers", List.of(
                new Book("J.R.R. Tolkien", "The Hobbit", "Fantasy", 1937),
                new Book("Ursula K. Le Guin", "A Wizard of Earthsea", "Fantasy", 1968))));
        recommendations.add(new Recommendation("Mystery fans", List.of(
                new Book("Agatha Christie", "Murder on the Orient Express", "Mystery", 1934),
                new Book("Arthur Conan Doyle", "The Hound of the Baskervilles", "Mystery", 1902))));
        recommendations.add(new Recommendation("Science fiction readers", List.of(
                new Book("Octavia E. Butler", "Kindred", "Science fiction", 1979),
                new Book("Frank Herbert", "Dune", "Science fiction", 1965))));
    }

    private void showRecommendation(Recommendation recommendation) {
        if (recommendation != null) {
            workingCopy = null;
            audienceField.setText(recommendation.getTargetAudience());
            bookList.setItems(FXCollections.observableArrayList(recommendation.getBooks()));
        }
    }

    private void startNewRecommendation() {
        recommendationList.getSelectionModel().clearSelection();
        workingCopy = new Recommendation("New audience");
        audienceField.setText(workingCopy.getTargetAudience());
        bookList.setItems(FXCollections.observableArrayList());
    }

    private void cloneSelectedRecommendation() {
        Recommendation selected = recommendationList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Select a saved recommendation to clone first.");
            return;
        }
        workingCopy = selected.clone();
        audienceField.setText(workingCopy.getTargetAudience());
        bookList.setItems(FXCollections.observableArrayList(workingCopy.getBooks()));
    }

    private void addBook() {
        try {
            if (workingCopy == null) {
                workingCopy = selectedRecommendation().clone();
            }
            String yearText = yearField.getText().trim();
            int year = Integer.parseInt(yearText);
            Book book = new Book(authorField.getText(), titleField.getText(), genreField.getText(), year);
            workingCopy.addBook(book);
            refreshDraftBooks();
            titleField.clear();
            authorField.clear();
            genreField.clear();
            yearField.clear();
        } catch (NumberFormatException exception) {
            showError("Enter a valid publication year.");
        } catch (IllegalArgumentException | NullPointerException exception) {
            showError(exception.getMessage() == null ? "Select a recommendation first." : exception.getMessage());
        }
    }

    private void removeBook() {
        Book selected = bookList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Select a book to remove.");
            return;
        }
        if (workingCopy == null) {
            workingCopy = selectedRecommendation().clone();
        }
        workingCopy.removeBook(selected);
        refreshDraftBooks();
    }

    private void saveRecommendation() {
        try {
            if (workingCopy == null) {
                workingCopy = selectedRecommendation().clone();
            }
            workingCopy.setTargetAudience(audienceField.getText());
            Recommendation saved = workingCopy.clone();
            recommendations.add(saved);
            recommendationList.getSelectionModel().select(saved);
            workingCopy = null;
        } catch (IllegalArgumentException | NullPointerException exception) {
            showError(exception.getMessage() == null ? "Select or create a recommendation first." : exception.getMessage());
        }
    }

    private Recommendation selectedRecommendation() {
        Recommendation selected = recommendationList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            throw new IllegalArgumentException("Create a new list or select a saved recommendation first.");
        }
        return selected;
    }

    private void refreshDraftBooks() {
        bookList.setItems(FXCollections.observableArrayList(new ArrayList<>(workingCopy.getBooks())));
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Book recommendations");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
