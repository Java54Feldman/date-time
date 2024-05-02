package telran.time.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
		
	}

}
