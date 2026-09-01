package composite;

import java.util.ArrayList;
import java.util.List;

public class Department extends Component {
    private String name;
    private List<Component> components;

    public Department(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public double getSalary() {
        double total = 0;
        for (Component component : components) {
            total += component.getSalary();
        }
        return total;
    }

    public String getName() {
        return name;
    }

    public List<Component> getComponents() {
        return components;
    }

    @Override
    public String toXml() {
        StringBuilder xml = new StringBuilder();
        xml.append("<Department><Name>").append(name).append("</Name>");
        for (Component component : components) {
            xml.append(component.toXml());  // Polymorphic call - no instanceof needed
        }
        xml.append("</Department>");
        return xml.toString();
    }
}