package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Iterates through the Fibonacci sequence beginning with 1, 1. */
public final class FibonacciIterator implements Iterator<Integer> {
    private final FibonacciSequence sequence;
    private final int limit;
    private int previous = 0;
    private int current = 1;
    private int produced;

    /**
     * Each iterator owns its own previous/current values and count. This keeps
     * iterators independent: creating or using one cannot change another's progress.
     */
    FibonacciIterator(FibonacciSequence sequence) {
        this(sequence, Integer.MAX_VALUE);
    }

    FibonacciIterator(FibonacciSequence sequence, int limit) {
        this.sequence = sequence;
        this.limit = limit;
    }

    @Override
    public boolean hasNext() {
        return produced < limit;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("The Fibonacci sequence limit has been reached.");
        }

        int result = current;
        current = sequence.nextFibonacci(previous, current);
        previous = result;
        produced++;
        return result;
    }
}
