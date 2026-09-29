package strategy;

public class sortContext {
    private SortStrategy sortStrategy;

    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void sort(int[] array) {
        sortStrategy.sort(array);
    }
}