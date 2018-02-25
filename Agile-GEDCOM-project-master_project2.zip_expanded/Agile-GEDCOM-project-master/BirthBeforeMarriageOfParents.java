package agile;


import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.HashMap;
import java.util.List;

public class BirthBeforeMarriageOfParents {
	
	public HashMap<String, Object> hm = new HashMap<String, Object>();
	public parser var= new parser();
	
	void checkChildBirth(List<Individual> individuals_list,List<Family> families_list){
		for (Individual induvidual : individuals_list) {

			hm.put(induvidual.getId(), induvidual);
			hm.put(induvidual.getBirthDate(), induvidual);
		}

		for (Family fam : families_list) {
			String child_id=fam.getChildId();
			String children[]=child_id.split("\\s+");
			//split child id's at spaces
			
			String WeddingDate=fam.getWeddingDate();
			String DivorceDate=fam.getDivorceDate();
			
			for(String temp: children){
				Individual c=(Individual) hm.get(temp);
		
				if (c!= null) {
					SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
			        
			        LocalDate locBDate=null;
			        LocalDate locWDate=null;
			        LocalDate locDDate=null;
			        String Bdate=null;
			        
			        Bdate=c.getBirthDate();
			        if(!"null".equals(Bdate))
				        {
				        	
				        	locBDate = var.convertToLocalDate(Bdate);
				        }
			        
			       
			        locWDate = var.convertToLocalDate(WeddingDate);
			        if(DivorceDate!=null)
			        	 {
				        
			        	 locDDate = var.convertToLocalDate(DivorceDate);
			        	 locDDate.plusMonths(9);
			        	 }
				       
			        
			        
			        if(locBDate!=null && locWDate!=null ){
			        	if (locBDate.isBefore(locWDate))
			        	{
			        	System.out.println("ERROR: US08: FAMILY: CHILD " +temp+" Born before marriage of parents"+" Marriage date:"+WeddingDate+" Birthdate:"+Bdate);
			            }
			        	if(locBDate!=null && locDDate!=null)
			        	{
				        	if (locBDate.isAfter(locWDate))
				        	{
				        		System.out.println("ERROR: US08: FAMILY: CHILD " +temp+" Born 9 months after divorce of parents"+" Divorce date:"+DivorceDate+" Birthdate:"+Bdate);
				        	}
			        		
			        	}
			        
			        }
			        else
			        {
			        	//System.out.printf("\n No children in family:"+fam.getId());
			
			        }
			}
		}
			
	}
		System.out.printf("FAMILY :US08: Birth before marriage of parents check completed\n");

	}
	
}
