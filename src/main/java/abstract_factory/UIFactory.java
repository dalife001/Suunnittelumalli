package abstract_factory;

public abstract class UIFactory {
    public abstract Button createButton(String text);
    public abstract CheckBox createCheckBox(String text);
    public abstract TextField createTextField(String text);
}