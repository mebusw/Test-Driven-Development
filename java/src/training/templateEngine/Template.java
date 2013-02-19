package training.templateEngine;

public class Template {
    private String template;

    public void set(String template) {
        this.template = template;
    }

    public String replaceValues(TemplatePlaceholderValues replacementValues) {
        return template.replace(replacementValues.firstPlaceholder().name(),
                replacementValues.firstPlaceholder().value());
    }
}
