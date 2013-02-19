package training.templateEngine;

public class TemplatePlaceholderValues {
    private PlaceHolder placeholder = new PlaceHolder("");

    public PlaceHolder placeholder(String name) {
        placeholder.setName(name);
        return placeholder;
    }

    public PlaceHolder firstPlaceholder() {
        return placeholder;
    }

}
