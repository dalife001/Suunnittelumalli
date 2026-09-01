package composite;

public abstract class Component {
    public abstract double getSalary();
    public abstract void add(Component component);
    public abstract void remove(Component component);
    public abstract String toXml();
}