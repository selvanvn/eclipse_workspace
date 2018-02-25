
public class Bus_Passenger {
	String busNo;
	int currentStop, travelPath, waitCounter;
	Boolean isBoarding, isTravelling, isWaiting;
	int total_Stops= 14; //since bus starts from 0
	int travel_time=300 ;//in seconds
	public Bus_Passenger(String busNo){
		this.busNo = busNo;
		this.isWaiting = false;
		this.isBoarding = false;
		this.isTravelling = false;
	}
	
	public String getbusNo(){
		return busNo;
	}
	
	public void setNextBusStopNumber(){
		if(currentStop == total_Stops){
			currentStop = 0;
			return;
		}
		currentStop = currentStop + 1;
	}
	
	public int getNextBusStopNumber(){
		if(currentStop == total_Stops){
			return 0;
		}
		return (currentStop+1);
	}
	
	public void setCurrentBusStopNumber(int currentStop){
		this.currentStop = currentStop;
	}
	
	public int getCurrentBusStopNumber(){
		return currentStop;
	}
	
	public void setIsOnBoarding(Boolean isOnBoarding){
		this.isBoarding = isOnBoarding;
	}
	
	public Boolean getIsOnBoarding(){
		return isBoarding;
	}
	
	public void setIsTravelling(Boolean isTravelling){
		this.isTravelling = isTravelling;
	}
	
	public Boolean getIsTravelling(){
		return isTravelling;
	}
	
	public Boolean updateTravellingCounter(){
		travelPath = travelPath + 1;
		if (travelPath == travel_time){
			isTravelling = false;
			travelPath = 0;
		}
		return isTravelling; 
	}
	
	public int getTravellingCounter(){
		return travelPath;
	}
	
	public void setIsWaiting(Boolean isWaiting){
		this.isWaiting = isWaiting;
	}
	
	public Boolean getIsWaiting(){
		return isWaiting;
	}
	
	public void updateWaitCounter(){
		waitCounter = waitCounter + 1;
	}
	
	public int getWaitCounter(){
		return waitCounter;
	}
	
	public void setWaitCounter(int i){
		waitCounter = i;
	}

}
