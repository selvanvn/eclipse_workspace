import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Bus_Passenger_Queue {
	
	private Queue<Passenger> queue;
	private int numServed;
	
	public Bus_Passenger_Queue(String queueName) {
		numServed = 0;
	    totalWait = 0;
	    this.queueName = queueName;
	    queue = new LinkedList < Passenger > ();
	}
	
	private Boolean boardingIntervalFlag = false;

	/** The total time passengers were waiting. */
	private int totalWait;

	/** The name of this queue. */
	private String queueName;

	/** The average arrival rate. */
	private double arrivalRate;
	
	/** Return the number of passengers served
    @return The number of passengers served
	 */
	public int getNumServed() {
	  return numServed;
	}
	
	/** Return the total wait time
	    @return The total wait time
	 */
	public int getTotalWait() {
	  return totalWait;
	}
	
	/** Return the queue name
	    @return - The queue name
	 */
	public String getQueueName() {
	  return queueName;
	}
	
	/** Set the arrival rate
	    @param arrivalRate the value to set
	 */
	public void setArrivalRate(double arrivalRate) {
	  this.arrivalRate = arrivalRate;
	}
	
	public double getArrivalRate() {
		  return arrivalRate;
	}
	
	/** Determine if the passenger queue is empty
	        @return true if the passenger queue is empty
	 */
	public boolean isEmpty() {
	  return queue.isEmpty();
	}
	
	/** Determine the size of the passenger queue
	    @return the size of the passenger queue
	 */
	public int size() {
	  return queue.size();
	}
	
	public  int checkforNewPassenger(int clock){
		int totatPassengersToAdd = new Random().nextInt(3)+1;
		for(int i=0; i<totatPassengersToAdd; i++){
			queue.add(new Passenger());
		}
		return totatPassengersToAdd;	
		
	}
	
	
	
	public void removePassenger(){
		if(boardingIntervalFlag)
			queue.remove();
		boardingIntervalFlag = !boardingIntervalFlag;		
	}
	
}
