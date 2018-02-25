package agile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MarriageBeforeDivorce {

	    void compare3(List<Family> families_list) {
	    	
			for (Family fam : families_list) {
				String fid=fam.getId();
				//String husband_id = fam.getHusbandId();
				//String wife_id = fam.getWifeId();
				
				
				if (fid != null) {
	    	SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
	    	Date marriageDate=null;
	        Date divorceDate=null;
	        String weddingdate=fam.getWeddingDate();
			String divorcedate=fam.getDivorceDate();
			
	    	try{
		        if(weddingdate!=null)
		        marriageDate = format.parse(weddingdate);
		        } catch (ParseException e) {
		        	e.printStackTrace();
		        }
	        try{
		        if(divorcedate!=null)
		        divorceDate = format.parse(divorcedate);
		        } catch (ParseException e) {
		        	e.printStackTrace();
		        }
	        if(divorcedate!=null){
	        if (marriageDate.compareTo(divorceDate) == 1) {
	            System.out.println("ERROR: FAMILY: US04: Marriage date: "+weddingdate+" in family " +fid+ " occurs after divorce date:" +divorcedate);
	
	        } else {
	            //MarriageBeforeDivorce checked
	        }
	        } else{
	        	//no divorce to be checked
	        }
				}
			}
	    }
}
			

	

