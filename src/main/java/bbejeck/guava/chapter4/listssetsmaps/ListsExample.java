package bbejeck.guava.chapter4.listssetsmaps;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.google.common.collect.Lists;

import bbejeck.guava.common.model.Person;

public class ListsExample {

	private Person person1;
	private Person person2;
	private Person person3;
	private Person person4;
	private Person person5;
	private List<Person> personList;

	@Before
	public void setUp() {
		person1 = new Person("Wilma", "Flintstone", 30, "F");
		person2 = new Person("Fred", "Flintstone", 32, "M");
		person3 = new Person("Betty", "Rubble", 31, "F");
		person4 = new Person("Barney", "Rubble", 33, "M");
		person5 = new Person("Benedek", "Kiss", 23, "M");
		personList = Lists.newArrayList(person1, person2, person3, person4, person5);
	}

	@Test
	public void testPartition() {
		List<List<Person>> subList = Lists.partition(personList, 2);

		assertThat(subList.size(), is(3));
		assertThat(subList.get(0)).containsOnly(person1, person2);
		assertThat(subList.get(1)).containsOnly(person3, person4);
		assertThat(subList.get(2)).containsOnly(person5);
	}
}
