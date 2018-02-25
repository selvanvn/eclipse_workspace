package com.test.gedcom;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileRead {
	
	private static final List<String> genTag = Arrays.asList("NAME","SEX","BIRT","DEAT","FAMC","FAMS","MARR","HUSB","WIFE","CHIL","DIV","DATE");
	private static final List<String> zeroTag = Arrays.asList("INDI", "FAM", "HEAD","TRLR","NOTE");
	
	/**
	 * method to read ged file
	 * and print
	 * @throws IOException
	 */
	
	public  void parse() throws IOException {
		String gedFileName = "com/test/gedcom/Selva_Chandran.ged";
		ClassLoader classLoader = getClass().getClassLoader();
		File gedFile = new File(classLoader.getResource(gedFileName).getFile());
		BufferedReader br = new BufferedReader(new FileReader(gedFile));
	    String line = "";
	    
	    while ((line = br.readLine()) != null) {
	    	String[] array = line.split(" ");
	    	if(array.length > 1) {
		    		if("0".equals(array[0])) {
		    			if(array[1].matches("[A-Za-z]+")) {
		    				System.out.print("Level : " +array[0]+ " Tag : " + array[1]);
		    				if(zeroTag.contains(array[1]))System.out.println(); else System.out.println(" \nTag Invalid");
		    			} else { 
		    				System.out.print("Level : " +array[0]+ " Tag : " + array[2]);
		    				if(zeroTag.contains(array[2]))System.out.println(); else System.out.println(" \nTag Invalid");
		    			}
		    		} else {
		    			System.out.print("Level:" + array[0]+ " Tag:" + array[1]);
		    			if(genTag.contains(array[1]))System.out.println(); else System.out.println(" \nTag Invalid");
		    		}
		    }
	  
	    }
	}
	
	/**
	 * create object for the class
	 * call parse method
	 */
    public static void main(String[] args) throws IOException {
    	FileRead fileRead = new FileRead();
    	fileRead.parse();	 
    }
	
}
