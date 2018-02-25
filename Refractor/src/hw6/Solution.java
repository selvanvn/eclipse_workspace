package hw6;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class Solution {
	public String date_diff_years(LocalDate dt1,LocalDate dt2)
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
	public String date_diff_months(LocalDate dt1,LocalDate dt2)
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
	public String date_diff_days(LocalDate dt1,LocalDate dt2)
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
	//LocalDate target1 = LocalDate.now();
	public static void main(String args[])
	{
		LocalDate target1 = LocalDate.of(2000, 01, 01); //(2000-01-01);
		LocalDate target2 = LocalDate.of(2016, 10, 13);
		Solution s= new Solution();
		System.out.println(s.date_diff_days(target1, target2));
		System.out.println(s.date_diff_months(target1, target2));
		System.out.println(s.date_diff_years(target1, target2));
		
	}
}
