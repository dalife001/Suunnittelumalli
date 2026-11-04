package visitor;

import java.util.Objects;

public final class File implements FileSystemElement {
    private final String name;
    private final double sizeInMegabytes;

    public File(String name, double sizeInMegabytes) {
        this.name = Objects.requireNonNull(name, "name");
        if (sizeInMegabytes < 0) {
            throw new IllegalArgumentException("sizeInMegabytes cannot be negative");
        }
        this.sizeInMegabytes = sizeInMegabytes;
    }

    public String getName() {
        return name;
    }

    public double getSizeInMegabytes() {
        return sizeInMegabytes;
    }

    @Override
    public void accept(FileSystemVisitor visitor) {
        Objects.requireNonNull(visitor, "visitor").visit(this);
    }
}
