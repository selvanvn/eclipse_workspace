package Exercise;

import java.util.Scanner;

public class Palindrome {
	
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		String s = sc.nextLine();
		char[] c=s.toCharArray();
		int i1=0,i2=c.length-1;
		System.out.println(c.length);
		while(i2>i1)
		{
			if(c[i1]!=c[i2])
			{
				System.out.println("N");
				break;
				
			}
			else{
				i1++;
				i2--;
			}
			
		}
		System.out.println("palindrome");
	}

}
