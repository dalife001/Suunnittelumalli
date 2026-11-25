package iterator;

import java.util.Iterator;

public final class FibonacciSequence implements Sequence {
    @Override
    public Iterator<Integer> iterator() {
        return new FibonacciIterator(this);
    }

    public Iterator<Integer> iterator(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Limit cannot be negative.");
        }
        return new FibonacciIterator(this, limit);
    }

    int nextFibonacci(int previous, int current) {
        return Math.addExact(previous, current);
    }
}
