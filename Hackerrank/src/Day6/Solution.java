package Day6;

import java.lang.reflect.Array;
import java.util.Scanner;

public class Solution {
	
	String St;
	public String getSring()
	{
		return this.St;
	}
	public void setString(String newS)
	{
	this.St= newS;
	}
public static void main(String args[])
{
	Solution  S = new Solution();
	Scanner scan = new Scanner(System.in);
	int t=scan.nextInt();
	scan.nextLine();
	String myString;
	//scan.skip("[\r\n]+");
	for(int j=0;j<t;j++)
	{
		myString=scan.nextLine();
		char[] myCharArray= myString.toCharArray();
		//System.out.println(Array.getChar(myCharArray, 2));
		for(int i = 0; i < myCharArray.length; i+=2){
			
		    // Print each sequential character on the same line
				System.out.print(myCharArray[i]);
		}
		System.out.print(" ");
				for(int k=1; k < myCharArray.length; k+=2)
				{
			System.out.print(myCharArray[k]);	
			}
				System.out.println("");
	}
}
	
	}

