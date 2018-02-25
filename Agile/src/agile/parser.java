package agile;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.format.DateTimeFormatter;
import java.util.*;
//import java.time.LocalDate;
import java.time.*;

public class parser {
	// storing list of individuals and families
	public List<Individual> individuals_list = new ArrayList<Individual>();
	public List<Family> families_list = new ArrayList<Family>();
	public HashMap<String, Object> hm = new HashMap<String, Object>();
	public List<String> individual_id = new ArrayList<String>();
	public List<String> family_id = new ArrayList<String>();

	private String getVar(String[] parseLine) {
		String var = "";
		for (int i = 2; i < parseLine.length; i++) {
			var = var + " " + parseLine[i];
		}
		return var.trim();
	}

	// replacing "@" by space in id
	private String getId(String Id) {
		return Id.replace("@", "");
	}

	public void readFile(String file) throws IOException {

		FileInputStream fileInput = null;
		BufferedReader bufferRead = null;

		try {

			fileInput = new FileInputStream(file);
			bufferRead = new BufferedReader(new InputStreamReader(fileInput));

			String line = null;
			boolean flagIndi = false;
			boolean flagBirthDate = true;
			Individual ind = null;
			Family fam = null;

			while ((line = bufferRead.readLine()) != null) {
				String[] parseLine = (line.split("\\s+"));
				int level = Integer.valueOf(parseLine[0]);
				String tag = parseLine[1];
				String var = (parseLine.length > 2) ? getVar(parseLine) : null;
				if (level == 0) {
					if ("INDI".equals(var)) {
						ind = new Individual();
						if (ind != null) {
							ind.setId(getId(tag));
							individuals_list.add(ind);
							flagIndi = true;
						}
					} else if ("FAM".equals(var)) {
						fam = new Family();
						if (fam != null) {
							families_list.add(fam);
							fam.setId(getId(tag));
							flagIndi = false;
						}
					} else {
						flagIndi = false;
					}
				}
				if (level == 1) {
					if (flagIndi) {
						if (tag.equals("NAME")) {
							ind.setName(var);
							// System.out.println(var+"\n");
						}
						if (tag.equals("SEX")) {
							ind.setSex(var.charAt(0));
						}
						if (tag.equals("BIRT")) {
							flagBirthDate = true;
						}
						if (tag.equals("DEAT")) {
							flagBirthDate = false;
						}
						if (tag.equals("FAMS")) {
							ind.setSpouse(var);
						}
						if (tag.equals("FAMC")) {
							ind.setChild(var);
						}
					} else {
						if ("HUSB".equals(tag)) {
							fam.setHusbandId(getId(var));
						} else if ("WIFE".equals(tag)) {
							fam.setWifeId(getId(var));
						} else if ("CHIL".equals(tag)) {
							fam.setChildId(getId(var));
						}

						if ("MARR".equals(tag)) {
							line = bufferRead.readLine();

							String[] nextLine = (line.split("\\s+"));
							if (nextLine[1].equals("DATE")) {
								String wedDate = nextLine[2] + " " + nextLine[3] + " " + nextLine[4];
								fam.setWeddingDate(wedDate);
							}

						}
						if (tag.equals("DIV")) {
							line = bufferRead.readLine();
							String[] nextLine1 = (line.split("\\s+"));
							if (nextLine1[1].equals("DATE")) {
								String divDate = nextLine1[2] + " " + nextLine1[3] + " " + nextLine1[4];
								fam.setDivorceDate(divDate);
							}
						}

					}
				}
				if (level == 2) {
					if (flagIndi) {
						if (tag.equals("SURN")) {
							ind.setSurName(var);
						}
						if (tag.equals("GIVN")) {
							ind.setGivenName(var);
						}
						if (tag.equals("DATE")) {
							if (flagBirthDate) {
								ind.setBirthDate(var);
								ind.setAliveStatus(true);
								ind.setAge(calculateAge(ind.getBirthDate(), LocalDate.now().toString()));
							} else {
								ind.setDeathDate(var);
								ind.setAliveStatus(false);
								ind.setAge(calculateAge(ind.getBirthDate(), ind.getDeathDate()));
								compare(ind.getBirthDate(), ind.getDeathDate(), ind.getId());
							}
						}
					}
				}
			}

		} catch (FileNotFoundException ex) {
			System.out.println("File is not found!");
		}

		setIndividualsInFamilies();
		printAllDetails(individuals_list, families_list);
	}

