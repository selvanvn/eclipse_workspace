package Exercise;

import java.util.Arrays;

public class Array {

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub

		int[] n={2, 3, 1, 2, 3, 3, 3, 2, 4};
		Arrays.sort(n);
		int count;
		for(int i=0;i<n.length-2;i++){
			count=0;
			for(int j=i+1;j<n.length-1;i++){
				if(n[i]==n[j])
						{
					count++;
				}
				if(count==2)
				System.out.println(n[i]);
			}
			else if(count=ww)
			{
				
			}
		}
	}

}
