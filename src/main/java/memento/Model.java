package memento;

public class Model {
    private final int[] options = new int[3];
    private boolean isSelected;

    public void setOption(int optionNumber, int choice) {
        if (optionNumber < 1 || optionNumber > 3) {
            return;
        }
        options[optionNumber - 1] = choice;
    }

    public int getOption(int optionNumber) {
        if (optionNumber < 1 || optionNumber > 3) {
            return -1;
        }
        return options[optionNumber - 1];
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public IMemento createMemento() {
        return new Memento(options, isSelected);
    }

    public void restoreState(IMemento memento) {
        if (!(memento instanceof Memento savedState)) {
            throw new IllegalArgumentException("Error invalid memento");
        }
        int[] savedOptions = savedState.getOptions();
        System.arraycopy(savedOptions, 0, options, 0, options.length);
        isSelected = savedState.isSelected();
    }
}
