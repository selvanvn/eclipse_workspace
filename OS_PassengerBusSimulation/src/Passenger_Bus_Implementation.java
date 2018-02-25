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
				
			}
			else if(i==1){
				bus[i].setCurrentBusStopNumber(3);
				
			}
			else if(i==2){
				bus[i].setCurrentBusStopNumber(6);
				
			}
			else if(i==3){
				bus[i].setCurrentBusStopNumber(9);
				
			}
			else if(i==4){
				bus[i].setCurrentBusStopNumber(12);
				
			}
			bus[i].setIsTravelling(false);
			
			bus[i].setIsOnBoarding(true);
		
			bus[i].setIsWaiting(false);
			
		}
	}
	
	public void updateBusPosition(int clock, Bus_Stop_List allStops){
		
		for(int i=0; i<bus.length; i++){
			
			if(bus[i].isTravelling){
				
				if(bus[i].updateTravellingCounter()){
					
				}
				else{
					
					if(checkIfThereIsABusInNextStop(i)){
						
						bus[i].setNextBusStopNumber();
						bus[i].setIsWaiting(false);
						bus[i].setIsOnBoarding(true);
						bus[i].setIsTravelling(false);
						continue;
					}
					else{
						
						bus[i].setNextBusStopNumber();
					
						Bus_Passenger_Queue queueInstance = allStops.getStopHandle(bus[i].getCurrentBusStopNumber());
						
						if(queueInstance.size() == 0){
							
							bus[i].setIsTravelling(true);							
							bus[i].setIsOnBoarding(false);
							bus[i].setIsWaiting(false);
						}
						else{
							
							bus[i].setIsOnBoarding(true);							
							bus[i].setIsTravelling(false);
							bus[i].setIsWaiting(false);
							queueInstance.removePassenger();
							
						}
					}
				}
			}
			
			else if(bus[i].isBoarding){
				
				Bus_Passenger_Queue queueInstance = allStops.getStopHandle(bus[i].getCurrentBusStopNumber());
				
				if(queueInstance.size() == 0){
					
					bus[i].setIsTravelling(true);
					
					bus[i].setIsOnBoarding(false);
				}
				else{
					
					queueInstance.removePassenger();
					
					boardedPassengerCounter += 1;
					boardedPassengerLog = boardedPassengerLog + " " + (i+1) + ":" + boardedPassengerCounter; 
				}
			}
			
			else if(bus[i].isWaiting){
				
				if(checkIfThereIsABusInNextStop(i)){
					
					bus[i].updateWaitCounter();
					
				}
				else {
					if(checkIfThereIsAnyOtherBusWithGreaterCounter(i)){
						bus[i].updateWaitCounter();
					}
					else{
						bus[i].setIsTravelling(false);
						bus[i].setIsOnBoarding(true);
						bus[i].setIsWaiting(false);
						bus[i].setWaitCounter(0);						
						bus[i].setNextBusStopNumber();
						
					}
				}
			}
			
			log = log + " " + bus[i].getCurrentBusStopNumber(); 
		}
	}
	
	public Boolean checkIfThereIsAnyOtherBusWithGreaterCounter(int i){
		
		Boolean flag = false;
		for(int j=0;j<bus.length;j++){
			if(bus[j].getCurrentBusStopNumber() == bus[i].getCurrentBusStopNumber()){
				if(bus[j].getWaitCounter() > bus[i].getWaitCounter()){
					flag = true;
					
				}
			}
		}
		
		return flag;
	}
	
	public Boolean checkIfThereIsABusInNextStop(int i){
		
		Boolean flag = false;
		for(int j=0;j<bus.length;j++){
			if(bus[i].getNextBusStopNumber() == bus[j].getCurrentBusStopNumber()){
				if(bus[j].getIsOnBoarding()){
					bus[j].setIsOnBoarding(false);
					bus[j].setIsTravelling(true);
					bus[j].setIsWaiting(false);
					flag = true;
					
				}
			}
		}
		
		return flag;
	}
	
	public void clearLog(){
		log = "";
		boardedPassengerLog = "";
	}
	
	public String getBoardedPassengerLog(){
		return boardedPassengerLog;
	}

}
