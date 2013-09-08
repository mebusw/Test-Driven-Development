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
        engine.setTemplateString("Hello");

        assertEquals("Hello", engine.evaluate(placeHolders));
    }

    @Test
    public void testTemplateWithOneVariable() {
        engine.setTemplateString("Hello, ${name}");
        placeHolders.put("${name}", "Jacky");

        assertEquals("Hello, Jacky", engine.evaluate(placeHolders));
    }

    @Test
    public void testTemplateWithTwoVariables() {
        engine.setTemplateString("Hello, ${name} ${family}");
        placeHolders.put("${name}", "Jacky");
        placeHolders.put("${family}", "Shen");

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
        List<String> segments = engine.compile("Hello, ${name} ${family}");
        assertSegments(segments, "Hello, ", "${name}", " ", "${family}");
        placeHolders.put("${name}", "Jacky");
        placeHolders.put("${family}", "Shen");

        assertEquals("Hello, Jacky Shen", engine.evaluate2(placeHolders));
    }

    ///////////////////
    private void assertSegments(List<String> segments, String... expected) {
        assertEquals(Arrays.asList(expected), segments);
        assertEquals(expected.length, segments.size());
    }
}

class TemplateEngine {

    void setTemplateString(String templateString) {
        this.templateString = templateString;
    }

    private String templateString;
    private List<String> segments;

    public String evaluate(Map<String, String> placeHolders) {
        for (Map.Entry<String, String> entry : placeHolders.entrySet()) {
            this.templateString = this.templateString.replace(entry.getKey(), entry.getValue());
        }
        return this.templateString;
    }

    public List<String> compile(String templateString) {
        segments = new ArrayList<String>();
        int index = collectSegments(templateString, segments);
        addTailText(templateString, segments, index);
        return segments;
    }


    public String evaluate2(Map<String, String> placeHolders) {
        StringBuffer sb = new StringBuffer();
        for (String seg : segments) {
            if (seg.startsWith("${")) {
                sb.append(placeHolders.get(seg));
            } else {
                sb.append(seg);
            }
        }
        return sb.toString();
    }

    private int collectSegments(String templateString, List<String> segments) {
        Pattern pattern = Pattern.compile("\\$\\{[^}]*\\}");
        Matcher matcher = pattern.matcher(templateString);

        int index = 0;
        while (matcher.find()) {
            appendPreceedingText(templateString, segments, matcher, index);

            appendVariable(segments, matcher);
            index = matcher.end();
        }
        return index;
    }

    private void addTailText(String templateString, List<String> segments, int index) {
        if (index < templateString.length()) {
            segments.add(templateString.substring(index));
        }
    }

    private void appendVariable(List<String> segments, Matcher matcher) {
        segments.add(matcher.group(0));
    }

    private void appendPreceedingText(String templateString, List<String> segments, Matcher matcher, int index) {
        segments.add(templateString.substring(index, matcher.start()));
    }


}

