package training.templateEngine;

/**
 * Created with IntelliJ IDEA.
 * User: mebusw
 * Date: 13-9-7
 * Time: 下午10:44
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TemplateEngineTest {
    private TemplateEngine engine;

    @Before
    public void setUp() {
    }

    @Test
    public void testTemplateWithNoVariable() {
        engine = new TemplateEngine("Hello");
        assertEquals("Hello", engine.evaluate());
    }

    @Test
    public void testTemplateWithOneVariable() {
        engine = new TemplateEngine("Hello, $name");
        engine.set("$name", "Jacky");
        assertEquals("Hello, Jacky", engine.evaluate());
    }

    @Test
    public void testTemplateWithTwoVariables() {
        engine = new TemplateEngine("Hello, $name $family");
        engine.set("$name", "Jacky");
        engine.set("$family", "Shen");
        assertEquals("Hello, Jacky Shen", engine.evaluate());
    }
}

class TemplateEngine {
    private String templateString;
    //    private PlaceHolder placeHolder;
    private List<PlaceHolder> placeHolders;

    public TemplateEngine(String templateString) {
        this.templateString = templateString;
//        this.placeHolder = PlaceHolder.NULL;
        this.placeHolders = new ArrayList<PlaceHolder>();
    }

    public String evaluate() {
        String replaced = this.templateString;
        for (PlaceHolder placeHolder : this.placeHolders) {
            replaced = placeHolder.replaceIn(replaced);
        }
        return replaced;
    }

    public void set(String variable, String value) {
        this.placeHolders.add(new PlaceHolder(variable, value));
    }
}

class PlaceHolder {
    private String name;
    private String value;
    public static PlaceHolder NULL = new PlaceHolder(null, null) {
        public String replaceIn(String templateString) {
            return templateString;
        }
    };

    public PlaceHolder(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String replaceIn(String templateString) {
        return templateString.replace(this.getName(), this.getValue());
    }

    String getName() {
        return name;
    }

    String getValue() {
        return value;
    }
}


