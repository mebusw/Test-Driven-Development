package exercise.stringCaculator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class StringCalculatorTest {
    @Test
    public void sumEmptyStringToZero() {
        assertThat(StringCalculator.sum(""), is(0));
    }

    @Test
    public void sumSingleNumberToItself() {
        assertThat(StringCalculator.sum("12"), is(12));
    }

    @Test
    public void commaAsSeparator() {
        assertThat(StringCalculator.sum("1,2"), is(3));
        assertThat(StringCalculator.sum("1,2,3"), is(6));
    }
    @Test
    public void newlineAsSeparator() {
        assertThat(StringCalculator.sum("1\n2"), is(3));
        assertThat(StringCalculator.sum("1\n2,3"), is(6));
    }
    @Test
    public void specifiedSeparator() {
        assertThat(StringCalculator.sum("//;\n1;2"), is(3));
        assertThat(StringCalculator.sum("//.\n2.3.1"), is(6));
    }

    @Rule
    public ExpectedException expectedException= ExpectedException.none();

    @Test
    public void throwOnNegtives() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Contains Negtives: -3, -5, -13");

        assertThat(StringCalculator.sum("1,-3,5,-5,-13"), is(-1));
    }
    @Test
    public void above1000WillBeMod() {
        assertThat(StringCalculator.sum("1005,1234"), is(239));
    }

}