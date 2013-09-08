package training.templateEngine;


import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class TemplateEngineTest {
    private TemplateEngine engine;
    private Map<String, String> placeHolders;

    @Before
    public void setUp() {
        engine = new TemplateEngine();
        placeHolders = new HashMap<String, String>();
    }

    @Test
    public void testTemplateWithNoVariable() {
        engine.compile("Hello");

        assertEquals("Hello", engine.evaluate(placeHolders));
    }

    @Test
    public void testTemplateWithOneVariable() {
        engine.compile("Hello, $name");
        placeHolders.put("$name", "Jacky");

        assertEquals("Hello, Jacky", engine.evaluate(placeHolders));
    }

    @Test
    public void testTemplateWithTwoVariables() {
        engine.compile("Hello, $name $family");
        placeHolders.put("$name", "Jacky");
        placeHolders.put("$family", "Shen");

        assertEquals("Hello, Jacky Shen", engine.evaluate(placeHolders));
    }

    //    @Test
//    public void testTemplateWithSpecialVariables() {
//        engine.compile("Hello, $name $family");
//        placeHolders.put("$name", "$family");
//        placeHolders.put("$family", "$name");
//
//        assertEquals("Hello, $family $name", engine.evaluate(placeHolders));
//    }
    @Test
    public void testCompileTemplateOfPureTextToSegments() {
        List<String> segments = engine.compile("Hello");
        assertSegments(segments, "Hello");
    }

    @Test
    public void testCompileTemplateToSegments() {
        List<String> segments = engine.compile("Hello ${aaa} ${bbb}");
        assertSegments(segments, "Hello ", "aaa", " ", "bbb");
    }

    ///////////////////
    private void assertSegments(List<String> segments, String... expected) {
        assertEquals(Arrays.asList(expected), segments);
        assertEquals(expected.length, segments.size());
    }
}

class TemplateEngine {

    private String templateString;

    public String evaluate(Map<String, String> placeHolders) {
        for (Map.Entry<String, String> entry : placeHolders.entrySet()) {
            this.templateString = this.templateString.replace(entry.getKey(), entry.getValue());
        }
        return this.templateString;
    }

    public List<String> compile(String templateString) {
        this.templateString = templateString;
        List<String> segments = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\\$\\{([^}]*)\\}");
        Matcher matcher = pattern.matcher(templateString);

        int index = 0;
        while (matcher.find()) {
            segments.add(templateString.substring(index, matcher.start()));

            segments.add(matcher.group(1));
            index = matcher.end();
        }

        if (index < templateString.length()) {
            segments.add(templateString.substring(index));
        }
        return segments;
    }


}

