package visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Predicate;

public final class SearchVisitor implements FileSystemVisitor {
    private final Predicate<File> criterion;
    private final List<File> matchingFiles = new ArrayList<>();

    public SearchVisitor(Predicate<File> criterion) {
        this.criterion = Objects.requireNonNull(criterion, "criterion");
    }

    public static SearchVisitor byExtension(String extension) {
        Objects.requireNonNull(extension, "extension");
        String normalizedExtension = extension.startsWith(".")
                ? extension.toLowerCase(Locale.ROOT)
                : "." + extension.toLowerCase(Locale.ROOT);
        return new SearchVisitor(file -> file.getName().toLowerCase(Locale.ROOT).endsWith(normalizedExtension));
    }

    @Override
    public void visit(File file) {
        if (criterion.test(file)) {
            matchingFiles.add(file);
        }
    }

    @Override
    public void visit(Directory directory) {
        // Directories do not match file criteria.
    }

    public List<File> getMatchingFiles() {
        return Collections.unmodifiableList(matchingFiles);
    }
}
