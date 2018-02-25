package agile;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

public class BirthdaysAnniversaries {

	// US:38 Method to list upcoming birthdays
	
	public void upcomingBirthdays(parser par) {
		System.out.println("INDIVIDUAL: US38: Displayed Upcoming Birthdays");
		System.out.println("--------------------------------------------");
		System.out.println("          Upcoming Birthdays List           ");
		System.out.println("--------------------------------------------");
		System.out.printf("|%-8s|%-20s|%-12s|", "ID", "Name", "Birthday");
		System.out.println();
		System.out.println("--------------------------------------------");
		boolean status = false;
		for (Individual indbi : par.individuals_list) {
			String id = indbi.getId();
			String name = indbi.getName();
			String bdate = indbi.getBirthDate();
			if (bdate != null && !bdate.isEmpty()) {
			LocalDate bdate1 = par.convertToLocalDate(bdate);
			MonthDay birth = MonthDay.of(bdate1.getMonth(), bdate1.getDayOfMonth());
			LocalDate start = LocalDate.now();
			LocalDate end = LocalDate.now().plusDays(30);
			List<MonthDay> totalDates1 = new ArrayList<>();
			while (!start.isAfter(end)) {
				MonthDay srt1 = MonthDay.of(start.getMonth(), start.getDayOfMonth());
				totalDates1.add(srt1);
				start = start.plusDays(1);
			}
			
				if (totalDates1.contains(birth)) {
					System.out.printf("|%-8s|%-20s|%-12s|", id, name, bdate);
					System.out.println();
					status = true;
				}
			}

		}
		if (!status) {
			System.out.println("No upcoming birthdays ");
		}

		System.out.println("--------------------------------------------");
	}

	// US:39 Method to list upcoming anniversaries
	
	public void upcomingAnniversaries(parser par) {
		System.out.println("FAMILY: US39: Displayed Upcoming Anniversaries");
		System.out.println("------------------------------------------------------------------");
		System.out.println("                   Upcoming Anniversaries List                    ");
		System.out.println("------------------------------------------------------------------");
		System.out.printf("|%-9s|%-20s|%-20s|%-12s|", "Family ID", "Husband", "Wife", "Wedding Date");
		System.out.println();
		System.out.println("------------------------------------------------------------------");
		boolean status = false;
		for (Family famav : par.families_list) {
			String fid = famav.getId();
			String hus = famav.getHusband().toString();
			String wif = famav.getWife().toString();
			String wdate = famav.getWeddingDate();
			if (wdate != null && !wdate.isEmpty()) {
			LocalDate wdate1 = par.convertToLocalDate(wdate);
			MonthDay birth = MonthDay.of(wdate1.getMonth(), wdate1.getDayOfMonth());
			LocalDate start = LocalDate.now();
			LocalDate end = LocalDate.now().plusDays(30);
			List<MonthDay> totalDates1 = new ArrayList<>();
			while (!start.isAfter(end)) {
				MonthDay srt1 = MonthDay.of(start.getMonth(), start.getDayOfMonth());
				totalDates1.add(srt1);
				start = start.plusDays(1);
			}

			
				if (totalDates1.contains(birth)) {
					System.out.printf("|%-9s|%-20s|%-20s|%-12s|", fid, hus, wif, wdate);
					System.out.println();
					status = true;
				}
			}

		}
		if (!status) {
			System.out.println("No upcoming anniversaries ");
		}

		System.out.println("------------------------------------------------------------------");
	}
}
