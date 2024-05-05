package telran.time;

import java.time.*;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextFriday13 implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		LocalDate date = LocalDate.from(temporal).plusDays(1);
		int month = date.getMonthValue();
		int year = date.getYear();
		LocalDate nextFriday13 = LocalDate.of(year, month, 13);

		while (nextFriday13.isBefore(date) || nextFriday13.getDayOfWeek() != DayOfWeek.FRIDAY) {
			/*
			 * V.R. It is better to use LocalDate functionality like plusMonths and
			 * withDayOfMonth.
			 */
			month++;
			if (month > 12) {
				month = 1;
				year++;
			}
			nextFriday13 = LocalDate.of(year, month, 13);
		}

		return nextFriday13;
	}

}