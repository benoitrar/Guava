package bbejeck.guava.chapter2.joinersplitter;

import java.util.Map;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;

public class JoinerSplitterExample {
	@Test
	public void testMapJoiner() {
		// GIVEN
		// Using LinkedHashMap so that the original order is preserved
		String expectedString = "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
		Map<String, String> testMap = Maps.newLinkedHashMap();
		testMap.put("Washington D.C", "Redskins");
		testMap.put("New York City", "Giants");
		testMap.put("Philadelphia", "Eagles");
		testMap.put("Dallas", "Cowboys");

		// WHEN
		String returnedString = Joiner.on("#").withKeyValueSeparator("=").join(testMap);

		// THEN
		assertThat(returnedString, is(expectedString));
	}

	@Test
	public void testSplitter() {
		// GIVEN
		String startString = "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
		Map<String, String> testMap = Maps.newLinkedHashMap();
		testMap.put("Washington D.C", "Redskins");
		testMap.put("New York City", "Giants");
		testMap.put("Philadelphia", "Eagles");
		testMap.put("Dallas", "Cowboys");
		Splitter.MapSplitter mapSplitter = Splitter.on("#").withKeyValueSeparator("=");

		// WHEN
		Map<String, String> splitMap = mapSplitter.split(startString);

		// THEN
		assertThat(testMap, is(splitMap));
	}

	@Test
	public void testRetainFrom() {
		// GIVEN
		String lettersAndNumbers = "foo989yxbar234";
		String expected = "989234";

		// WHEN
		String retained = CharMatcher.JAVA_DIGIT.retainFrom(lettersAndNumbers);

		// THEN
		assertThat(expected, is(retained));
	}

	@Test
	public void testCharMatcherOR() {
		// GIVEN
		String lettersAndNumbersAndWhitespaces = "foo989 yxbar234";
		String expected = "989 234";
		CharMatcher matcher = CharMatcher.JAVA_DIGIT.or(CharMatcher.WHITESPACE);

		// WHEN
		String retained = matcher.retainFrom(lettersAndNumbersAndWhitespaces);

		// THEN
		assertThat(expected, is(retained));
	}
}
