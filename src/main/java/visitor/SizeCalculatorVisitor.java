package visitor;

public final class SizeCalculatorVisitor implements FileSystemVisitor {
    private double totalSizeInMegabytes;

    @Override
    public void visit(File file) {
        totalSizeInMegabytes += file.getSizeInMegabytes();
    }

    @Override
    public void visit(Directory directory) {
        // Directory sizes are the sum of the files visited inside them.
    }

    public double getTotalSizeInMegabytes() {
        return totalSizeInMegabytes;
    }
}
