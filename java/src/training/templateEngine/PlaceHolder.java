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

    public String name() {
        // TODO Auto-generated method stub
        return name;
    }

    public String value() {
        // TODO Auto-generated method stub
        return value;
    }

    public void setName(String name) {
        this.name = name;

    }

}
