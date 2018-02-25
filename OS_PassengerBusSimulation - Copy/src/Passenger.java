

public class Passenger {
	private int passengerId;

	/** The time needed to process this passenger. */
	private int processingTime;

	/** The time this passenger arrives. */
	private int arrivalTime;

	/** The maximum time to process a passenger. */
	private int maxProcessingTime;
	
	public Passenger(){
		//this.arrivalTime = arrivalTime;
	    passengerId = idNum++;
	}

	/** The sequence number for passengers. */
	private static int idNum = 0;
	
	public int getArrivalTime() {
	    return arrivalTime;
	}

	  /** Get the processing time.
	      @return The processing time */
	public int getProcessingTime() {
	    return processingTime;
	}

	  /** Get the passenger ID.
	      @return The passenger ID */
	public int getId() {
	    return passengerId;
	}
}
