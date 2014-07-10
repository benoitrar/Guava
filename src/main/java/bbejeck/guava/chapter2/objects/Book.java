package bbejeck.guava.chapter2.objects;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import bbejeck.guava.common.model.Person;

public class Book implements Comparable<Book> {

	private Person author;
	private String title;
	private String publisher;
	private String isbn;
	private double price;

	@Override
	public int hashCode() {
		return Objects.hashCode(title, author, publisher, isbn);
	}

	public int compareToOldWay(Book o) {
		int result = this.title.compareTo(o.getTitle());
		if (result != 0) {
			return result;
		}

		result = this.author.compareTo(o.getAuthor());
		if (result != 0) {
			return result;
		}

		result = this.publisher.compareTo(o.getPublisher());
		if (result != 0) {
			return result;
		}

		return this.isbn.compareTo(o.getIsbn());
	}

	@Override
	public int compareTo(Book o) {
		return ComparisonChain.start()
				.compare(this.title, o.title)
				.compare(this.author, o.author)
				.compare(this.publisher, o.publisher)
				.compare(this.isbn, o.isbn)
				.compare(this.price, o.price)
				.result();
	}

	public Person getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public double getPrice() {
		return price;
	}
}
