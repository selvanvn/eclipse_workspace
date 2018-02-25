package agile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

	// Method to calculate age
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

	// Method to validate dates before current date
	public String validateDateBeforeCurrent(String value1, String value2) {
		LocalDate today = LocalDate.now();
		if (!"null".equals(value1) && value2 == null) {
			LocalDate locDate1 = convertToLocalDate(value1);
			if (locDate1.isBefore(today)) {
				return "Yes";
			} else {
				return "No";
			}
		} else if (!"null".equals(value1) && !"null".equals(value2)) {
			LocalDate locDate1 = convertToLocalDate(value1);
			LocalDate locDate2 = convertToLocalDate(value2);
			if (locDate1.isBefore(today) && locDate2.isBefore(today)) {
				return "Yes";
			} else if (locDate1.isAfter(today) && locDate2.isBefore(today)) {
				return "Invalid Birthdate/Weddingdate";
			} else if (locDate1.isBefore(today) && locDate2.isAfter(today)) {
				return "Invalid Deathdate/Divorcedate";
			} else {
				return "No";
			}
		} else if ((value1 == null) && !"null".equals(value2)) {
			LocalDate locDate2 = convertToLocalDate(value2);
			if (locDate2.isBefore(today)) {
				return "Yes";
			} else {
				return "No";
			}
		} else if (value1 == null && value2 == null) {
			return "Null Dates";
		} else if (value1.isEmpty() && value2.isEmpty()) {
			return "Empty";
		} else {
			return "Invalid Dates";
		}
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
						//status = false;

						System.out.println("HusbId:" + husband_id + "   Sex:" + i.getSex() + "   InValid");

					}
				}
				i = (Individual) hm.get(wife_id);
				if (i != null) {
					if (i.getSex() == 'F') {
						// System.out.println("WifeId:"+wife_id+" Sex:"+i.getSex()+"
						// Valid");

					} else {

						System.out.println("WifeId:" + wife_id + "   Sex:" + i.getSex() + "   InValid");

						//status = false;

					}

				}
			}
			System.out.println("Gender Role Validation Completed");
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
				System.out.println("UniqueID Check Completed");
			} else {
				System.out.println("DuplicateID" + dupesList);
			}
		}
	public void printAllDetails(List<Individual> individuals, List<Family> families) {

		System.out.println("Individuals");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("|%-11s|%-22s|%-11s|%-11s|%-11s|%-11s|%-11s|%-11s|%-11s|%-11s|\n", "ID", "Name", "Gender",
				"Age", "Birthday", "Alive", "Death", "Child", "Spouse", "Valid Date");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		for (Individual indv : individuals) {
			individual_id.add(indv.getId());
			System.out.printf("|%-11s|%-22s|%-11s|%-11s|%-11s|%-11s|%-11s|%-11s|%-11s|%-11s|\n", indv.getId(),
					indv.getName(), indv.getSex(), indv.getAge(), indv.getBirthDate(), indv.getAliveStatus(),
					indv.getDeathDate(), indv.getChild(), indv.getSpouse(),
					validateDateBeforeCurrent(indv.getBirthDate(), indv.getDeathDate()));
		}
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------");
		uniqueIdCheck(individual_id);
		System.out.println("Families");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("|%-11s|%-11s|%-11s|%-11s|%-22s|%-11s|%-22s|%-11s|%-11s|\n", "ID", "Married", "Divorced",
				"Husband ID", "Husband Name", "Wife ID", "Wife Name", "Children", "Valid Date");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------");
		for (Family fam : families) {
			family_id.add(fam.getId());
			System.out.printf("|%-11s|%-11s|%-11s|%-11s|%-22s|%-11s|%-22s|%-11s|%-11s|\n", fam.getId(),
					fam.getWeddingDate(), fam.getDivorceDate(), fam.getHusbandId(), fam.getHusband(), fam.getWifeId(),
					fam.getWife(), fam.getChildId(),
					validateDateBeforeCurrent(fam.getWeddingDate(), fam.getDivorceDate()));
		}
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------");
		uniqueIdCheck(family_id);
	}

}
/*
 * public String validateDateBeforeCurrent(String value1, String value2){
 * LocalDate today = LocalDate.now(); Date date1 =null; Date date2 = null;
 * if(value1 != null && value2 == null){ String str1 = value1.replace(" ",
 * "-").toUpperCase(); try { date1 = new
 * SimpleDateFormat("dd-MMM-yyyy").parse(str1); } catch (ParseException e) {
 * e.printStackTrace(); } String newFormat1 = new
 * SimpleDateFormat("yyyy-MM-dd").format(date1); LocalDate locDate1 =
 * LocalDate.parse(newFormat1); if(locDate1.isBefore(today)){ return "yes"; }
 * else { return "No"; } }else if(value1 != null && value2 != null) { String
 * str1 = value1.replace(" ", "-").toUpperCase(); String str2 =
 * value2.replace(" ", "-").toUpperCase(); try { date1 = new
 * SimpleDateFormat("dd-MMM-yyyy").parse(str1); date2 = new
 * SimpleDateFormat("dd-MMM-yyyy").parse(str2); } catch (ParseException e) {
 * e.printStackTrace(); } String newFormat1 = new
 * SimpleDateFormat("yyyy-MM-dd").format(date1); String newFormat2 = new
 * SimpleDateFormat("yyyy-MM-dd").format(date2); LocalDate locDate1 =
 * LocalDate.parse(newFormat1); LocalDate locDate2 =
 * LocalDate.parse(newFormat2); if(locDate1.isBefore(today) &&
 * locDate2.isBefore(today)) { return "yes"; } else if(locDate1.isAfter(today)
 * && locDate2.isBefore(today)) { return "Invalid Birthdate/Weddingdate"; } else
 * if(locDate1.isBefore(today) && locDate2.isAfter(today)) {
 * 
 * return "Invalid Deathdate/Divorcedate"; } else {
 * 
 * return "No"; } }else if (value1 == null && value2 != null){ String str2 =
 * value2.replace(" ", "-").toUpperCase(); try { date2 = new
 * SimpleDateFormat("dd-MMM-yyyy").parse(str2); } catch (ParseException e) {
 * e.printStackTrace(); } String newFormat2 = new
 * SimpleDateFormat("yyyy-MM-dd").format(date2); LocalDate locDate2 =
 * LocalDate.parse(newFormat2); if(locDate2.isBefore(today)){ return "Yes"; }
 * else { return "Yes"; } }else { return "No Dates"; }
 * 
 * 
 * }
 */