	public Individual getIndividual(String id) {
		if (individuals_list != null && !individuals_list.isEmpty()) {
			for (int i = 0; i < individuals_list.size(); i++) {
				Individual individualObject = individuals_list.get(i);
				if (individualObject.getId().equals(id)) {
					return individualObject;
				}
			}
		}
		return null;
	}

	private void setIndividualsInFamilies() {
		for (int i = 0; i < families_list.size(); i++) {

			Family fam = families_list.get(i);
			if (fam.getHusbandId() != null) {
				fam.setHusband(getIndividual(fam.getHusbandId()));

				// System.out.println("Hus"+fam.getHusband().toString());
			}
			if (fam.getWifeId() != null) {
				fam.setWife(getIndividual(fam.getWifeId()));
				// System.out.println("Wife"+fam.getWife());

			}
		}
	}

	// Method to list Deceased
	public void listDeceased() {
		System.out.println("US29: Deceased List");
		for (Individual ind : individuals_list) {
			String ddate = ind.getDeathDate();
			if (ddate != null) {
				System.out.println(">  " + ind.getId() + "   " + ind.getName());
			}
		}
	}
	//Method to list Married
	public void listLivingMarried()
	{
		System.out.println("US30: Living Married List");
		for(Individual ind:individuals_list){
			if(ind.getAliveStatus()==true && ind.getSpouse() != null)
			{
				System.out.println(">  " + ind.getId() + "   " + ind.getName());
			}
		}
	}
	//Method to list Single
	public void listLivingSingle()
	{
		System.out.println("US31: Living Single List");
		for(Individual ind:individuals_list){
			if(ind.getAliveStatus()==true && ind.getSpouse() == null && ind.getAge() > 30)
			{
				System.out.println(">  " + ind.getId() + "   " + ind.getName());
			}
		}
	}
	// Method to check legitimate dates
	public void legitimateDate() {
		// public static boolean isDateValid(String date)
		// {
		DateFormat df = new SimpleDateFormat("dd MMM yyyy");
		for (Individual ind : individuals_list) {
			if (ind.getBirthDate() != null) {
				try {

					df.setLenient(false);
					df.parse(ind.getBirthDate());
				} catch (ParseException e) {
					System.out.println("ERROR:INDIVIDUAL:US42:BirthDate:" + ind.getBirthDate() + " " + ind.getId() + " "
							+ ind.getName());
				}
			}
			if (ind.getDeathDate() != null) {

				try {
					df.setLenient(false);
					df.parse(ind.getDeathDate());
				} catch (ParseException e) {
					System.out.println("ERROR:INDIVIDUAL:US42:DeathDate:" + ind.getDeathDate() + " " + ind.getId() + " "
							+ ind.getName());
				}
			}
		}
		for (Family fam : families_list) {

			if (fam.getWeddingDate() != null) {
				try {

					df.setLenient(false);
					df.parse(fam.getWeddingDate());
				} catch (ParseException e) {
					System.out.println("ERROR:FAMILY:US42:WeddingDate:" + fam.getWeddingDate() + " " + fam.getId());
				}
			}
			if (fam.getDivorceDate() != null) {
				try {

					df.setLenient(false);
					df.parse(fam.getDivorceDate());
				} catch (ParseException e) {
					System.out.println("ERROR:FAMILY:US42:DivorceDate:" + fam.getDivorceDate() + " " + fam.getId());
				}
			}
		}
		/*
		 * try { DateFormat df = new SimpleDateFormat("dd MMM yyyy");
		 * df.setLenient(false); df.parse(); return true; } catch
		 * (ParseException e) { return false; }
		 */
		// }
	}

