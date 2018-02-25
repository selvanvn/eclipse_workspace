import java.util.Formatter;

import org.apache.log4j.Logger;

public class Problem_Simulation {
	
	private Bus_Stop_List allStops = new Bus_Stop_List();
	private Passenger_Bus_Implementation busImpl = new Passenger_Bus_Implementation();
	private int total_Seconds=3600;
	private int total_Stops=15;
	private int total_bus=5;
	private int[][] queuestate = new int[total_Stops][30000];
	private int[][] avehr = new int[8][total_Stops];
	private int[][] maxhr = new int[8][total_Stops];
	Formatter formatter = new Formatter();
	Formatter formatter2 = new Formatter();
	Formatter formatter3= new Formatter();
	private int[][] minhr = new int[8][total_Stops];
	
	
	private int counter = 0;
	private int total_time_Seconds= 8*60*60+1;// 8 hrs to seconds (+1 since we start with 1)
	
	final static Logger logger = Logger.getLogger(Problem_Simulation.class.toString());
	
	
	

	
	public void start(){
		busImpl.createBus(total_bus);
		for(int clocktime=1; clocktime<total_time_Seconds; clocktime++){
		
			
			allStops.addPassengers(clocktime);
			allStops.clearLog();
			busImpl.updateBusPosition(clocktime, allStops);
			busImpl.clearLog();
			computeQueueSize(clocktime);
			if(clocktime%total_Seconds == 0){
				
				compute(clocktime,counter);
				counter = counter + 1;
			}
		}
		
		
		PrintOutPut();
	}
	
	public void computeQueueSize(int clock){
		for(int i=0; i<total_Stops; i++){
			queuestate[i][clock] = allStops.getQueueSize(i);
		}
	}
	
	
	private void compute(int clock, int counter)
	{
		
		for(int stopNo = 0; stopNo<total_Stops;stopNo++){
			int tvarb = 0;
			for(int start = clock-total_Seconds; start < clock; start++){
				tvarb = tvarb + queuestate[stopNo][start];
			}
			avehr[counter][stopNo] = tvarb / total_Seconds;
		}
		
		
		for(int stopNo = 0; stopNo<total_Stops;stopNo++){
			int tvarb = 0;
			for(int start = clock-total_Seconds; start < clock; start++){
				if(queuestate[stopNo][start] > tvarb){
					tvarb = queuestate[stopNo][start];
				}
			}
			maxhr[counter][stopNo] = tvarb ;
		}
		
		for(int stopNo = 0; stopNo<total_Stops;stopNo++){
			int tvarbVar = clock-total_Seconds;
			int tvarb = queuestate[stopNo][tvarbVar];
			for(int start = clock-total_Seconds; start < clock; start++){
				if(queuestate[stopNo][start] < tvarb){
					tvarb = queuestate[stopNo][start];
				}
			}
			minhr[counter][stopNo] = tvarb;
		}
		
	}
	
	private void PrintOutPut()
	{
		
		System.out.printf("%n");
		System.out.println("\n\nMaximum queue size of bus stops for hours (1 to 8)");
		System.out.print("\tBus Stop");
		System.out.print("\nHours 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15");
		System.out.print("\n--------------------------------------------------\n");
		System.out.printf("%n");
		
		for(int i = 0; i<8;i++){
			System.out.print((i+1)+" |");
			for(int stopNo = 0; stopNo < total_Stops; stopNo++){
				System.out.print(" " + maxhr[i][stopNo]);
				
			}
			System.out.printf("%n");
			}
		
		System.out.println("\n\nAverage queue size of bus stops for hours (1 to 8)\n");
		System.out.print("\tBus Stop");
		System.out.print("\nHours 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15");
		System.out.print("\n--------------------------------------------------\n");
		System.out.println("\n");
	
		for(int i=0; i<8;i++){
			System.out.print((i+1)+" |");
			for(int stopNumber = 0; stopNumber < total_Stops; stopNumber++){
				System.out.print(" " + avehr[i][stopNumber]);
			}
			 
			System.out.printf("%n");
			
			}
		System.out.println("\n\nMinimum queue size of bus stops for hours (1 to 8)\n");
		System.out.printf("%n");
		System.out.print("\tBus Stop");
		System.out.print("\nHours 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15");
		System.out.print("\n--------------------------------------------------\n");
	
		for(int i = 0; i<8;i++){
			System.out.print((i+1)+" |");
			for(int stopNo = 0; stopNo < total_Stops; stopNo++){
				System.out.print(" " +  minhr[i][stopNo]);
			}
			System.out.printf("%n");
			}
	}
	
	
	
	public static void main(String args[]){
	    Problem_Simulation simulation = new Problem_Simulation();
	    simulation.start();		
	}
		
	
	

}
