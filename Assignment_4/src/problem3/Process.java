package problem3;

public class Process {
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
	boolean started=false;
    boolean cpuTotalBurstFinished=false;
    boolean cpuExecutingTimeFinished=false;
    boolean ioFinished=false;
    boolean arrived=false;
    boolean active = false;
	
	Process(long burst, long cpuExecutingTime, long randExecutingTime){
			nextPID++;
			PID = nextPID;
			this.randExecutingTime = cpuExecutingTime;
			this.burstTime = burst;
			initBurst = this.burstTime;
			this.cpuExecutingTime = randExecutingTime;
			this.predictedBurstTime = randExecutingTime;
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
