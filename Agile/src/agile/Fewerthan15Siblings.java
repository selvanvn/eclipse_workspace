package agile;

import java.util.List;

public class Fewerthan15Siblings {
	void compare6(List<Family> families_list) {
    	
		for (Family fam : families_list) {
			String fid=fam.getId();
			String child_id=fam.getChildId();
			String children[]=child_id.split("\\s+");
			//split child id's at spaces
			int counter=-1;
			for(String temp: children){
				if(temp != null && temp != ""){
					counter++; 
				}
			}
			if(counter>=15){
				
				 System.out.println("ERROR: FAMILY: US15: Number of siblings in family " +fid+ " is "+counter+" which is more than or equal to 15" );
					
			}else{
				//number of siblings is less than 15
			}
				
			}
			
		}
}
