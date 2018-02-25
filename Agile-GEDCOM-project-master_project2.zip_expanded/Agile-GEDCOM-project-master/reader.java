package agile;

//import java.io.BufferedReader;

//import java.io.FileReader;
import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class reader {

	// private static final List<String> genTag =
	// Arrays.asList("NAME","SEX","BIRT","DEAT","FAMC","FAMS","MARR","HUSB","WIFE","CHIL","DIV","DATE");
	// private static final List<String> zeroTag = Arrays.asList("INDI", "FAM",
	// "HEAD","TRLR","NOTE");

	/**
	 * method to read ged filfe and print
	 * 
	 * @throws IOException
	 */

	// public void parse() throws IOException {
	// String gedFile = "C:/Users/Preity/Desktop/Prema Gopu.ged";

	// String gedFileName = "C:/Eclipse/PoojithaShivaPrasad.ged";
	// ClassLoader classLoader = getClass().getClassLoader();
	// File gedFile = new File(classLoader.getResource(gedFileName).getFile());
	// BufferedReader br = new BufferedReader(new FileReader(gedFile));
	// String line = "";

	// parser p = new parser();
	// p.readFile(gedFile);

	/*
	 * while ((line = br.readLine()) != null) { // String[] array =
	 * line.split(" "); // if(array.length > 1) { if("0".equals(array[0])) {
	 * if(array[1].matches("[A-Za-z]+")) { System.out.print("Level : "
	 * +array[0]+ " Tag : " + array[1]);
	 * if(zeroTag.contains(array[1]))System.out.println(); else
	 * System.out.println(" \nTag Invalid"); } else {
	 * System.out.print("Level : " +array[0]+ " Tag : " + array[2]);
	 * if(zeroTag.contains(array[2]))System.out.println(); else
	 * System.out.println(" \nTag Invalid"); } } else {
	 * System.out.print("Level:" + array[0]+ " Tag:" + array[1]);
	 * if(genTag.contains(array[1]))System.out.println(); else
	 * System.out.println(" \nTag Invalid"); } }
	 */
	// }

	/**
	 * create object for the class call parse method
	 */
	public static void main(String[] args) throws IOException {
		List<Individual> individuals_list = new ArrayList<Individual>();
		List<Family> families_list = new ArrayList<Family>();
		String gedFile = "C:/studies/2agile/PoojithaShivaPrasad.ged.ged";
		parser p = new parser(); 
		p.readFile(gedFile);
		p.validateDatesBeforeCurrentIndv();
		p.validateDatesBeforeCurrentFam();
		p.genderRoleValidate();
		p.compareb();
		families_list=p.marriagebeforedeath();
		individuals_list=p.divorcebeforedeath();
		AgeLessThan150 obj1= new AgeLessThan150();
		obj1.ageCheck(individuals_list);
		BirthBeforeMarriageOfParents obj2=new BirthBeforeMarriageOfParents();
		obj2.checkChildBirth(individuals_list, families_list);
		MarriageBeforeDivorce obj3=new MarriageBeforeDivorce();
		obj3.compare3(families_list);
	}

}
