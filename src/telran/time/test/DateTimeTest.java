package telran.time.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import org.junit.jupiter.api.Test;

class DateTimeTest {

	@Test
	void introductionTest() {
		//различные способы представления (разные toString/fromString) одной и той же даты
//		LocalDate birthDateAS = LocalDate.of(1799,  6, 6);
		LocalDate birthDateAS = LocalDate.parse("1799-06-06"); //same
		
		System.out.printf("birthdate of ASP in standart format is %s\n", birthDateAS);
		System.out.printf("birthdate of ASP in dd/mm/yyyy is %s\n", 
				birthDateAS.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		System.out.printf("birthdate of ASP in ... is %s\n", 
				birthDateAS.format(DateTimeFormatter.ofPattern("MMMM,d.yyyy EEEE")));
		System.out.printf("birthdate of ASP in ... is %s\n", 
				birthDateAS.format(DateTimeFormatter.ofPattern("MMMM,d yyy",Locale.JAPAN)));
		System.out.printf("birthdate of ASP in ... is %s\n", 
				birthDateAS.format(DateTimeFormatter.ofPattern("MMMM,d.yyy",
						Locale.forLanguageTag("he"))));
		
		LocalDate d1 = LocalDate.parse("1990-01-30");
		LocalDate d2 = LocalDate.parse("2000-10-20");
		ChronoUnit unit1 = ChronoUnit.MONTHS;
		System.out.printf("difference between %s and %s in %s is %d\n", d1, d2, unit1, unit1.between(d1, d2));

		LocalDateTime d3 = LocalDateTime.parse("1990-01-30T00:00:00");
		LocalDateTime d4 = LocalDateTime.parse("2000-10-20T00:00:00");
		ChronoUnit unit2 = ChronoUnit.SECONDS;
		System.out.printf("difference between %s and %s in %s is %d\n", d3, d4, unit2, unit2.between(d4, d3));
		
	}

}
