package bbejeck.guava.chapter4.fluentiterable;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import bbejeck.guava.common.model.Person;

public class FluentIterableExample {

	private Person person1;
	private Person person2;
	private Person person3;
	private Person person4;
	private List<Person> personList;

	@Before
	public void setUp() {
		person1 = new Person("Wilma", "Flintstone", 30, "F");
		person2 = new Person("Fred", "Flintstone", 32, "M");
		person3 = new Person("Betty", "Rubble", 31, "F");
		person4 = new Person("Barney", "Rubble", 33, "M");
		personList = Lists.newArrayList(person1, person2, person3, person4);
	}

	@Test
	public void testFilter() throws Exception {
		Iterable<Person> personsFilteredByAge = FluentIterable.from(personList).filter(new Predicate<Person>() {
			@Override
			public boolean apply(Person input) {
				return input.getAge() > 31;
			}
		});
		assertThat(personsFilteredByAge).containsOnly(person2, person4);

		// OR this way with Iterables
		assertThat(Iterables.size(personsFilteredByAge), is(2));
		assertThat(Iterables.contains(personsFilteredByAge, person2), is(true));
		assertThat(Iterables.contains(personsFilteredByAge, person4), is(true));
	}

	@Test
	public void testTransform() throws Exception {
		List<String> transformedPersonList = FluentIterable.from(personList).transform(new Function<Person, String>() {
			@Override
			public String apply(Person input) {
				return Joiner.on(' ').join(input.getFirstName(), input.getLastName(), input.getAge());
			}
		}).toList();
		assertThat(transformedPersonList.get(1), is("Fred Flintstone 32"));
	}

	@Test
	public void testToMap() throws Exception {
		ImmutableMap<Person, String> map = FluentIterable.from(personList).toMap(new Function<Person, String>() {
			@Override
			public String apply(Person input) {
				return Joiner.on(' ').join(input.getFirstName(), input.getLastName(), input.getAge());
			}
		});
		assertThat(map.get(person2), is("Fred Flintstone 32"));
	}
}
