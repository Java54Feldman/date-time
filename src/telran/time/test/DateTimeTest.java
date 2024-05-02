package telran.time.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import telran.time.BarMizvaAdjuster;

class DateTimeTest {

	@Test
	void barMizvaAdjusterTest() {
		LocalDate birthDate = LocalDate.parse("1799-06-06");
		LocalDate expected = LocalDate.of(1812,  6, 6);
		assertEquals(expected, birthDate.with(new BarMizvaAdjuster()));
	}
	@Test
	void nextFriday13() {
		//TODO
	}
	@Test
	void friday13RangeTest() {
		//TODO
	}
	@Test
	void zonedDateTimeTest() {
		var availableTimeZones = ZoneId.getAvailableZoneIds();
		for (String zone : availableTimeZones) {
			System.out.println(zone);			
		}
	}

}
