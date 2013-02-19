package training.templateEngine;

public class TemplatePlaceholderValues {
    private PlaceHolder placeholder = new PlaceHolder("");

    public PlaceHolder placeholder(String name) {
        placeholder.setName(name);
        return placeholder;
    }

    public String replaceValuesIn(String template) {
        //return template.replace(placeholder.name(), placeholder.value());
        return placeholder.replaceValuesIn(template);
    }

}
