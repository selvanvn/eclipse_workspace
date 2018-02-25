package agile;

import java.util.HashMap;

public class ValidateMarriageAfter14 {

	// US10: Method to validate marriage after 14

	public void validateMarriage(parser par) {
		HashMap<String, Object> mar = new HashMap<String, Object>();
		for (Individual indl : par.individuals_list) {

			mar.put(indl.getId(), indl);
		}

		for (Family fam : par.families_list) {
			if (fam.getWeddingDate() != null) {
				String fid = fam.getId();
				String husband_id = fam.getHusbandId();
				String wife_id = fam.getWifeId();
				Individual i = (Individual) mar.get(husband_id);
				Individual w = (Individual) mar.get(wife_id);
				if (i.getAge() >= 14 && w.getAge() >= 14) {
					// System.out.println();
				} else if (i.getAge() < 14) {
					System.out.println("ERROR: FAMILY: US10: Marriage before 14 - Family: " + fid + " Husband: "
							+ husband_id + " age is less than 14");
				} else if (w.getAge() < 14) {
					System.out.println("ERROR: FAMILY: US10: Marriage before 14 - Family: " + fid + " Wife: " + wife_id
							+ " age is less than 14");
				} else {
					System.out.println("ERROR: FAMILY: US10: Marriage before 14 - Family: " + fid + " Husband ID: "
							+ husband_id + " Wife ID: " + wife_id);
				}
			}

		}
	}
}
