package problem3;

public class Process_Round_Robin {
	int PID=0;
	static double alpha = 0;
	static int nextPID = 0;
	long burstTime=0;
	long IOProcessingTime = 0;
	long initBurst=0;
	long cpuExecutingTime = 0;
	long randExecutingTime = 0;
	long lifeTime = 0;
	long ioQueueWaitTime = 0;
	long cpuQueueWaitTime = 0;
	long predictedBurstTime = 0;
	long timeSlice = 0;
	long currentTimeSlice = 0;
	
	boolean started=false;
    boolean cpuTotalBurstFinished=false;
    boolean cpuExecutingTimeFinished=false;
    boolean currentTimeSliceFinished=false;
    boolean ioFinished=false;
    boolean arrived=false;
    boolean active = false;
	
	Process_Round_Robin(long burst, long cpuExecutingTime, long randExecutingTime, long timeSlice){
			nextPID++;
			if(nextPID == 11)
				nextPID = 1;
			PID = nextPID;
			this.randExecutingTime = cpuExecutingTime;
			this.burstTime = burst;
			initBurst = this.burstTime;
			this.cpuExecutingTime = randExecutingTime;
			this.predictedBurstTime = randExecutingTime;
			this.timeSlice = timeSlice;
			this.currentTimeSlice = timeSlice;
		    }

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}


	public long getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}
	
	public long getCpuExecutingTime() {
		return cpuExecutingTime;
	}

	public void setCpuExecutingTime(long randExecutingTime2) {
		this.cpuExecutingTime = randExecutingTime2;
	}

	public long getIOProcessingTime() {
		return IOProcessingTime;
	}

	public void setIOProcessingTime(int iOProcessingTime) {
		IOProcessingTime = iOProcessingTime;
	}

	public synchronized boolean cpuExecuting(long currentTime){	    
		active = true;
		burstTime--;
		cpuExecutingTime--;
		lifeTime++;
		currentTimeSlice--;
		
		if( burstTime == 0)
		    cpuTotalBurstFinished = true;
		
		if(cpuExecutingTime == 0)
			cpuExecutingTimeFinished = true;
		
		if(currentTimeSlice == 0)
			currentTimeSliceFinished = true;
		
		if (cpuTotalBurstFinished == true || cpuExecutingTimeFinished == true)
			return false;
		else
			return true;
	    }
	
	public boolean isCurrentTimeSliceFinished() {
		return currentTimeSliceFinished;
	}

	public void setCurrentTimeSliceFinished(boolean currentTimeSliceFinished) {
		this.currentTimeSliceFinished = currentTimeSliceFinished;
	}

	public long getCurrentTimeSlice() {
		return currentTimeSlice;
	}

	public void setCurrentTimeSlice(long currentTimeSlice) {
		this.currentTimeSlice = currentTimeSlice;
	}

	public synchronized void cpuWaiting(long timeNow){
		active = false;
		lifeTime++;
		cpuQueueWaitTime++;
	    }
	
	public synchronized void ioWaiting(long timeNow){
		lifeTime++;
		ioQueueWaitTime++;
	}
	
	public synchronized void ioExecuting(long currentTime){	    
		IOProcessingTime--;
		lifeTime++;
		
		if( IOProcessingTime == 0)
		    ioFinished = true;
	    }
	
	public boolean isIOfinished() {
		return ioFinished;
	}

	public void setIOfinished(boolean iOfinished) {
		ioFinished = iOfinished;
	}

	public boolean isCPUTotalBurstTimeFinished() {
		return cpuTotalBurstFinished;
	}
	
	public boolean isCPUExecutingTimeFinished() {
		return cpuExecutingTimeFinished;
	}
	
	public boolean isIOFinished() {
		return ioFinished;
	}
	
	public long calculatePredictedBurstTime(){
		double tempCpuExecutingTime = (double)cpuExecutingTime;
		double tempPredictedBurstTime = (double)predictedBurstTime;
		double result;
		result = alpha * tempCpuExecutingTime + (1 - alpha) * tempPredictedBurstTime;
		predictedBurstTime = (long)result;
		return (long)result;
	}

	public long getPredictedBurstTime() {
		return predictedBurstTime;
	}

	public void setPredictedBurstTime(long predictedBurstTime) {
		this.predictedBurstTime = predictedBurstTime;
	}
}
