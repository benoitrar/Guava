package bbejeck.guava.common.model;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: Bill Bejeck
 * Date: 4/23/13
 * Time: 9:34 PM
 */
public class Person implements Comparable<Person> {

    private final String firstName;
    private final String lastName;
    private final int age;
    private final String sex;

    public Person(String firstName, String lastName, int age, String sex) {
        this.firstName = checkNotNull(firstName, "First name can't be null");
        this.lastName = checkNotNull(lastName, "Last name can't be null");
        checkArgument(age > 0, "Age has to be greater than 0");
        this.age = age;
        this.sex = checkNotNull(sex, "Sex can't be null");
        checkArgument((sex.equals("M") || sex.equals("F")), "Sex has to be either M or F");
    }

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("first name", firstName)
				.add("last name", lastName)
				.add("age", age)
				.add("sex", sex)
				.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        if (sex != null ? !sex.equals(person.sex) : person.sex != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
		return Objects.hashCode(firstName, lastName, age, sex);
    }

    @Override
    public int compareTo(Person o) {
        return ComparisonChain.start().
                compare(this.firstName, o.getFirstName()).
                compare(this.lastName, o.getLastName()).
                compare(this.age, o.getAge()).
                compare(this.sex, o.getSex()).result();
    }

    public static class Builder {
        private String firstName = "John";
        private String lastName = "Doe";
        private int age = 25;
        private String sex = "M";

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder sex(String sex) {
            this.sex = sex;
            return this;
        }

        public Person build() {
            return new Person(this.firstName, this.lastName, this.age, this.sex);
        }
    }

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public String getSex() {
		return sex;
	}
}
