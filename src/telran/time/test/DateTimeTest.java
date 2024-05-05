package telran.time.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.jupiter.api.Test;

import telran.time.BarMizvaAdjuster;
import telran.time.Friday13Range;
import telran.time.NextFriday13;

class DateTimeTest {

	@Test
	void barMizvaAdjusterTest() {
		LocalDate birthDate = LocalDate.parse("1799-06-06");
		LocalDate expected = LocalDate.of(1812, 6, 6);
		assertEquals(expected, birthDate.with(new BarMizvaAdjuster()));
	}

	@Test
	void nextFriday13() {
		LocalDate date = LocalDate.of(2024, 5, 5);
		LocalDate expected = LocalDate.of(2024, 9, 13);
		assertEquals(expected, date.with(new NextFriday13()));
		assertEquals(LocalDate.of(2024, 12, 13), expected.with(new NextFriday13()));

	}

	@Test
	void friday13RangeTest() {
		Temporal from = LocalDate.of(2023, 1, 1);
		Temporal to = LocalDate.of(2023, 12, 31);
		Friday13Range range = new Friday13Range(from, to);

		Iterator<Temporal> iterator = range.iterator();

		assertTrue(iterator.hasNext());
		assertEquals(LocalDate.of(2023, 1, 13), iterator.next());

		assertTrue(iterator.hasNext());
		assertEquals(LocalDate.of(2023, 10, 13), iterator.next());
		assertThrowsExactly(NoSuchElementException.class, () -> iterator.next());

	}

	@Test
	void displayCurrentDateTimeTest() {
		// The following test implies printing out
		// all current date/time in Time Zones containing string "Canada"
		displayCurrentDateTime("Canada");

	}

	private void displayCurrentDateTime(String zonePart) {
		// prints out current date/time in all zones containing zonePart
		// ZonedDateTime class
		// format <year>-<month number>-<day> <hh:mm> <zone name>
		Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
		for (String zoneId : allZoneIds) {
			if (zoneId.contains(zonePart)) {
				ZoneId zone = ZoneId.of(zoneId);
				ZonedDateTime now = ZonedDateTime.now(zone);
				String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm VV"));
				System.out.println(formattedDateTime);
			}
		}
	}

}
