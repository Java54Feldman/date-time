package telran.time;

import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Friday13Range implements Iterable<Temporal> {
	Temporal from;
	Temporal to;

	public Friday13Range(Temporal from, Temporal to) {
		if(ChronoUnit.DAYS.between(from, to) < 0) {
			throw new IllegalArgumentException();
		}
		this.from = from;
		this.to = to;
	}

	@Override
	public Iterator<Temporal> iterator() {

		return new FridayIterator();
	}

	private class FridayIterator implements Iterator<Temporal> {
		Temporal current = from;
		NextFriday13 adjuster = new NextFriday13();

		@Override
		public boolean hasNext() {
			return ChronoUnit.DAYS.between(current.with(adjuster), to) >= 0;
		}

		@Override
		public Temporal next() {
			while (!hasNext()) {
				throw new NoSuchElementException();
			}
			return current = current.with(adjuster);
		}

	}

}
