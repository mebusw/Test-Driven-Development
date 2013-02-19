package training.templateEngine;

public class TemplatePlaceholderValues {
    private PlaceHolder placeholder = new PlaceHolder("");

    public PlaceHolder placeholder(String name) {
        // TODO Auto-generated method stub
        placeholder = new PlaceHolder(name);
        return placeholder;
    }

    public PlaceHolder firstPlaceholder() {
        // TODO Auto-generated method stub
        return placeholder;
    }

}
