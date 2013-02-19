package training.templateEngine;

public class PlaceHolder {

    private String name = "";
    private String value = "";

    public PlaceHolder(String name) {
        this.name = name;
    }

    public void hasValue(String value) {
        this.value = value;

    }

    public void setName(String name) {
        this.name = name;

    }

    public String replaceValuesIn(String template) {
        return template.replace(name, value);
    }

}
