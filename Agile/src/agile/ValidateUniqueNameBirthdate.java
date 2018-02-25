package agile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidateUniqueNameBirthdate {
	
	// US23: Unique name and birth date validation
	
	public void validateUniqueNameBirthDate(parser par) {
		List<String> allList = new ArrayList<String>();
		for (Individual indd : par.individuals_list) {
			String iname = indd.getName().replace("/", "").trim();
			String bd = indd.getBirthDate();
			if (iname != null && !iname.isEmpty()) {
				if (bd != null && !bd.isEmpty()) {
					LocalDate dob = par.convertToLocalDate(bd);
					String namedob = iname + " " + dob.toString().trim();
					allList.add(namedob);
				}
			}
		}
		Set<String> uniqueSet = new HashSet<String>();
		List<String> dupesList = new ArrayList<String>();
		for (String s : allList) {
			if (uniqueSet.contains(s)) {
				dupesList.add(s);
			} else {
				uniqueSet.add(s);
			}
		}
		if (dupesList.isEmpty()) {

		} else {
			System.out.println("ERROR: INDIVIDUAL: US23: Duplicate name and birthdate found " + dupesList);
		}
		System.out.println("INDIVIDUAL: US23: Validated all unique name and birthdate");
	}

}
