package agile;

import java.time.LocalDate;
import java.util.HashMap;

public class ListOrphans {
	public HashMap<String, Object> hm = new HashMap<String, Object>();
	public parser var= new parser();
	
	void FindOrphans(parser p){
		for (Individual induvidual : p.individuals_list) {

			hm.put(induvidual.getId(), induvidual);
			hm.put(induvidual.getDeathDate(),induvidual);
			hm.put(Integer.toString(induvidual.getAge()),induvidual);
		}

		for (Family fam : p.families_list) {
			String child_id=fam.getChildId();
			String children[]=child_id.split("\\s+");
			
			String husband_id=fam.getHusbandId();
			String wife_id=fam.getWifeId();
			
			Individual h = (Individual) hm.get(husband_id);
			Individual w = (Individual) hm.get(wife_id);
			
			String father_deathdate= h.getDeathDate();
			String mother_deathdate= w.getDeathDate();
			
			LocalDate FatherDDate=null;
	        LocalDate MotherDDate=null;
	        LocalDate Today=LocalDate.now();
	       

			        if(father_deathdate!=null)
			        	   	FatherDDate = var.convertToLocalDate(father_deathdate);
				     
			        
			        if(mother_deathdate!=null)
				           	MotherDDate = var.convertToLocalDate(mother_deathdate);
			   
			        	 
				       
			        
			        
			        if(father_deathdate!=null && FatherDDate.isBefore(Today) && mother_deathdate!=null && MotherDDate.isBefore(Today)){
			        	for(String child:children){
			        		if(child!=" " && child!=null){
			        		Individual c= (Individual) hm.get(child);
			        		int child_age=-1;
			        		if (c!= null)
			        			child_age =	c.getAge();
			        		if(child_age<18 && child_age>=0){
			        			System.out.println("US33: FAMILY: Child "+child+" in family "+fam.getId()+" is an orphan");
			        }
			       }
				}
			}
			
			
		
		
	}
		System.out.printf("FAMILY :US33: Listing Orphans completed\n");

	}
}
