package abstract_factory;

public class CheckBoxA extends CheckBox {
    public CheckBoxA(String text) {
        super(text);
    }
    public void display() {
        System.out.println("CheckBoxA: " + text);
    }
}