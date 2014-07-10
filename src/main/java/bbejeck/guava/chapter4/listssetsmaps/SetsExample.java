package bbejeck.guava.chapter4.listssetsmaps;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

public class SetsExample {

	private Set<String> set1;
	private Set<String> set2;

	@Before
	public void setUp() {
		set1 = Sets.newHashSet("1", "2", "3");
		set2 = Sets.newHashSet("2", "3", "4");
	}

	@Test
	public void testDifference() {
		// note that we get an immutable view
		SetView<String> difference = Sets.difference(set1, set2);

		assertThat(difference).containsOnly("1");

		// by the way
		assertThat(Sets.difference(set2, set1)).containsOnly("4");
	}

	@Test
	public void testSymmetricDifference() {
		assertThat(Sets.symmetricDifference(set1, set2)).containsOnly("1", "4");
	}

	@Test
	public void testIntersection() {
		assertThat(Sets.intersection(set1, set2)).containsOnly("2", "3");
	}

	@Test
	public void testUnion() {
		assertThat(Sets.union(set1, set2)).containsOnly("1", "2", "3", "4");
	}

}
