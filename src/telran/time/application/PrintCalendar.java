package telran.time.application;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class PrintCalendar {
	private static final int TITLE_OFFSET = 10;
	private static final int COLUMN_WIDTH = 4;
	private static DayOfWeek[] weekDays = DayOfWeek.values();

	record MonthYear(int month, int year) {

	}

	public static void main(String[] args) {
		try {
			MonthYear monthYear = getMonthYear(args);
			DayOfWeek weekDayFirst = getWeekDayFirst(args);
			printCalendar(monthYear, weekDayFirst);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static DayOfWeek getWeekDayFirst(String[] args) throws Exception {
		DayOfWeek wdFirst = args.length < 3 ? DayOfWeek.MONDAY : getWeekDayFirst(args[2]);
		return wdFirst;
	}

	private static DayOfWeek getWeekDayFirst(String weekDayStr) throws Exception {
		// V.R. It is possible to get the same result without switch by the following way:
		// in my HW was SWITCH
		try {
			return DayOfWeek.valueOf(weekDayStr.toUpperCase());			
		} catch (IllegalArgumentException e) {
			throw new Exception(weekDayStr.toUpperCase() + " is wrong day of week");
		}

	}

	private static MonthYear getMonthYear(String[] args) throws Exception {
		int monthNumber = getMonth(args);
		int year = getYear(args);
		return new MonthYear(monthNumber, year);
	}

	private static int getMonth(String[] args) throws Exception {
		int month = args.length == 0 ? getCurrentMounth() : getMonthNumber(args[0]);
		return month;
	}

	private static int getMonthNumber(String monthStr) throws Exception {
		try {
			int result = Integer.parseInt(monthStr);
			if (result < 1) {
				throw new Exception("Month cannot be less than 1");
			}
			if (result > 12) {
				throw new Exception("Month cannot be greater than 12");
			}
			return result;
		} catch (NumberFormatException e) {
			throw new Exception("Month must be a number");
		}
	}

	private static int getCurrentMounth() {

		return LocalDate.now().get(ChronoField.MONTH_OF_YEAR);
	}

	private static int getYear(String[] args) throws Exception {
		int year = args.length < 2 ? getCurrentYear() : getYear(args[1]);
		return year;
	}

	private static int getYear(String yearStr) throws Exception {
		try {
			int result = Integer.parseInt(yearStr);
			return result;
		} catch (NumberFormatException e) {
			throw new Exception("Year must be a number");
		}
	}

	private static int getCurrentYear() {

		return LocalDate.now().getYear();
	}

	private static void printCalendar(MonthYear monthYear, DayOfWeek weekDayFirst) {
		printTitle(monthYear);
		printWeekDays(weekDayFirst);
		printDays(monthYear, weekDayFirst);

	}

	private static void printDays(MonthYear monthYear, DayOfWeek weekDayFirst) {
		int nDays = getDaysInMonth(monthYear);
		int currentWeekDay = getFirstDayOfMonth(monthYear);
		int offsetWeekDay = (weekDays.length - weekDayFirst.ordinal()) % weekDays.length;
		int firstOffset = getFirstOffset(currentWeekDay, offsetWeekDay);
		System.out.printf("%s", " ".repeat(firstOffset));
		for (int day = 1; day <= nDays; day++) {
			System.out.printf("%" + COLUMN_WIDTH + "d", day);
			if ((currentWeekDay + offsetWeekDay) % weekDays.length == 0) {
				currentWeekDay = 0 - offsetWeekDay;
				System.out.println();
			}
			currentWeekDay++;
		}
	}

	private static int getFirstOffset(int currentWeekDay, int offsetWeekDay) {
		return COLUMN_WIDTH * ((currentWeekDay - 1 + offsetWeekDay) % weekDays.length);
	}

	private static int getFirstDayOfMonth(MonthYear monthYear) {
		LocalDate ld = LocalDate.of(monthYear.year(), monthYear.month(), 1);
		return ld.get(ChronoField.DAY_OF_WEEK);
	}

	private static int getDaysInMonth(MonthYear monthYear) {
		YearMonth ym = YearMonth.of(monthYear.year(), monthYear.month());
		return ym.lengthOfMonth();
	}

	private static void printWeekDays(DayOfWeek weekDayFirst) {
		System.out.printf("%s", " ".repeat(COLUMN_WIDTH / 3));
		int startIndex = weekDayFirst.ordinal();
		for (int i = 0; i < weekDays.length; i++) {
			int index = (startIndex + i) % weekDays.length;
			DayOfWeek weekday = weekDays[index];
			System.out.printf("%" + COLUMN_WIDTH + "s",
					weekday.getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("en")));
		}
		System.out.println();

	}

	private static void printTitle(MonthYear monthYear) {
		String monthName = Month.of(monthYear.month()).getDisplayName(TextStyle.FULL, Locale.getDefault());
		System.out.printf("%s%s %d\n", " ".repeat(TITLE_OFFSET), monthName, monthYear.year());

	}

}
