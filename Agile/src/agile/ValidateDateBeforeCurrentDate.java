package agile;

import java.time.LocalDate;

public class ValidateDateBeforeCurrentDate {

	// US:01 Method to validate dates before current date for Individual

	public void validateDatesBeforeCurrentIndv(parser par) {
		for (Individual indc : par.individuals_list) {

			String value1 = indc.getBirthDate();
			String value2 = indc.getDeathDate();
			String Id = indc.getId();

			LocalDate today = LocalDate.now();
			if (!"null".equals(value1) && value2 == null) {
				LocalDate locDate1 = par.convertToLocalDate(value1);
				if (locDate1.isBefore(today)) {
					// System.out.println("Yes");
				} else {
					System.out.println("ERROR: INDIVIDUAL: US01: Birthday " + value1 + " for the individual " + Id
							+ " occurs in the future.");
				}
			} else if (!"null".equals(value1) && !"null".equals(value2)) {
				LocalDate locDate1 = par.convertToLocalDate(value1);
				LocalDate locDate2 = par.convertToLocalDate(value2);
				if (locDate1.isBefore(today) && locDate2.isBefore(today)) {
					// System.out.println("Yes");
				} else if (locDate1.isAfter(today) && locDate2.isBefore(today)) {
					System.out.println("ERROR: INDIVIDUAL: US01: Birthday " + value1 + " for the individual " + Id
							+ " occurs in the future.");
				} else if (locDate1.isBefore(today) && locDate2.isAfter(today)) {
					System.out.println("ERROR: INDIVIDUAL: US01: Death Date " + value2 + " for the individual " + Id
							+ " occurs in the future.");
				} else {
					System.out.println("ERROR: INDIVIDUAL: US01: Invalid date format for the individual " + Id);
				}
			} else if ((value1 == null) && !"null".equals(value2)) {
				LocalDate locDate2 = par.convertToLocalDate(value2);
				if (locDate2.isBefore(today)) {
					// return "Yes";
				} else {
					System.out.println("ERROR: INDIVIDUAL: US01: Death Date " + value2 + " for the individual " + Id
							+ " occurs in the future.");
				}
			} else if (value1 == null && value2 == null) {
				System.out.println("ERROR: INDIVIDUAL: US01: Dates are null for the individual " + Id);
			} else if (value1.isEmpty() && value2.isEmpty()) {
				System.out.println("ERROR: INDIVIDUAL: US01: Dates are empty for the individual " + Id);
			} else {
				System.out.println("ERROR: INDIVIDUAL: US01: Invalid date format for the individual " + Id);

			}
		}
		System.out.println("INDIVIDUAL: US01: Validated all dates before current date");
	}

	// US:01 Method to validate dates before current date for Family

	public void validateDatesBeforeCurrentFam(parser par) {
		for (Family famc : par.families_list) {
			String value1 = famc.getWeddingDate();
			String value2 = famc.getDivorceDate();
			String fId = famc.getId();
			String hId = famc.getHusbandId();
			String wId = famc.getWifeId();

			LocalDate today = LocalDate.now();
			if (!"null".equals(value1) && value2 == null) {
				LocalDate locDate1 = par.convertToLocalDate(value1);
				if (locDate1.isBefore(today)) {
					// System.out.println("Yes");
				} else {
					System.out.println("ERROR: FAMILY: US01: Wedding Date " + value1 + " for the family " + fId
							+ " [Husband: " + hId + " Wife: " + wId + "] occurs in the future.");
				}
			} else if (!"null".equals(value1) && !"null".equals(value2)) {
				LocalDate locDate1 = par.convertToLocalDate(value1);
				LocalDate locDate2 = par.convertToLocalDate(value2);
				if (locDate1.isBefore(today) && locDate2.isBefore(today)) {
					// System.out.println("Yes");
				} else if (locDate1.isAfter(today) && locDate2.isBefore(today)) {
					System.out.println("ERROR: FAMILY: US01: Wedding Date " + value1 + " for the family " + fId
							+ " [Husband: " + hId + " Wife: " + wId + "] occurs in the future.");
				} else if (locDate1.isBefore(today) && locDate2.isAfter(today)) {
					System.out.println("ERROR: FAMILY: US01: Divorce Date " + value2 + " for the family " + fId
							+ " [Husband: " + hId + " Wife: " + wId + "] occurs in the future.");
				} else {
					System.out.println("ERROR: FAMILY: US01: Invalid date format for the family " + fId);
				}
			} else if ((value1 == null) && !"null".equals(value2)) {
				LocalDate locDate2 = par.convertToLocalDate(value2);
				if (locDate2.isBefore(today)) {
					// return "Yes";
				} else {
					System.out.println("ERROR: FAMILY: US01: Divorce Date " + value2 + " for the family " + fId
							+ " [Husband: " + hId + " Wife: " + wId + "] occurs in the future");
				}
			} else if (value1 == null && value2 == null) {
				System.out.println("ERROR: FAMILY: US01: Dates are null for the family " + fId);
			} else if (value1.isEmpty() && value2.isEmpty()) {
				System.out.println("ERROR: FAMILY: US01: Dates are empty for the family " + fId);
			} else {
				System.out.println("ERROR: FAMILY: US01: Invalid date format for the family " + fId);
			}
		}
		System.out.println("FAMILY: US01: Validated all dates before current date");
	}

}
