package abstract_factory;

public class Main {
    public static void main(String[] args) {
        AFactory factoryA = new AFactory();   // NOT UiFactoryA
        BFactory factoryB = new BFactory();   // NOT UIfactoryB

        Button buttonA = factoryA.createButton("Okay");
        CheckBox checkBoxA = factoryA.createCheckBox("Accept");
        TextField textFieldA = factoryA.createTextField("Enter your name");

        Button buttonB = factoryB.createButton("Submit");
        CheckBox checkBoxB = factoryB.createCheckBox("I agree");
        TextField textFieldB = factoryB.createTextField("Enter your email");

        buttonA.display();
        checkBoxA.display();
        textFieldA.display();
        System.out.println("\n");
        buttonB.display();
        checkBoxB.display();
        textFieldB.display();
        System.out.println("\n");
        System.out.println("\n");
    }
}