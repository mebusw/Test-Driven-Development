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

    @Test
    public void testSegment() {
        List<Segment> segments = engine.parse("Hello, ${name} ${family}");
        assertEquals(new TextSegment("Hello ,"), new TextSegment("Hello ,"));
        assertEquals(new VariableSegment("$name"), new VariableSegment("$name"));
        assertSegments(segments, new TextSegment("Hello, "), new VariableSegment("${name}"), new TextSegment(" "), new VariableSegment("${family}"));

    }

    @Test
    public void testSegmentEval() {
        String text = "Hello, ";
        assertEquals(text, new TextSegment(text).eval(null));

        Map<String, String> vars = new HashMap<String, String>();
        vars.put("${name}", "Jacky");
        assertEquals("Jacky", new VariableSegment("${name}").eval(vars));
    }

    @Test(expected = MissingFormatArgumentException.class)
    public void testSegmentEvalWithMissingVar() {
        String text = "Hello, ";
        assertEquals(text, new TextSegment(text).eval(null));

        Map<String, String> vars = new HashMap<String, String>();
        new VariableSegment("${name}").eval(vars);

    }

    ///////////////////
    private void assertSegments(List segments, Object... expected) {
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
        this.templateString = templateString;
        segments = new ArrayList<String>();
        int index = collectSegments(templateString, segments);
        addTailText(templateString, segments, index);
        return segments;
    }


    public String evaluate2(Map<String, String> placeHolders) {
        StringBuffer result = new StringBuffer();
        List<Segment> segments = parse(templateString);
        for (Segment seg : segments) {
            result.append(seg.eval(placeHolders));
        }
        return result.toString();
    }

    private void append(Map<String, String> placeHolders, StringBuffer result, String seg) {
        if (isVariable(seg)) {
            result.append(placeHolders.get(seg));
        } else {
            result.append(seg);
        }
    }

    private static boolean isVariable(String seg) {
        return seg.startsWith("${");
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

    public List<Segment> parse(String templateString) {
        List<String> segments = compile(templateString);
        List<Segment> result = new ArrayList<Segment>();
        for (String seg : segments) {
            if (isVariable(seg)) {
                result.add(new VariableSegment(seg));
            } else {
                result.add(new TextSegment(seg));
            }
        }
        return result;
    }
}

interface Segment {
    public String eval(Map<String, String> variables);
}

class TextSegment implements Segment {

    private String text;

    public TextSegment(String text) {
        this.text = text;
    }

    public boolean equals(Object other) {
        return text.equals(((TextSegment) other).text);
    }

    @Override
    public String toString() {
        return this.text;
    }

    @Override
    public String eval(Map<String, String> variables) {
        return this.text;
    }
}

class VariableSegment implements Segment {

    private String name;

    public VariableSegment(String name) {
        this.name = name;
    }


    public boolean equals(Object other) {
        return name.equals(((VariableSegment) other).name);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String eval(Map<String, String> variables) {
        if (!variables.containsKey(this.name)) {
            throw new MissingFormatArgumentException("");
        }
        return variables.get(this.name);


    }
}