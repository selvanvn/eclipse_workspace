package day12;

class Student extends Person{
	private int[] testScores;

    /*	
    *   Class Constructor
    *   
    *   @param firstName - A string denoting the Person's first name.
    *   @param lastName - A string denoting the Person's last name.
    *   @param id - An integer denoting the Person's ID number.
    *   @param scores - An array of integers denoting the Person's test scores.
    */
    // Write your constructor here
	Student(String fName, String lName, int iden,int[] tScores){
		super(fName, lName, iden);
		this.testScores = tScores;	
	}
    /*	
    *   Method Name: calculate
    *   @return A character denoting the grade.
    */
    // Write your method here
	public char calculate()
	{
		int ave = 0;
		for(int i=0; i<testScores.length; i++){
			ave = ave + testScores[i];
		}
		int per= ave/testScores.length;
		if(per>=90 && per<=100)
		{
			return 'O';
		}
		else if(per>=80 && per<90)
		{
			return 'E';
		}
		else if(per>=70 && per<80)
		{
			return 'A';
		}
		else if(per>=55 && per<70)
		{
			return 'P';
		}
		else if(per>=40 && per<55)
		{
			return 'D';
		}
		else
		{
			return 'T';
		}
		
	}
}