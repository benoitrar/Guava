package bbejeck.guava.chapter4.listssetsmaps;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import bbejeck.guava.common.model.Book;
import bbejeck.guava.common.model.Book.Builder;

public class MapsExample {

	private static final String MY_VERY_UNIQUE_ISBN_NUMBER = "my very unique isbn number";
	private Book book1;
	private Book book2;

	@Before
	public void setUp() {
		Builder builder = new Book.Builder();
		book1 = builder.build();
		book2 = builder.isbn(MY_VERY_UNIQUE_ISBN_NUMBER).build();
	}

	@Test
	public void testUniqueIndex() {
		List<Book> books = Lists.newArrayList(book1, book2);

		// consider either Java 8 OR dependency injection
		Map<String, Book> bookMap = Maps.uniqueIndex(books.iterator(), new Function<Book, String>() {
			@Override
			public String apply(Book input) {
				return input.getIsbn();
			}
		});

		assertThat(bookMap.get(MY_VERY_UNIQUE_ISBN_NUMBER)).isNotNull();
	}

	// asMap()
	// toMap() -> immutable

	// transformEntries()
	// transformValues()

	@Test
	public void testArrayListMultiMap(){
		ArrayListMultimap<String, String> multiMap = ArrayListMultimap.create();
		multiMap.put("Foo", "1");
		multiMap.put("Foo", "2");
		multiMap.put("Foo", "3");

		assertThat(multiMap.get("Foo")).containsOnly("1", "2", "3");
	}

	@Test
	public void testArrayListMultimapSameKeyValue() {
		ArrayListMultimap<String, String> multiMap = ArrayListMultimap.create();
		multiMap.put("Bar", "1");
		multiMap.put("Bar", "2");
		multiMap.put("Bar", "3");
		multiMap.put("Bar", "3");
		multiMap.put("Bar", "3");
		List<String> expected = Lists.newArrayList("1", "2", "3", "3", "3");

		assertEquals(multiMap.get("Bar"), expected);
	}

	@Test
	public void testBehaviorOfArrayListMultiMap() {
		ArrayListMultimap<String, String> multiMap = ArrayListMultimap.create();
		multiMap.put("Foo", "1");
		multiMap.put("Foo", "2");
		multiMap.put("Foo", "3");
		multiMap.put("Bar", "1");
		multiMap.put("Bar", "2");
		multiMap.put("Bar", "3");

		// don't forget multimap is actually not a real map
		assertEquals(multiMap.size(), 6);
		assertEquals(multiMap.asMap().size(), 2);
	}

	@Test
	public void testHashMultiMap() {
		HashMultimap<String, String> multiMap = HashMultimap.create();
		multiMap.put("Bar", "1");
		multiMap.put("Bar", "2");
		multiMap.put("Bar", "3");
		multiMap.put("Bar", "3");
		multiMap.put("Bar", "3");

		assertEquals(multiMap.size(), 3);
	}

	// ImmutableListMultiMap
	// ImmutableMultiMap
	// ImmutableSetMultiMap
	// LinkedHashMultiMap
	// TreeMultiMap

	@Test(expected = IllegalArgumentException.class)
	public void testBiMap() {
		BiMap<String,String> biMap = HashBiMap.create();
		biMap.put("1","Tom");
		biMap.put("2","Tom");
	}

	@Test
	public void testBiMapForcePut() {
		BiMap<String, String> biMap = HashBiMap.create();
		biMap.put("1", "Tom");
		biMap.forcePut("2", "Tom");

		assertFalse(biMap.containsKey("1"));
		assertTrue(biMap.containsKey("2"));
	}
}
