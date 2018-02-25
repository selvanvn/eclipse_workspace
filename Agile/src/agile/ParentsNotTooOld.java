package agile;


import java.util.HashMap;
import java.util.List;
import java.util.HashMap;
public class ParentsNotTooOld {
		public HashMap<String, Object> hm = new HashMap<String, Object>();
		public parser var= new parser();
		
	    void compare5(List<Individual> individuals_list,List<Family> families_list) 
	    {
	    	for (Individual induvidual : individuals_list) {

				hm.put(induvidual.getId(), induvidual);
				hm.put(Integer.toString(induvidual.getAge()), induvidual);
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
						int f_age = Integer.valueOf(i.getAge());
						int c_age = Integer.valueOf(c.getAge());
						 if(!"null".equals(c_age)){
							 if(c_age+80>=f_age){
								 //Father not too old
								
							 }
							 else{
								 System.out.println("ERROR: FAMILY: US12:  Father " +husband_id+ " of age " +f_age+ " is too old for child " +temp+ " of age " +c_age);
								 
							 }
						 }
					}
					i = (Individual) hm.get(wife_id);
					c = (Individual) hm.get(temp);
					if (c!= null && i!=null) {
						int m_age = Integer.valueOf(i.getAge());
						int c_age = Integer.valueOf(c.getAge());
						 if(!"null".equals(c_age)){
							 if(c_age+60>=m_age){
								 //Mother not too old
								 
							 }
							 else{
								 System.out.println("ERROR: FAMILY: US12: Mother " +wife_id+ " of age " +m_age+ " is too old for child " +temp+ " of age " +c_age);
								 
							 }
						 }
					}
						
	   
	    }}
	}
}
