package strategy;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        sortContext context = new sortContext();
        SortStrategy[] strategies = {
                new Bubble_Sort(),
                new Quick_Sort(),
                new Insertion_Sort()
        };
        String[] strategyNames = {"Bubble Sort", "Quick Sort", "Insertion Sort"};

        int[] smallData = createRandomArray(30);
        int[] largeData = createRandomArray(100_000);

        System.out.println("===== Sorting Strategy Comparison =====");
        runBenchmark("Small dataset (30 integers)", smallData, context, strategies, strategyNames);
        runBenchmark("Large dataset (100,000 integers)", largeData, context, strategies, strategyNames);
    }

    private static void runBenchmark(
            String datasetName,
            int[] data,
            sortContext context,
            SortStrategy[] strategies,
            String[] strategyNames) {
        int[] expected = data.clone();
        Arrays.sort(expected);

        System.out.println("\n" + datasetName);
        for (int i = 0; i < strategies.length; i++) {
            int[] values = data.clone();
            context.setSortStrategy(strategies[i]);

            long start = System.nanoTime();
            context.sort(values);
            long elapsed = System.nanoTime() - start;

            if (!Arrays.equals(values, expected)) {
                throw new IllegalStateException(strategyNames[i] + " did not sort the data correctly");
            }
            System.out.printf("%-16s %d ns%n", strategyNames[i] + ":", elapsed);
        }
    }

    public static int[] createRandomArray(int size){
        Random random = new Random();
        int[] array = new int[size];

        for (int i = 0; i< size; i++){
            array[i] = random.nextInt(100000);

        }


        return array;
    }
}