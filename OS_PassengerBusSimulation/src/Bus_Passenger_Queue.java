import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Bus_Passenger_Queue {
	
	private Queue<Passenger> Passqueue;
	private int numSer;
	
	public Bus_Passenger_Queue(String queueName) {
		numSer = 0;
	    totalWait = 0;
	    this.queueName = queueName;
	    Passqueue = new LinkedList < Passenger > ();
	}
	
	private Boolean boardingIntervalFlag = false;

	private int totalWait;

	
	private String queueName;

	private double arrivalRate;
	
	
	public int getnumSer() {
	  return numSer;
	}
	
	
	public int getTotalWait() {
	  return totalWait;
	}
	
	public String getQueueName() {
	  return queueName;
	}
	
	public void setArrivalRate(double arrivalRate) {
	  this.arrivalRate = arrivalRate;
	}
	
	public double getArrivalRate() {
		  return arrivalRate;
	}
	
	public boolean isEmpty() {
	  return Passqueue.isEmpty();
	}
	
	
	public int size() {
	  return Passqueue.size();
	}
	
	public  int checkforNewPassenger(int clock){
		int totatPassengersToAdd = new Random().nextInt(3)+1;
		for(int i=0; i<totatPassengersToAdd; i++){
			Passqueue.add(new Passenger());
		}
		return totatPassengersToAdd;	
		
	}
	
	
	
	public void removePassenger(){
		if(boardingIntervalFlag)
			Passqueue.remove();
		boardingIntervalFlag = !boardingIntervalFlag;		
	}
	
}
