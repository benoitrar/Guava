package bbejeck.guava.chapter3.function;

import java.util.Map;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableMap;

public class ForMapExample {

	private static final Map<String, Integer> amounts = ImmutableMap.<String, Integer> builder()
			.put("alma", 250)
			.put("barack", 400)
			.build();

	private static final Function<String, Integer> lookup = Functions.forMap(amounts, 0);

	public static void main(String args[]) {
		System.out.println(lookup.apply("alma"));
		System.out.println(lookup.apply("amma"));
		System.out.println(lookup.apply(null));
		Predicates.alwaysTrue();
	}

}
