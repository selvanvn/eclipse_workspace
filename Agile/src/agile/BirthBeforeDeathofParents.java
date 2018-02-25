package agile;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class BirthBeforeDeathofParents {
	public HashMap<String, Object> hm = new HashMap<String, Object>();
	public parser var= new parser();
	
	void compare4(List<Individual> individuals_list,List<Family> families_list){
		for (Individual induvidual : individuals_list) {

			hm.put(induvidual.getId(), induvidual);
			hm.put(induvidual.getBirthDate(), induvidual);
			hm.put(induvidual.getDeathDate(), induvidual);
		}
		for (Family fam : families_list) {
			String child_id=fam.getChildId();
			String children[]=child_id.split("\\s+");
			//split child id's at spaces
			
			String husband_id=fam.getHusbandId();
			String wife_id=fam.getWifeId();
			for(String temp: children){
				Individual i = (Individual) hm.get(husband_id);
				Individual c = (Individual) hm.get(temp);
				if (c!= null && i!=null) {
					
					LocalDate locbirthDate=null;
					LocalDate locdeathDate=null;
			        String Bdate=c.getBirthDate();
			        String Ddate=i.getDeathDate();	
			        if(!"null".equals(Bdate))
			        		locbirthDate = var.convertToLocalDate(Bdate);
			        if(Ddate!=null)
				    {
					     locdeathDate = var.convertToLocalDate(Ddate);
				         locdeathDate.plusMonths(9);
				     }
				    if(locbirthDate!=null && locdeathDate!=null){
				    	if (locbirthDate.isAfter(locdeathDate)){
			        		System.out.println("ERROR: FAMILY: US09:  CHILD " +temp+" Born: " +locbirthDate+ " after 9 months after father " +husband_id+ " death:"+locdeathDate);
				        } else {
			        		//birth before death of father checked
			        	}
			        }
				}
			
				i = (Individual) hm.get(wife_id);
				c = (Individual) hm.get(temp);
					if (c!= null && i!=null) {
						
						LocalDate locbirthDate=null;
						LocalDate locdeathDate=null;
						String Bdate=c.getBirthDate();
						String Ddate=i.getDeathDate();	
				        if(!"null".equals(Bdate))
				        		locbirthDate = var.convertToLocalDate(Bdate);
				        if(Ddate!=null)
					    {
						         locdeathDate = var.convertToLocalDate(Ddate);
					        	 locdeathDate.plusMonths(9);
					    }
					    if(locbirthDate!=null && locdeathDate!=null){
					    	if (locbirthDate.isAfter(locdeathDate)){
				        		System.out.println("ERROR: FAMILY: US09: CHILD " +temp+" Born: " +locbirthDate+ " after mother " +wife_id+ " death:"+locdeathDate);
					        } else {
				        		//birth before death of mother checked
				        	}
				        }
					}
					
					
				}
			}
	}
}
				
