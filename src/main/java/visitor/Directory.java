package visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Directory implements FileSystemElement {
    private final String name;
    private final List<FileSystemElement> elements = new ArrayList<>();

    public Directory(String name) {
        this.name = Objects.requireNonNull(name, "name");
    }

    public String getName() {
        return name;
    }

    public void add(FileSystemElement element) {
        elements.add(Objects.requireNonNull(element, "element"));
    }

    public void remove(FileSystemElement element) {
        elements.remove(element);
    }

    public List<FileSystemElement> getElements() {
        return Collections.unmodifiableList(elements);
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        Objects.requireNonNull(visitor, "visitor").visit(this);
        for (FileSystemElement element : elements) {
            element.accept(visitor);
        }
    }
}
