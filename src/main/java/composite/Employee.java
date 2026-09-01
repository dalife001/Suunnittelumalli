package composite;

public class Employee extends Component {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void add(Component component) {
        // Leaf node - no operation
    }

    @Override
    public void remove(Component component) {
        // Leaf node - no operation
    }

    public String getName() {
        return name;
    }

    @Override
    public String toXml() {
        return "<Employee><Name>" + name + "</Name><Salary>" + salary + "</Salary></Employee>";
    }
}