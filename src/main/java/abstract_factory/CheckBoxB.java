package abstract_factory;

public class CheckBoxB extends CheckBox {
    public CheckBoxB(String text) {
        super(text);
    }
    public void display() {
        System.out.println("CheckBoxB: " + text);
    }
}