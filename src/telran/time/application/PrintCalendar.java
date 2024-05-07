package telran.time.application;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class PrintCalendar {
	private static final int TITLE_OFFSET = 8;
	private static final int COLUMN_WIDTH = 4;
	private static DayOfWeek[] weekDays = DayOfWeek.values(); 

	record MonthYear(int month, int year) {

	}

	public static void main(String[] args) {
		try {
			MonthYear monthYear = getMonthYear(args);
			printCalendar(monthYear);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static MonthYear getMonthYear(String[] args) throws Exception {
		int monthNumber = getMonth(args);
		int year = getYear(args);
		return new MonthYear(monthNumber, year);
	}

	private static int getMonth(String[] args) throws Exception{
		int month = args.length == 0 ? getCurrentMounth() : getMonthNumber(args[0]);
		return month;
	}
	private static int getMonthNumber(String monthStr) throws Exception{
		try {
			int result = Integer.parseInt(monthStr);
			if(result < 1) {
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

	private static void printCalendar(MonthYear monthYear) {
		printTitle(monthYear);
		printWeekDays();
		printDays(monthYear);

	}

	private static void printDays(MonthYear monthYear) {
		int nDays = getDaysInMonth(monthYear);
		int currentWeekDay = getFirstDayOfMonth(monthYear);
		int firstOffset = getFirstOffset(currentWeekDay);
		System.out.printf("%s", " ".repeat(firstOffset));
		for(int day = 1; day <= nDays; day++) {
			System.out.printf("%" + COLUMN_WIDTH + "d", day);
			if(currentWeekDay == weekDays.length) {
				currentWeekDay = 0;
				System.out.println();
			}
			currentWeekDay++;
		}
	}

	private static int getFirstOffset(int currentWeekDay) {
		
		return COLUMN_WIDTH * (currentWeekDay - 1);
	}

	private static int getFirstDayOfMonth(MonthYear monthYear) {
		LocalDate ld = LocalDate.of(monthYear.year(), monthYear.month(), 1);
		return ld.get(ChronoField.DAY_OF_WEEK);
	}

	private static int getDaysInMonth(MonthYear monthYear) {
		YearMonth ym = YearMonth.of(monthYear.year(), monthYear.month());
		return ym.lengthOfMonth();
	}

	private static void printWeekDays() {
		System.out.printf("%s", " ".repeat(COLUMN_WIDTH / 3));
		for(DayOfWeek weekday: weekDays) {
			System.out.printf("%" + COLUMN_WIDTH + "s", weekday.getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("en")));
		}
		System.out.println();
		
	}

	private static void printTitle(MonthYear monthYear) {
		String monthName = Month.of(monthYear.month()).getDisplayName(TextStyle.FULL, Locale.getDefault());
		System.out.printf("%s%s %d\n",	" ".repeat(TITLE_OFFSET), monthName, monthYear.year());
				
	}

}
