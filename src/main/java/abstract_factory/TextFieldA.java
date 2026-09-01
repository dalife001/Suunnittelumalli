package abstract_factory;

public class TextFieldA extends TextField {
    public TextFieldA(String text) {
        super(text);
    }
    public void display() {
        System.out.println("TextFieldA: " + text);
    }
}