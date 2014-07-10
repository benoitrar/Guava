package bbejeck.guava.chapter2.strings;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.google.common.base.CharMatcher;

public class StringsExample
{
    @Test
    public void testRemoveWhiteSpace(){
        // GIVEN
		String tabsAndSpaces = "String with   spaces \n     and                               tabs";
        String expected = "String with spaces and tabs";

        // WHEN
        String scrubbed = CharMatcher.WHITESPACE.collapseFrom(tabsAndSpaces, ' ');

        // THEN
        assertThat(scrubbed, is(expected));
    }

    @Test
    public void testTrimRemoveWhiteSpace(){
        // GIVEN
        String tabsAndSpaces = "      String with   spaces \n     and                               tabs                ";
        String expected = "String with spaces and tabs";

        // WHEN
        String scrubbed = CharMatcher.WHITESPACE.trimAndCollapseFrom(tabsAndSpaces, ' ');
        
        // THEN
        assertThat(scrubbed,is(expected));
    }
}
