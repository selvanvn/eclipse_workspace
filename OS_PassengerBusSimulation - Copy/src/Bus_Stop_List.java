import org.apache.log4j.Logger;

public class Bus_Stop_List {
	
	final static Logger logger = Logger.getLogger(Problem_Simulation.class.toString());
	Bus_Passenger_Queue[] stop = new Bus_Passenger_Queue[15];
	String log = "";
	
	public Bus_Stop_List(){
		for(int i=0; i<stop.length; i++)
			stop[i] = new Bus_Passenger_Queue("stop"+i);
	}
	
	public void addPassengers(int clock){
		if (clock % 12 == 0){
			for(int i=0; i<stop.length; i++){
				logger.info("---------------------------");
				logger.info("Adding passengers to queue for stop no: " + i);
				logger.info("Queue size before adding is: " + stop[i].size());
				int totalPassengersAdded = stop[i].checkforNewPassenger(clock);
				logger.info("Total passengers added: " + totalPassengersAdded);
				logger.info("Queue size after adding is: " + stop[i].size());
				log = log + " " + stop[i].size(); 
			}
		}
		else{
			logger.info("we are not adding passengers this time at: "+ clock);
		}
	}
	
	public Bus_Passenger_Queue getStopHandle(int stopNumber){
		return stop[stopNumber];
	}
	
	public int getQueueSize(int i){
		return stop[i].size();
	}
	
	public void clearLog(){
		log = "";
	}
	
	public String allStopsQueueSize(){
		return log;
	}

}
