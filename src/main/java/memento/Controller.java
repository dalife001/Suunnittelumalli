package memento;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Controller {
    private final Model model;
    private final Gui gui;
    private final Deque<IMemento> undoStates = new ArrayDeque<>();
    private final Deque<IMemento> redoStates = new ArrayDeque<>();
    private final List<IMemento> history = new ArrayList<>();

    public Controller(Gui gui) {
        this.model = new Model();
        this.gui = gui;
        history.add(model.createMemento());
    }

    public void setOption(int optionNumber, int choice) {
        saveCurrentState();
        model.setOption(optionNumber, choice);
        history.add(model.createMemento());
    }

    public int getOption(int optionNumber) {
        return model.getOption(optionNumber);
    }

    public void setIsSelected(boolean isSelected) {
        saveCurrentState();
        model.setIsSelected(isSelected);
        history.add(model.createMemento());
    }

    public boolean getIsSelected() {
        return model.getIsSelected();
    }

    public void undo() {
        if (undoStates.isEmpty()) {
            return;
        }
        redoStates.push(model.createMemento());
        model.restoreState(undoStates.pop());
        updateGui();
    }

    public void redo() {
        if (redoStates.isEmpty()) {
            return;
        }
        undoStates.push(model.createMemento());
        model.restoreState(redoStates.pop());
        updateGui();
    }

    public List<IMemento> getHistory() {
        return Collections.unmodifiableList(history);
    }

    public void restoreFromHistory(IMemento state) {
        if (state == null) {
            return;
        }
        undoStates.push(model.createMemento());
        redoStates.clear();
        model.restoreState(state);
        updateGui();
    }

    private void saveCurrentState() {
        IMemento currentState = model.createMemento();
        undoStates.push(currentState);
        redoStates.clear();
    }

    private void updateGui() {
        if (gui != null) {
            gui.updateGui();
        }
    }
}
