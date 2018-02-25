

public class Passenger {
	private int passengerID;

	
	private int ProcessTime;


	private int arrivalTime;


	private int maxTime;
	
	public Passenger(){
		
	    passengerID = idNum++;
	}

	
	private static int idNum = 0;
	
	public int getArrTime() {
	    return arrivalTime;
	}


	public int getProcTime() {
	    return ProcessTime;
	}

	
	public int getId() {
	    return passengerID;
	}
}
