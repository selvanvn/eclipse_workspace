package hw6_Phase2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Solution2 {
		public String date_diff(LocalDate dt1,LocalDate dt2,int Unit)
		{
			if(Unit==3)
			{
			if(dt1.isAfter(dt2))
			{
				return("There are "+ChronoUnit.YEARS.between(dt2,dt1)+" years between "+dt1+" and "+dt2);
			}
			else
			{
				return("There are "+ChronoUnit.YEARS.between(dt1,dt2)+" years between "+dt1+" and "+dt2);	
			}
			}
			else if(Unit==2)
			{
				if(dt1.isAfter(dt2))
				{
					return("There are "+ChronoUnit.MONTHS.between(dt2, dt1) +" months between "+dt1+" and "+dt2);
				}
				else
				{
					return("There are "+ChronoUnit.MONTHS.between(dt1, dt2)+" months between "+dt1+" and "+dt2);	
				}
			}
			else if(Unit==1)
			{
				if(dt1.isAfter(dt2))
				{
					return("There are "+ChronoUnit.DAYS.between(dt2, dt1)+" days between "+dt1+" and "+dt2);
				}
				else
				{
					return("There are "+ChronoUnit.DAYS.between(dt1, dt2)+" days between "+dt1+" and "+dt2);	
				}
			}
			else
			{
				return("Enter Valid Unit");
			}
		}
				//LocalDate target1 = LocalDate.now();
		public static void main(String args[])
		{
			LocalDate target1 = LocalDate.of(2000, 01, 01); //(2000-01-01);
			LocalDate target2 = LocalDate.of(2016, 10, 13);
			Solution2 s= new Solution2();
			System.out.println(s.date_diff(target1, target2, 1));
			System.out.println(s.date_diff(target1, target2, 2));
			System.out.println(s.date_diff(target1, target2, 3));

		}
	}
