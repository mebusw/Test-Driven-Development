package training.templateEngine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TemplateEngineTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void templatesWithoutPlaceHoldersDoNotChange() throws Exception {
        Template template = new Template();
        TemplatePlaceholderValues replacementValues = new TemplatePlaceholderValues();
        template.set("Nothing here");
        assertEquals("Nothing here", template.replaceValues(replacementValues));
    }

}