	// US:27 Method to calculate age
	public int calculateAge(String birthDate, String deathDate) {
		LocalDate birDate = convertToLocalDate(birthDate);
		LocalDate deaDate;
		if (deathDate.matches(".*[a-zA-Z]+.*")) {
			deaDate = convertToLocalDate(deathDate);
			return Period.between(birDate, deaDate).getYears();
		} else {
			deaDate = LocalDate.parse(deathDate);
			return Period.between(birDate, deaDate).getYears();
		}
	}

	// Method to convert string to LocalDate format
	public LocalDate convertToLocalDate(String date1) {

		String s2 = date1.replace(" ", "-").toUpperCase();
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MMM-yyyy").parse(s2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String newFormat = new SimpleDateFormat("yyyy-MM-dd").format(date);
		LocalDate date2 = LocalDate.parse(newFormat);
		return date2;
	}

	// Gender Role Validation
	public void genderRoleValidate() {
		Individual i;
		for (Individual induvidual : individuals_list) {

			hm.put(induvidual.getId(), induvidual);
		}

		for (Family fam : families_list) {
			String husband_id = fam.getHusbandId();
			String wife_id = fam.getWifeId();

			i = (Individual) hm.get(husband_id);
			if (i != null) {
				if (i.getSex() == 'M') {
					// System.out.println("HusbId:"+husband_id+"
					// Sex:"+i.getSex()+ " Valid");

				} else {
					// status = false;

					System.out.println("ERROR: US21: HusbId:" + husband_id + "   Sex:" + i.getSex() + "   InValid");

				}
			}
			i = (Individual) hm.get(wife_id);
			if (i != null) {
				if (i.getSex() == 'F') {
					// System.out.println("WifeId:"+wife_id+" Sex:"+i.getSex()+"
					// Valid");

				} else {

					System.out.println("ERROR: US21: WifeId:" + wife_id + "   Sex:" + i.getSex() + "   InValid");

					// status = false;

				}

			}
		}
	}

	// Unique ID check
	public void uniqueIdCheck(List<String> idList) {
		Set<String> uniqueSet = new HashSet<String>();
		List<String> dupesList = new ArrayList<String>();
		for (String s : idList) {
			if (uniqueSet.contains(s)) {
				dupesList.add(s);
			} else {
				uniqueSet.add(s);
			}

		}
		if (dupesList.isEmpty()) {
			System.out.println("US22: UniqueID Check Completed");
		} else {
			System.out.println("ERROR: US22: DuplicateID" + dupesList);
		}
	}

	// method to check birth date before death date

