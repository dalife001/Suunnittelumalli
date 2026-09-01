package abstract_factory;

public class ButtonB extends Button {
    public ButtonB(String text) {
        super(text);
    }
    public void display() {
        System.out.println("ButtonB: " + text);
    }
}