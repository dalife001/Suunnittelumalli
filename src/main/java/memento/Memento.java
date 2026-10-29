package memento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Memento implements IMemento {
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final int[] options;
    private final boolean isSelected;
    private final LocalDateTime savedAt;

    public Memento(int[] options, boolean isSelected) {
        this(options, isSelected, LocalDateTime.now());
    }

    public Memento(int[] options, boolean isSelected, LocalDateTime savedAt) {
        if (options == null || options.length != 3) {
            throw new IllegalArgumentException("A memento must contain three color options");
        }
        this.options = options.clone();
        this.isSelected = isSelected;
        this.savedAt = savedAt;
    }

    public int[] getOptions() {
        return options.clone();
    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    @Override
    public String getDescription() {
        return savedAt.format(DISPLAY_FORMAT)
                + " - colors: " + options[0] + ", " + options[1] + ", " + options[2]
                + ", checkbox: " + (isSelected ? "selected" : "not selected");
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
