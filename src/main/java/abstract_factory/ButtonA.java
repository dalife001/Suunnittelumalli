package abstract_factory;

public class ButtonA extends Button {
    public ButtonA(String text) {
        super(text);
    }
    public void display() {
        System.out.println("ButtonA: " + text);
    }
}