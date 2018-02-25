package problem3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class CPURR {
	Vector allProcs = new Vector();
	Vector jobQueue = new Vector();
	Queue<Process_Round_Robin> readyQueue = new LinkedList<Process_Round_Robin>();
	Queue<Process_Round_Robin> ioQueue = new LinkedList<Process_Round_Robin>();
	long idle = 0, currentTime = 0, busy = 0;
	long totalWaitingTime = 0, ioQueueWaitingTime = 0;
	int procsOut = 0;
	TreeMap turnAroundTime = new TreeMap();
	long totalUtilityTime = 0;
	
	Process_Round_Robin activeJobInCPU = null;
	Process_Round_Robin activeJobInIO = null;
	
	CPURR(String filename, long timeSlice) {
		Process_Round_Robin proc = null;
		String s = null;
		double executingTime = 0;
		try {
			BufferedReader input = new BufferedReader(new FileReader(filename));
			while ((s = input.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(s, ",");
				executingTime = Double.parseDouble(st.nextToken());
				int burstTime = ThreadLocalRandom.current().nextInt(2, 5);
				burstTime = burstTime * 60 * 1000;
				int randExecutingTime = getPoissonRandom(executingTime);
				proc = new Process_Round_Robin((long)burstTime, (long)executingTime, (long)randExecutingTime, (long)timeSlice);
				allProcs.add(proc);
			}

		} catch (FileNotFoundException fnfe) {
		} catch (IOException ioe) {
		}
		LoadJobQueue(allProcs);
		LoadReadyQueue();
	}
	
	private void LoadJobQueue(Vector jobs) {
		Process_Round_Robin p;
		for (int i = 0; i < jobs.size(); i++) {
			p = (Process_Round_Robin) jobs.get(i);
			jobQueue.add(p);
		}
	}
	
	private void LoadReadyQueue() {
		Process_Round_Robin p;
		for (int i = 0; i < jobQueue.size(); i++) {
			p = (Process_Round_Robin) jobQueue.get(i);
			readyQueue.add(p);
		}
	}
	
	public void startSimulate() {
		while (nextCycle())
			;
	}

	private boolean nextCycle() {
		boolean moreCycles = false;
		if (readyQueue.isEmpty() && ioQueue.isEmpty()){
			moreCycles = false;
			printStats();
		}
		else{
			moreCycles = true;
			performCPU();
			performIO();
			cleanup();
			rotateQueue();
			currentTime++;
		}
		return moreCycles;
	}

	private void performCPU() {	
		if(!readyQueue.isEmpty()){
			Process_Round_Robin p = readyQueue.peek();
			p.cpuExecuting(currentTime);
			busy++;
			totalUtilityTime++;
			if(readyQueue.size()!=1){
				Iterator i = readyQueue.iterator();
				Process_Round_Robin temp = (Process_Round_Robin)i.next();
				while(i.hasNext()){
					temp = (Process_Round_Robin)i.next();
					temp.cpuWaiting(currentTime);
				}
			}
			/*if (currentTime % 100000 == 0)
				System.out.print("P" + p.PID + " ");*/
		}else{
			idle++;
		}
	}
	
	private void performIO() {
		if(!ioQueue.isEmpty()){
			Process_Round_Robin p = ioQueue.peek();
			p.ioExecuting(currentTime);
			if(ioQueue.size()!=1){
				Iterator i = ioQueue.iterator();
				Process_Round_Robin temp = (Process_Round_Robin)i.next();
				while(i.hasNext()){
					temp = (Process_Round_Robin)i.next();
					temp.ioWaiting(currentTime);
				}
			}
		}
	}
	
	private void rotateQueue() {
		
		if (!readyQueue.isEmpty()){
			Process_Round_Robin p = readyQueue.peek();
			if(p.isCPUExecutingTimeFinished()){
				Process_Round_Robin temp = readyQueue.remove();
				temp.setIOProcessingTime(60);
				p.setIOfinished(false);
				ioQueue.add(temp);
			}
			
			else if (p.isCurrentTimeSliceFinished()){
				Process_Round_Robin temp = readyQueue.remove();
				temp.setCurrentTimeSliceFinished(false);
				temp.setCurrentTimeSlice(temp.timeSlice);
				readyQueue.add(temp);
			}
		}
		
		if (!ioQueue.isEmpty()){
			Process_Round_Robin p = ioQueue.peek();
			if(p.isIOfinished()){
				Process_Round_Robin temp = ioQueue.remove();
				temp.setCpuExecutingTime(temp.randExecutingTime);
				p.cpuExecutingTimeFinished = false;
				readyQueue.add(temp);
			}
		}
	}
	
	private void cleanup(){
		if(!readyQueue.isEmpty()){
			Process_Round_Robin p = readyQueue.peek();
			if(p.isCPUTotalBurstTimeFinished()){
				Process_Round_Robin removedP = readyQueue.remove();
				turnAroundTime.put(removedP.PID, removedP.lifeTime);
				totalWaitingTime = totalWaitingTime + removedP.cpuQueueWaitTime;
				ioQueueWaitingTime = ioQueueWaitingTime + removedP.ioQueueWaitTime;
				procsOut++;
			}
		}
	}
	
	private static int getPoissonRandom(double mean) {
	    Random r = new Random();
	    double L = Math.exp(-mean);
	    int k = 0;
	    double p = 1.0;
	    do {
	        p = p * r.nextDouble();
	        k++;
	    } while (p > L);
	    return k - 1;
	}
	
	private void printStats(){
		double cpuUtilization = 0, idlePercentage = 0, totalTurnAroundTime = 0;
		double throughput = (double)10 / totalUtilityTime;
		cpuUtilization = (double)busy / currentTime * 100;
		idlePercentage = (double)idle / currentTime * 100;
		System.out.println();
		System.out.println("CPU Utilization: " + cpuUtilization + "% (Idle:"+ idlePercentage +"%)");
		System.out.println("Throughput: " + throughput);
		Set set = turnAroundTime.entrySet();
	    Iterator i = set.iterator();
	     while(i.hasNext()) {
	         Map.Entry processId = (Map.Entry)i.next();
	         //System.out.print("Turn-around time Process " + processId.getKey() + ": ");
	         totalTurnAroundTime = totalTurnAroundTime + (Long)processId.getValue() / 60000;
	         //System.out.println((Long)processId.getValue() / 60000+ "m" );
	      }
	      
	    System.out.println("Average Turnaround Time: " + totalTurnAroundTime / 10);  
	    System.out.println("Mean waiting time in ready queue: " + totalWaitingTime / 1000 + "s");
	}
}
