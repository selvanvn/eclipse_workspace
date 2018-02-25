package agile;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;

public class SiblingSpacing {
//Siblings spacing = No more than five siblings should be born at the same time
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
				if(c!=null){
					String Bdate=null;
			        LocalDate locBDate=null;
					Bdate=c.getBirthDate();
			        if(!"null".equals(Bdate))
				        {
				        	
				        	locBDate = var.convertToLocalDate(Bdate);
				        }
				
				for(String temp1:children){
				Individual c1=(Individual) hm.get(temp1);
		
				if (c1!=null) {
				//	SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
			        
			        LocalDate locB2Date=null;
			        
			        String B2date=null;
			        	        
			        B2date=c1.getBirthDate();
			        if(!"null".equals(B2date))
				        {
				        	
			        	locB2Date = var.convertToLocalDate(B2date);
				        }
			        	       
			       if(locBDate!=null && locB2Date!=null ){
			    	   if(locBDate.isBefore(locB2Date)){
			        	if (Period.between(locBDate,locB2Date).getDays()<=1 || Period.between(locBDate,locB2Date).getMonths()>=8 ||Period.between(locBDate,locB2Date).getYears()>=1)
			        	{
			        		//System.out.println("Check completed for"+temp+" and "+temp1+"days between:"+Period.between(locBDate,locB2Date).getDays()+" months between:"+Period.between(locBDate,locB2Date).getMonths());
			            }
			        	else
			        	{
			        		if(Period.between(locBDate,locB2Date).getDays()<=1)
			        		{
			        			System.out.println("ERROR: US13: FAMILY: CHILDREN "+temp+" and "+temp1+" Sibling Spacing is incorrect"+" BirthDate1:"+Bdate+" Birthdate2:"+B2date+" Months in between:"+Period.between(locBDate,locB2Date).getMonths());
			        		}
			        		else{
			        			System.out.println("ERROR: US13: FAMILY: CHILDREN "+temp+" and "+temp1+" Sibling Spacing is incorrect"+" BirthDate1:"+Bdate+" Birthdate2:"+B2date+" Days in between:"+Period.between(locBDate,locB2Date).getDays());
			        		}
			        		
			        	}
			    	   }
			    	   else{
			    		   if (Period.between(locB2Date,locBDate).getDays()<=1 || Period.between(locB2Date,locBDate).getMonths()>=8||Period.between(locB2Date,locBDate).getYears()>=1)
				        	{
				        		//System.out.println("Check completed for"+temp+" and "+temp1+"days between:"+Period.between(locBDate,locB2Date).getDays()+" months between:"+Period.between(locBDate,locB2Date).getMonths());
				            }
				        	else
				        	{
				        		if(Period.between(locB2Date,locBDate).getDays()<=1)
				        		{
				        			System.out.println("ERROR: US13: FAMILY: CHILDREN "+temp+" and "+temp1+" Sibling Spacing is incorrect"+" BirthDate1:"+Bdate+" Birthdate2:"+B2date+" Months in between:"+Period.between(locB2Date,locBDate).getMonths());
				        		}
				        		else{
				        			System.out.println("ERROR: US13: FAMILY: CHILDREN "+temp+" and "+temp1+" Sibling Spacing is incorrect"+" BirthDate1:"+Bdate+" Birthdate2:"+B2date+" Days in between:"+Period.between(locB2Date,locBDate).getDays());
				        		}
				        		
				        	}
			    	   }
			        
			        }
			        else
			        {
			        	//System.out.printf("\n Not enough children:"+fam.getId());
			
			        }
			  	}
			}
	}
		
			}
		}
    System.out.printf("FAMILY :US13:Sibling Spacing check completed\n");
	}
}
