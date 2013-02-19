package training.templateEngine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TemplateEngineTest {
    private Template template;
    private TemplatePlaceholderValues replacementValues;

    @Before
    public void setUp() throws Exception {
        template = new Template();
        replacementValues = new TemplatePlaceholderValues();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void templatesWithoutPlaceHoldersDoNotChange() throws Exception {
        template.set("Nothing here");
        assertEquals("Nothing here", template.replaceValues(replacementValues));
    }

    @Test
    public void templateWithOnePlaceholderReplacesItToValue() throws Exception {
        template.set("Hi, $name");
        replacementValues.placeholder("$name").hasValue("John");
        assertEquals("Hi, John", template.replaceValues(replacementValues));
    }

    @Test
    public void templateWhereOnePlaceholderIsReplacedAndOneIsNot()
            throws Exception {
        template.set("Hi, $name $last_name");
        replacementValues.placeholder("$last_name").hasValue("Doe");
        assertEquals("Hi, $name Doe", template.replaceValues(replacementValues));
    }
}
