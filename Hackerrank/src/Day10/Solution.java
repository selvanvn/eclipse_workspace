package Day10;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        
        System.out.println(Integer.toBinaryString(n));
        char[] c=Integer.toBinaryString(n).toCharArray();
        int count = 0;
        		int max = 1;
        for(char b:c)
        {
        //System.out.println("here");
        	if(b=='1')
        	{
        		count++;
        		//System.out.println(count);
        		//break;
        	}
        	else
        	{
        		count=0;
        	}
        	if(max < count)
        		{
        		max = count;
        		//System.out.println(max);
        		
        		}
      
        	        }
       System.out.println(max);
        }
}
