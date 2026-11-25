package iterator;

import java.util.Iterator;

/** A computed sequence that can be traversed with an iterator. */
public interface Sequence {
    Iterator<Integer> iterator();
}
