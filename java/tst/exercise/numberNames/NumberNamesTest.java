package exercise.numberNames;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Spell out a number. For example
 * 99 --> ninety nine
 * 300 --> three hundred
 * 310 --> three hundred and ten
 * 1501 --> one thousand, five hundred and one
 * 12609 --> twelve thousand, six hundred and nine
 * 512607 --> five hundred and twelve thousand,
 * six hundred and seven
 * 43112603 --> forty three million, one hundred and
 * twelve thousand,
 * six hundred and three
 */
public class NumberNamesTest {
    @Test
    public void single() {
        assertThat(NumberNames.translate(0), is("zero"));
        assertThat(NumberNames.translate(1), is("one"));
        assertThat(NumberNames.translate(2), is("two"));
        assertThat(NumberNames.translate(3), is("three"));
        assertThat(NumberNames.translate(4), is("four"));
        assertThat(NumberNames.translate(5), is("five"));
        assertThat(NumberNames.translate(6), is("six"));
        assertThat(NumberNames.translate(7), is("seven"));
        assertThat(NumberNames.translate(8), is("eight"));
        assertThat(NumberNames.translate(9), is("nine"));
        assertThat(NumberNames.translate(10), is("ten"));
    }

    @Test
    public void teen() {
        assertThat(NumberNames.translate(11), is("eleven"));
        assertThat(NumberNames.translate(12), is("twelve"));
        assertThat(NumberNames.translate(13), is("thirteen"));
        assertThat(NumberNames.translate(14), is("fourteen"));
        assertThat(NumberNames.translate(15), is("fifteen"));
        assertThat(NumberNames.translate(16), is("sixteen"));
        assertThat(NumberNames.translate(17), is("seventeen"));
        assertThat(NumberNames.translate(18), is("eighteen"));
        assertThat(NumberNames.translate(19), is("nineteen"));
    }

    @Test
    public void ty() {
        assertThat(NumberNames.translate(20), is("twenty"));
        assertThat(NumberNames.translate(30), is("thirty"));
        assertThat(NumberNames.translate(40), is("forty"));
        assertThat(NumberNames.translate(50), is("fifty"));
        assertThat(NumberNames.translate(60), is("sixty"));
        assertThat(NumberNames.translate(70), is("seventy"));
        assertThat(NumberNames.translate(80), is("eighty"));
        assertThat(NumberNames.translate(90), is("ninety"));
    }

    @Test
    public void ty_plus_single() {
        assertThat(NumberNames.translate(21), is("twenty one"));
        assertThat(NumberNames.translate(22), is("twenty two"));
        assertThat(NumberNames.translate(45), is("forty five"));
    }

    @Test
    public void hundred() {
        assertThat(NumberNames.translate(100), is("one hundred"));
        assertThat(NumberNames.translate(200), is("two hundred"));
    }

    @Test
    public void hundred_plus_ty() {
        assertThat(NumberNames.translate(120), is("one hundred and twenty"));
        assertThat(NumberNames.translate(220), is("two hundred and twenty"));
    }

    @Test
    public void hundred_plus_ty_plus_single() {
        assertThat(NumberNames.translate(356), is("three hundred and fifty six"));
        assertThat(NumberNames.translate(306), is("three hundred and six"));
        assertThat(NumberNames.translate(310), is("three hundred and ten"));
    }

    @Test
    public void thousand() {
        assertThat(NumberNames.translate(1000), is("one thousand"));
        assertThat(NumberNames.translate(2000), is("two thousand"));
    }

    @Test
    public void thousand_plus_x() {
        assertThat(NumberNames.translate(1501), is("one thousand, five hundred and one"));
    }

    @Test
    public void ten_thousands() {
        assertThat(NumberNames.translate(12609), is("twelve thousand, six hundred and nine"));
        assertThat(NumberNames.translate(512607), is("five hundred and twelve thousand, six hundred and seven"));

    }

    @Test
    public void million() {
        assertThat(NumberNames.translate(43112603), is("forty three million, one hundred and twelve thousand, six hundred and three"));
        assertThat(NumberNames.translate(43000000), is("forty three million"));
    }
}