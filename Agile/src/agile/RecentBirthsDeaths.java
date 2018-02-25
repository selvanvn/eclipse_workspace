package agile;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

public class RecentBirthsDeaths {
	public void recentBirth(parser par) {
		System.out.println("INDIVIDUAL: US35: Displayed Recent Births");
		System.out.println("--------------------------------------------");
		System.out.println("               Recent Births                ");
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
			//MonthDay birth = MonthDay.of(bdate1.getMonth(), bdate1.getDayOfMonth());
			LocalDate end = LocalDate.now();
			LocalDate start = LocalDate.now().minusDays(30);
			List<LocalDate> totalDates1 = new ArrayList<>();
			while (!start.isAfter(end)) {
				//MonthDay srt1 = MonthDay.of(start.getMonth(), start.getDayOfMonth());
				totalDates1.add(start);
				start = start.plusDays(1);
			}
		
				if (totalDates1.contains(bdate1)) {
					System.out.printf("|%-8s|%-20s|%-12s|", id, name, bdate);
					System.out.println();
					status = true;
				}
			}

		}
		if (!status) {
			System.out.println("No recent births ");
		}

		System.out.println("--------------------------------------------");
	}
	
	public void recentDeath(parser par) {
		System.out.println("INDIVIDUAL: US36: Displayed Recent Deaths");
		System.out.println("--------------------------------------------");
		System.out.println("               Recent Deaths                ");
		System.out.println("--------------------------------------------");
		System.out.printf("|%-8s|%-20s|%-12s|", "ID", "Name", "Death Date");
		System.out.println();
		System.out.println("--------------------------------------------");
		boolean status = false;
		for (Individual indbi : par.individuals_list) {
			String id = indbi.getId();
			String name = indbi.getName();
			String ddate = indbi.getDeathDate();
			if (ddate != null && !ddate.isEmpty()) {
			LocalDate ddate1 = par.convertToLocalDate(ddate);
			//MonthDay death = MonthDay.of(ddate1.getMonth(), ddate1.getDayOfMonth());
			LocalDate end = LocalDate.now();
			LocalDate start = LocalDate.now().minusDays(30);
			List<LocalDate> totalDates1 = new ArrayList<>();
			while (!start.isAfter(end)) {
				//MonthDay srt1 = MonthDay.of(start.getMonth(), start.getDayOfMonth());
				totalDates1.add(start);
				start = start.plusDays(1);
			}
			
				if (totalDates1.contains(ddate1)) {
					System.out.printf("|%-8s|%-20s|%-12s|", id, name, ddate);
					System.out.println();
					status = true;
				}
			}

		}
		if (!status) {
			System.out.println("No recent deaths ");
		}
		
		System.out.println("--------------------------------------------");
	}
}
