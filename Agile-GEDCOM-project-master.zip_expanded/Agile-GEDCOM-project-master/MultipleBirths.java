package agile;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class MultipleBirths {
//Multiple births <= 5 = No more than five siblings should be born at the same time
	public HashMap<String, Object> hm = new HashMap<String, Object>();
	public parser var= new parser();
	
	
	void CheckBirths(List<Individual> individuals_list,List<Family> families_list){
		for (Individual induvidual : individuals_list) {

			hm.put(induvidual.getId(), induvidual);
			hm.put(induvidual.getBirthDate(), induvidual);
		}

		for (Family fam : families_list) {
			String child_id=fam.getChildId();
			String children[]=child_id.split("\\s+");
			//split child id's at spaces
			
			for(String temp: children){
				Individual c=(Individual) hm.get(temp);
				int counter=1;
				if(c!=null){
					String Bdate=null;
			        LocalDate locBDate=null;
					Bdate=c.getBirthDate();
			        if(!"null".equals(Bdate))
				        {
				        	
				        	locBDate = var.convertToLocalDate(Bdate);
				        }
			        for(String temp1: children){
			        	Individual c2=(Individual) hm.get(temp1);
						if(c2!=null){
			        	String B2date=null;
				        LocalDate locB2Date=null;
						B2date=c2.getBirthDate();
				        if(!"null".equals(B2date))
					        {
					        	
					        	locB2Date = var.convertToLocalDate(B2date);
					        }
			        	if(locB2Date.equals(locBDate)&& !temp.equals(temp1))
			        		counter++;
			        	if(counter>5)
			        		break;
			        }
				}
			        if(counter>5)
			        {
			        	System.out.println("ERROR:US14:FAMILY: Multiple births on Date:"+Bdate+" in family: "+fam.getId());
			        	break;
			        }
			        
			        
			        
			}
			
			}
			
		}
		System.out.println("FAMILY :US14:Multiple births check completed");
	}
}
