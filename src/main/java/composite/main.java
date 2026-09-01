package composite;

public class main {
    public static void main(String[] args) {
        Department department = new Department("Engineering");
        Department department2 = new Department("HR");
        Department department3 = new Department("IT");
        Department department4 = new Department("Finance");

        Employee employee1 = new Employee("John", 50000);
        Employee employee2 = new Employee("Jane", 60000);
        Employee employee3 = new Employee("Mike", 55000);
        Employee employee4 = new Employee("Emily", 70000);
        Employee employee5 = new Employee("David", 65000);

        department.add(employee1);
        department.add(employee2);
        department2.add(employee3);
        department3.add(employee4);
        department4.add(employee5);
        
        department.add(department2);
        department.add(department3);
        department.add(department4);
        department2.add(employee4);
        System.out.println("Total Salary: " + department.getSalary());
        System.out.println("Department XML: " + department.toXml());

        
    }
}