	public static boolean compare(String birthDateString, String deathDateString, String ID) {

		SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
		Date birthDate = null;
		Date deathDate = null;

		try {
			birthDate = format.parse(birthDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			deathDate = format.parse(deathDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (birthDate.compareTo(deathDate) == 1) {
			System.out.printf(" \nERROR: INDIVIDUAL: US03: Birth date is after the death date for INDIVIDUAL " + ID
					+ " Birth date=" + birthDateString + " Death date=" + deathDateString);

			return false;
		} else {
			// System.out.printf(" \n*US03* Birthdate before Death date checked
			// for " + ID);
			return true;
		}

	}

	// method to check birth before marriage

	public void compareb() {
		Individual i;
		for (Individual induvidual : individuals_list) {

			hm.put(induvidual.getId(), induvidual);
			hm.put(induvidual.getBirthDate(), induvidual);
		}

		for (Family fam : families_list) {
			String husband_id = fam.getHusbandId();
			String wife_id = fam.getWifeId();
			String weddingdate = fam.getWeddingDate();
			i = (Individual) hm.get(husband_id);
			if (i != null) {
				SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
				Date birthDate = null;
				Date marriageDate = null;
				String bdate = i.getBirthDate();
				try {
					birthDate = format.parse(i.getBirthDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				try {
					marriageDate = format.parse(weddingdate);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				if (birthDate.compareTo(marriageDate) == 1) {
					System.out
							.println("\nERROR: INDIVIDUAL: US02: Birth date is after the marraige date for INDIVIDUAL "
									+ i.getId() + " Birth date=" + bdate + " Marriage date= " + weddingdate);

				} else {
					// System.out.printf("\n *US02* Birthdate before marraige
					// date checked for " + i.getId());

				}
			}
			i = (Individual) hm.get(wife_id);
			if (i != null) {
				SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
				Date birthDate = null;
				Date marriageDate = null;
				String bdate = i.getBirthDate();
				try {
					birthDate = format.parse(i.getBirthDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				try {
					marriageDate = format.parse(weddingdate);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				if (birthDate.compareTo(marriageDate) == 1) {
					System.out.println("ERROR: INDIVIDUAL: US02: Birth date is after the marraige date for INDIVIDUAL "
							+ i.getId() + " Birth date=" + bdate + " Marriage date= " + weddingdate);

				} else {
					// System.out.printf(" \n *US02* Birthdate before marraige
					// date checked for " + i.getId());

				}

			}

		}
		System.out.printf("INDIVIDUAL: US02: Birth date before Marriage date check completed \n");
	}

	// Method to check if Marriage date is before death date
	public List<Family> marriagebeforedeath() {
		Individual i;
		for (Individual induvidual : individuals_list) {

			hm.put(induvidual.getId(), induvidual);
			hm.put(induvidual.getDeathDate(), induvidual);
		}

		for (Family fam : families_list) {
			String husband_id = fam.getHusbandId();
			String wife_id = fam.getWifeId();
			String weddingdate = fam.getWeddingDate();
			i = (Individual) hm.get(husband_id);
			if (i != null) {
				SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
				Date deathDate = null;
				Date marriageDate = null;
				String DDate = i.getDeathDate();
				try {
					if (i.getDeathDate() != null)
						deathDate = format.parse(i.getDeathDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				try {
					marriageDate = format.parse(weddingdate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (deathDate != null) {
					if (marriageDate.compareTo(deathDate) == 1) {
						System.out.println(
								"ERROR: INDIVIDUAL:US05: Marriage date should be before the Death date for INDIVIDUAL "
										+ i.getId() + " Marriage date=" + weddingdate + " Death date=" + DDate);

					} else {
						// System.out.printf("\n *US05* Marriage date before
						// Death date checked for " + i.getId());

					}
				} else {
					// System.out.printf("\n *US05* Marriage date before Death
					// date checked for " + i.getId());
				}
			}
			i = (Individual) hm.get(wife_id);
			if (i != null) {
				SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
				Date deathDate = null;
				Date marriageDate = null;
				String DDate = i.getDeathDate();
				try {
					if (i.getDeathDate() != null)
						deathDate = format.parse(i.getDeathDate());

				} catch (ParseException e) {
					e.printStackTrace();
				}
				try {
					marriageDate = format.parse(weddingdate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (i.getDeathDate() != null) {

					if (marriageDate.compareTo(deathDate) == 1) {
						System.out.println(
								"ERROR :INDIVIDUAL: US05: Marriage date should be before the Death date for INDIVIDUAL "
										+ i.getId() + " Marriage date=" + weddingdate + " Death date=" + DDate);

					} else {
						// System.out.printf("\n *US05* Marriage date before
						// Death date checked for " + i.getId());

					}
				} else {
					// System.out.printf("\n *US05* Marriage date before Death
					// date checked for " + i.getId());
				}
			}

		}
		System.out.printf("INDIVIDUAL: US05: Marriage date before Death date check completed \n");
		return families_list;
	}

	public List<Individual> divorcebeforedeath() {
		Individual i;
		for (Individual induvidual : individuals_list) {

			hm.put(induvidual.getId(), induvidual);
			hm.put(induvidual.getDeathDate(), induvidual);
		}

		for (Family fam : families_list) {
			String husband_id = fam.getHusbandId();
			String wife_id = fam.getWifeId();
			String divorcedate = fam.getDivorceDate();
			i = (Individual) hm.get(husband_id);
			if (i != null && divorcedate != null) {
				SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
				Date deathDate = null;
				Date divorceDate = null;
				String Ddate = i.getDeathDate();
				try {
					if (i.getDeathDate() != null)
						deathDate = format.parse(i.getDeathDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				try {
					divorceDate = format.parse(divorcedate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (i.getDeathDate() != null) {
					if (divorceDate.compareTo(deathDate) == 1) {
						System.out
								.println("ERROR: INDIVIDUAL: US06: Divorce date is after the Death date for INDIVIDUAL "
										+ i.getId() + " Divorce date=" + divorcedate + " Death date=" + Ddate);

					} else {
						// System.out.printf("\n *US06* Divorce date before
						// Death date checked for " + i.getId());
					}
				} else {
					System.out.printf("*US06* Divorce date before Death date checked for " + i.getId());
				}

			}
			i = (Individual) hm.get(wife_id);
			if (i != null && divorcedate != null) {
				SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
				Date deathDate = null;
				Date divorceDate = null;
				String Ddate = i.getDeathDate();
				try {
					if (i.getDeathDate() != null)
						deathDate = format.parse(i.getDeathDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				try {
					divorceDate = format.parse(divorcedate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (i.getDeathDate() != null) {
					if (deathDate.compareTo(divorceDate) == 1) {
						System.out
								.println("ERROR: INDIVIDUAL: US06: Divorce date is after the Death date for INDIVIDUAL "
										+ i.getId() + "Divorce date=" + divorcedate + " Death date=" + Ddate);

					} else {
						// System.out.printf("\n *US06* Divorce date before
						// Death date checked for " + i.getId());

					}

				} else {
					// System.out.printf("\n *US06* Divorce date before Death
					// date checked for " + i.getId() );
				}

			}
		}
		System.out.printf("INDIVIDUAL: US06: Divorce date before Death date check completed \n");
		return individuals_list;
	}

	// Method to print individual and family list

	public void printAllDetails(List<Individual> individuals, List<Family> families) {

		System.out.println("\nIndividuals");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("|%-11s|%-22s|%-11s|%-11s|%-11s|%-11s|%-11s|%-11s|%-11s|\n", "ID", "Name", "Gender", "Age",
				"Birthday", "Alive", "Death", "Child", "Spouse");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------");
		for (Individual indv : individuals) {
			individual_id.add(indv.getId());
			System.out.printf("|%-11s|%-22s|%-11s|%-11s|%-11s|%-11s|%-11s|%-11s|%-11s|\n", indv.getId(), indv.getName(),
					indv.getSex(), indv.getAge(), indv.getBirthDate(), indv.getAliveStatus(), indv.getDeathDate(),
					indv.getChild(), indv.getSpouse());

		}
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------");
		System.out.println("INDIVIDUAL: US27: Displayed Individuals Age");
		uniqueIdCheck(individual_id);
		System.out.println("Families");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("|%-11s|%-11s|%-11s|%-11s|%-22s|%-11s|%-22s|%-22s|\n", "ID", "Married", "Divorced",
				"Husband ID", "Husband Name", "Wife ID", "Wife Name", "Children");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------------------");
		for (Family fam : families) {
			family_id.add(fam.getId());
			System.out.printf("|%-11s|%-11s|%-11s|%-11s|%-22s|%-11s|%-22s|%-22s|\n", fam.getId(), fam.getWeddingDate(),
					fam.getDivorceDate(), fam.getHusbandId(), fam.getHusband(), fam.getWifeId(), fam.getWife(),
					fam.getChildId());
		}
		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------------------");
		uniqueIdCheck(family_id);
	}
}
