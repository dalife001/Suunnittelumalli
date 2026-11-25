package iterator;

import java.util.Iterator;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        FibonacciSequence sequence = new FibonacciSequence();
        Iterator<Integer> fibonacci = sequence.iterator(10);

        while (fibonacci.hasNext()) {
            System.out.println(fibonacci.next());
        }
    }
}
