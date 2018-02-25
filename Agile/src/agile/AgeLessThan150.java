package agile;

import java.util.List;

public class AgeLessThan150 {
	
	void ageCheck(List<Individual> individuals_list){
		for (Individual indv : individuals_list) {
			if(indv.getAge()>=150){
				System.out.println("ERROR : INDIVIDUAL : US07 :"+indv.getName()+" is older than 150 years");
			}
		}
		System.out.println("INDIVIDUAL : US07 : Age check of all individuals completed");
	}
}
