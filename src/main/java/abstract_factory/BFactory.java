package abstract_factory;
public class BFactory extends UIFactory{
    public Button createButton(String text){
        return new ButtonB(text);
    }
    public CheckBox createCheckBox(String text){
        return new CheckBoxB(text);
    }
    public TextField createTextField(String text){
        return new TextFieldB(text);
    }
}