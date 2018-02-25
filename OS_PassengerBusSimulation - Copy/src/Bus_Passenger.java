
public class Bus_Passenger {
	String busName;
	int currentBusStopNumber, travellingCounter, waitingCounter;
	Boolean isOnBoarding, isTravelling, isWaiting;
	
	public Bus_Passenger(String busName){
		this.busName = busName;
		this.isOnBoarding = false;
		this.isTravelling = false;
		this.isWaiting = false;
	}
	
	public String getBusName(){
		return busName;
	}
	
	public void setNextBusStopNumber(){
		if(currentBusStopNumber == 14){
			currentBusStopNumber = 0;
			return;
		}
		currentBusStopNumber = currentBusStopNumber + 1;
	}
	
	public int getNextBusStopNumber(){
		if(currentBusStopNumber == 14){
			return 0;
		}
		return (currentBusStopNumber+1);
	}
	
	public void setCurrentBusStopNumber(int currentBusStopNumber){
		this.currentBusStopNumber = currentBusStopNumber;
	}
	
	public int getCurrentBusStopNumber(){
		return currentBusStopNumber;
	}
	
	public void setIsOnBoarding(Boolean isOnBoarding){
		this.isOnBoarding = isOnBoarding;
	}
	
	public Boolean getIsOnBoarding(){
		return isOnBoarding;
	}
	
	public void setIsTravelling(Boolean isTravelling){
		this.isTravelling = isTravelling;
	}
	
	public Boolean getIsTravelling(){
		return isTravelling;
	}
	
	public Boolean updateTravellingCounter(){
		travellingCounter = travellingCounter + 1;
		if (travellingCounter == 300){
			isTravelling = false;
			travellingCounter = 0;
		}
		return isTravelling; 
	}
	
	public int getTravellingCounter(){
		return travellingCounter;
	}
	
	public void setIsWaiting(Boolean isWaiting){
		this.isWaiting = isWaiting;
	}
	
	public Boolean getIsWaiting(){
		return isWaiting;
	}
	
	public void updateWaitingCounter(){
		waitingCounter = waitingCounter + 1;
	}
	
	public int getWaitingCounter(){
		return waitingCounter;
	}
	
	public void setWaitingCounter(int i){
		waitingCounter = i;
	}

}
