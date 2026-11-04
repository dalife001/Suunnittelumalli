package visitor;

public class Main {
    public static void main(String[] args) {
        Directory root = new Directory("project");
        Directory source = new Directory("src");
        Directory resources = new Directory("resources");

        source.add(new File("Main.java", 2.5));
        source.add(new File("FileSystem.java", 1.8));
        resources.add(new File("application.properties", 0.4));
        resources.add(new File("logo.png", 3.2));
        root.add(source);
        root.add(resources);
        root.add(new File("README.md", 0.1));

        SizeCalculatorVisitor sizeCalculator = new SizeCalculatorVisitor();
        root.accept(sizeCalculator);

        SearchVisitor javaSearch = SearchVisitor.byExtension(".java");
        root.accept(javaSearch);

        System.out.println("Total size: " + sizeCalculator.getTotalSizeInMegabytes() + " MB");
        System.out.println("Java files:");
        for (File file : javaSearch.getMatchingFiles()) {
            System.out.println("- " + file.getName());
        }
    }
}
