package abstract_factory;
public class AFactory extends UIFactory{
    public Button createButton(String text){
        return new ButtonA(text);
    }
    public CheckBox createCheckBox(String text){
        return new CheckBoxA(text);
    }
    public TextField createTextField(String text){
        return new TextFieldA(text);
    }
}