package training.templateEngine;

import java.util.ArrayList;
import java.util.List;

public class TemplatePlaceholderValues {
    private List<PlaceHolder> placeholders = new ArrayList<PlaceHolder>();

    public PlaceHolder placeholder(String name) {
        PlaceHolder placeholder = new PlaceHolder(name);
        placeholders.add(placeholder);
        return placeholder;
    }

    public String replaceValuesIn(String template) {
        String replacedTemplate = template;
        for (PlaceHolder placeholder : placeholders) {
            replacedTemplate = placeholder.replaceValuesIn(replacedTemplate);
        }
        return replacedTemplate;
    }

}
