package problem2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class CPUFCFS2b {
	Vector allProcs = new Vector();
	Vector jobQueue = new Vector();
	Queue<Process> readyQueue = new LinkedList<Process>();
	long idle = 0, currentTime = 0, busy = 0;
	long totalWaitingTime = 0;
	int procsOut = 0;
	TreeMap turnAroundTime = new TreeMap();
	TreeMap waitingTime = new TreeMap();
	static long totalUtilityTime = 0;
	int tempPID = 0;
	int timeArr[] = new int[99];
	int time = 0;
	int index = 0;
	int dummy = 0;
	
	Process activeJobInCPU = null;
	Process activeJobInIO = null;
	
	CPUFCFS2b(String filename) {
		for(int i=0; i< timeArr.length; i++)
			timeArr[i] = 98;
		
		Process proc = null;
		String s = null;
		double arrivalTime = 0;
		double burstTime = 0;
		double priority = 0;
		try {
			BufferedReader input = new BufferedReader(new FileReader(filename));
			while ((s = input.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(s, ",");
				//arrivalTime = Double.parseDouble(st.nextToken());
				burstTime = Double.parseDouble(st.nextToken());
				priority = Double.parseDouble(st.nextToken());
				arrivalTime = arrivalTime * 10;
				burstTime = burstTime * 10;
				proc = new Process((long)arrivalTime, (long)burstTime, (long)priority);
				allProcs.add(proc);
			}

		} catch (FileNotFoundException fnfe) {
		} catch (IOException ioe) {
		}
		LoadJobQueue(allProcs);
		LoadReadyQueue();
	}
	
	private void LoadJobQueue(Vector jobs) {
		Process p;
		for (int i = 0; i < jobs.size(); i++) {
			p = (Process) jobs.get(i);
			jobQueue.add(p);
		}
	}
	
	private void LoadReadyQueue() {
		Process p;
		for (int i = 0; i < jobQueue.size(); i++) {
			p = (Process) jobQueue.get(i);
			readyQueue.add(p);
		}
	}
	
	public void startSimulate() {
		while (nextCycle())
			;
	}

	private boolean nextCycle() {
		boolean moreCycles = false;
		if (readyQueue.isEmpty()){
			System.out.print("-|");
			time++;
			timeArr[index] = time;
			moreCycles = false;
			printStats();
		}
		else{
			moreCycles = true;
			performCPU();
			cleanup();
			//rotateQueue();
			currentTime++;
		}
		return moreCycles;
	}

	private void performCPU() {	
		if(!readyQueue.isEmpty()){
			Process p = (Process)readyQueue.peek();
			if(p.PID != tempPID){
				tempPID = p.PID;
				if(time % 10 == 0){
					System.out.print("P" + tempPID);
					timeArr[index] = time;
					index++;
				}
				time++;
			}
			else{
				if(time % 10 == 0){

					System.out.print("-");
					timeArr[index] = 99;
					index++;
				}
				time++;
			}
			p.cpuExecuting(currentTime);
			busy++;
			totalUtilityTime++;
			if(readyQueue.size()!=1){
				Iterator i = readyQueue.iterator();
				Process temp = (Process)i.next();
				while(i.hasNext()){
					temp = (Process)i.next();
					temp.cpuWaiting(currentTime);
				}
			}
		}else{
			idle++;
		}
	}
	
	private void rotateQueue() {
		if (!readyQueue.isEmpty()){
			Process p = (Process)readyQueue.peek();
			if(p.isCPUExecutingTimeFinished()){
				Process temp = (Process)readyQueue.remove();
			}
		}
	}
	
	private void cleanup(){
		if(!readyQueue.isEmpty()){
			long temp = 0;
			Process p = (Process)readyQueue.peek();
			if(p.isCPUTotalBurstTimeFinished()){
				timeArr[index] = 99;
				index++;
				dummy++;
				if(dummy!=1)
					System.out.print("--");
				else
					System.out.print("-");
				Process removedP = (Process)readyQueue.remove();
				temp = removedP.lifeTime - removedP.getArrivalTime();
				turnAroundTime.put(removedP.PID, temp);
				
				waitingTime.put(removedP.getPID(), removedP.cpuQueueWaitTime);
				totalWaitingTime = totalWaitingTime + removedP.cpuQueueWaitTime;
				procsOut++;
			}
		}
	}
	
	private void printStats(){
		double cpuUtilization = 0, idlePercentage = 0, totalTurnAroundTime = 0;
		double throughput = (double)10 / totalUtilityTime;
		cpuUtilization = (double)busy / currentTime * 100;
		idlePercentage = (double)idle / currentTime * 100;
		System.out.println();
		for(int i =0; i < timeArr.length; i++){
			if (timeArr[i] == 99){
				System.out.print(" ");
			}
			else if(timeArr[i] == 98){
				break;
			}
			else{
				System.out.print(timeArr[i] /10+ " ");
			}
		}
		System.out.println();
		//System.out.println("CPU Utilization: " + cpuUtilization + "% (Idle:"+ idlePercentage +"%)");
		//System.out.println("Throughput: " + throughput);
		Set set = turnAroundTime.entrySet();
	    Iterator i = set.iterator();
	    System.out.println();
	      while(i.hasNext()) {
	         Map.Entry processId = (Map.Entry)i.next();
	         System.out.print("Turn-around time Process " + processId.getKey() + ": ");
	         totalTurnAroundTime = totalTurnAroundTime + (Long)processId.getValue();
	         System.out.println((Long)processId.getValue() / 10);
	      }
	    System.out.println("\nAverage Turnaround Time: " + totalTurnAroundTime / 50); 
	    
	    double tempWaitingTime = 0;
	    Set set2 = waitingTime.entrySet();
	    Iterator i2 = set2.iterator();
	    System.out.println();
	      while(i2.hasNext()) {
	         Map.Entry processId2 = (Map.Entry)i2.next();
	         System.out.print("Waiting Time Process " + processId2.getKey() + ": ");
	         tempWaitingTime = tempWaitingTime + (Long)processId2.getValue();
	         System.out.println((Long)processId2.getValue() / 10);
	      }
	    System.out.println("Mean waiting time in ready queue: " + tempWaitingTime / 50 + "s");
	}
}
