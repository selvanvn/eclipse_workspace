import org.apache.log4j.Logger;

public class Passenger_Bus_Implementation {
	
	private Bus_Passenger[] bus;
	Bus_Passenger_Queue stopQueue;
	String log = "";
	String boardedPassengerLog = "";
	int boardedPassengerCounter = 0;
	final static Logger logger = Logger.getLogger(Problem_Simulation.class.toString());
	
	public void createBus(int numberOfBuses){
		bus = new Bus_Passenger[numberOfBuses];
		for(int i=0; i<numberOfBuses; i++){
			bus[i] = new Bus_Passenger("bus"+i);
			if(i==0){
				bus[i].setCurrentBusStopNumber(0);
				logger.info("for bus number "+i+" stop no. set to: 0");
			}
			else if(i==1){
				bus[i].setCurrentBusStopNumber(3);
				logger.info("for bus number "+i+" stop no. set to: 3");
			}
			else if(i==2){
				bus[i].setCurrentBusStopNumber(6);
				logger.info("for bus number "+i+" stop no. set to: 6");
			}
			else if(i==3){
				bus[i].setCurrentBusStopNumber(9);
				logger.info("for bus number "+i+" stop no. set to: 9");
			}
			else if(i==4){
				bus[i].setCurrentBusStopNumber(12);
				logger.info("for bus number "+i+" stop no. set to: 12");
			}
			bus[i].setIsTravelling(false);
			logger.info("isTravelling set to false while initializing the buses");
			bus[i].setIsOnBoarding(true);
			logger.info("isOnBoarding set to true while initializing the buses");
			bus[i].setIsWaiting(false);
			logger.info("isWaiting set to false while initializing the buses");
		}
	}
	
	public void updateBusPosition(int clock, Bus_Stop_List allStops){
		logger.info("Update Bus position called at time: " + clock);
		for(int i=0; i<bus.length; i++){
			logger.info("-----------------------------");
			logger.info("Processing for bus number: " + i);
			logger.info("Checking if isTravelling is true "+ bus[i].isTravelling);
			if(bus[i].isTravelling){
				logger.info("calling updateTravellingCounter");
				if(bus[i].updateTravellingCounter()){
					logger.info("update travelling counter returned true");
					logger.info("travelling counter number: "+ bus[i].getTravellingCounter());
				}
				else{
					logger.info("update travelling counter returned false");
					logger.info("check if there is already a bus standing at next stop");
					if(checkIfThereIsABusInNextStop(i)){
						logger.info("there is already a bus standing at next stop so setting isWaiting true");
						bus[i].setNextBusStopNumber();
						bus[i].setIsWaiting(false);
						bus[i].setIsOnBoarding(true);
						bus[i].setIsTravelling(false);
						continue;
					}
					else{
						logger.info("setting next bus stop number for bus number: "+ i);
						bus[i].setNextBusStopNumber();
						logger.info("Next bus stop number set {"+bus[i].getCurrentBusStopNumber()+"}");
						logger.info("getting instance for the current bus stop");
						Bus_Passenger_Queue queueInstance = allStops.getStopHandle(bus[i].getCurrentBusStopNumber());
						logger.info("checking for if the queue size is zero or not");
						if(queueInstance.size() == 0){
							logger.info("queue size is zero for current bus stop");
							logger.info("setIsTravelling true");
							bus[i].setIsTravelling(true);
							logger.info("setIsOnBoarding false");
							bus[i].setIsOnBoarding(false);
							bus[i].setIsWaiting(false);
						}
						else{
							logger.info("queue size is not empty");
							logger.info("setting onBoading true");
							bus[i].setIsOnBoarding(true);
							logger.info("setting isTravelling false");
							bus[i].setIsTravelling(false);
							bus[i].setIsWaiting(false);
							queueInstance.removePassenger();
							logger.info("removed a passenger");
							logger.info("current queue size is: "+ queueInstance.size());
						}
					}
				}
			}
			
			else if(bus[i].isOnBoarding){
				logger.info("isTravelling has returned false so checking if onBoarding is true");
				logger.info("OnBoarding is true");
				logger.info("getting instance for the current bus stop :"+bus[i].getCurrentBusStopNumber());
				Bus_Passenger_Queue queueInstance = allStops.getStopHandle(bus[i].getCurrentBusStopNumber());
				logger.info("checking if queue size is zero for current bus stop");
				if(queueInstance.size() == 0){
					logger.info("queue size is zero for current bus stop");
					logger.info("setIsTravelling true");
					bus[i].setIsTravelling(true);
					logger.info("setIsOnBoarding false");
					bus[i].setIsOnBoarding(false);
				}
				else{
					logger.info("queue size is not zero for current bus stop");
					logger.info("removing passenger from queue");
					queueInstance.removePassenger();
					logger.info("removed a passenger from queue");
					logger.info("current queue size is: "+ queueInstance.size());
					boardedPassengerCounter += 1;
					boardedPassengerLog = boardedPassengerLog + " " + (i+1) + ":" + boardedPassengerCounter; 
				}
			}
			
			else if(bus[i].isWaiting){
				logger.info("isTravelling & isOnBoarding has returned false so checking if isWaiting is true");
				logger.info("isWaiting is true");
				if(checkIfThereIsABusInNextStop(i)){
					logger.info("updating waiting counter");
					bus[i].updateWaitingCounter();
					logger.info("waiting counter no.: "+ bus[i].getWaitingCounter());
				}
				else {
					if(checkIfThereIsAnyOtherBusWithGreaterCounter(i)){
						bus[i].updateWaitingCounter();
					}
					else{
						bus[i].setIsTravelling(false);
						bus[i].setIsOnBoarding(true);
						bus[i].setIsWaiting(false);
						bus[i].setWaitingCounter(0);
						logger.info("setting next bus stop number for bus number: "+ i);
						bus[i].setNextBusStopNumber();
						logger.info("Next bus stop number set {"+bus[i].getCurrentBusStopNumber()+"}");
					}
				}
			}
			
			log = log + " " + bus[i].getCurrentBusStopNumber(); 
		}
	}
	
	public Boolean checkIfThereIsAnyOtherBusWithGreaterCounter(int i){
		logger.info("checking if there is any other bus which has greater waiting counter");
		Boolean flag = false;
		for(int j=0;j<bus.length;j++){
			if(bus[j].getCurrentBusStopNumber() == bus[i].getCurrentBusStopNumber()){
				if(bus[j].getWaitingCounter() > bus[i].getWaitingCounter()){
					flag = true;
					logger.info("Yes, bus no. "+ j +" has greater waiting counter");
				}
			}
		}
		logger.info("returning " + flag);
		return flag;
	}
	
	public Boolean checkIfThereIsABusInNextStop(int i){
		logger.info("checking if there is any other bus standing in next stop");
		Boolean flag = false;
		for(int j=0;j<bus.length;j++){
			if(bus[i].getNextBusStopNumber() == bus[j].getCurrentBusStopNumber()){
				if(bus[j].getIsOnBoarding()){
					bus[j].setIsOnBoarding(false);
					bus[j].setIsTravelling(true);
					bus[j].setIsWaiting(false);
					flag = true;
					logger.info("Yes, bus no. "+ j +" is standing");
				}
			}
		}
		logger.info("returning " + flag);
		return flag;
	}
	
	public void clearLog(){
		log = "";
		boardedPassengerLog = "";
	}
	
	public String getLog(){
		return log;
	}
	
	public String getBoardedPassengerLog(){
		return boardedPassengerLog;
	}

}
