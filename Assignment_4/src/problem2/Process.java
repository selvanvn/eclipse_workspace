package problem2;

public class Process {
	int PID=0;
	static double alpha = 0;
	static int nextPID = 0;
	long burstTime=0;
	long initBurst=0;
	long cpuExecutingTime = 0;
	long randExecutingTime = 0;
	long lifeTime = 0;
	long cpuQueueWaitTime = 0;
	long predictedBurstTime = 0;
	long arrivalTime = 0;
	boolean started=false;
    boolean cpuTotalBurstFinished=false;
    boolean cpuExecutingTimeFinished=false;
    boolean arrived=false;
    boolean active = false;
    long priority = 0;
	
	Process(long arrivalTime, long burstTime, long priority){
			nextPID++;
			PID = nextPID;
			this.burstTime = burstTime;
			initBurst = this.burstTime;
			this.arrivalTime = arrivalTime;
			this.priority = priority;
		    }

	public long getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
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

	public synchronized boolean cpuExecuting(long currentTime){	    
		
		active = true;
		burstTime--;
		cpuExecutingTime--;
		lifeTime++;
		
		if( burstTime == 0)
		    cpuTotalBurstFinished = true;
		
		if(cpuExecutingTime == 0)
			cpuExecutingTimeFinished = true;
		
		if (cpuTotalBurstFinished == true || cpuExecutingTimeFinished == true)
			return false;
		else
			return true;
	    }
	
	public synchronized void cpuWaiting(long timeNow){
		active = false;
		lifeTime++;
		cpuQueueWaitTime++;
	    }

	public boolean isCPUTotalBurstTimeFinished() {
		return cpuTotalBurstFinished;
	}
	
	public boolean isCPUExecutingTimeFinished() {
		return cpuExecutingTimeFinished;
	}
	

	public long getPredictedBurstTime() {
		return predictedBurstTime;
	}

	public void setPredictedBurstTime(long predictedBurstTime) {
		this.predictedBurstTime = predictedBurstTime;
	}
}
