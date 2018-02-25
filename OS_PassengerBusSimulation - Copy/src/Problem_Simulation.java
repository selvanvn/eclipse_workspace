import org.apache.log4j.Logger;

public class Problem_Simulation {
	
	private Bus_Stop_List allStops = new Bus_Stop_List();
	private Passenger_Bus_Implementation busImpl = new Passenger_Bus_Implementation();
	private int[][] queueStatus = new int[15][30000];
	private int[][] averageByHour = new int[8][15];
	private int[][] maximumByHour = new int[8][15];
	private int[][] minimumByHour = new int[8][15];
	private int counter = 0;
	
	final static Logger logger = Logger.getLogger(Problem_Simulation.class.toString());
	
	public static void main(String args[]){
		logger.info("Inside main method");
		Problem_Simulation bus = new Problem_Simulation();
		bus.runSimulation();		
	}
	

	
	public void runSimulation(){
		busImpl.createBus(5);
		for(int clock=1; clock<28801; clock++){
			logger.info("----------------------------------------------------------------");
			logger.info("At clock time: "+ clock);
			logger.info("----------------------------------------------------------------");
			logger.info("Operation 1: Calling add passengers");
			allStops.addPassengers(clock);
			//System.out.println("At clock" + clock + ":" + allStops.allStopsQueueSize());
			allStops.clearLog();
			
			logger.info("Operation 2: Updating bus positions");
			busImpl.updateBusPosition(clock, allStops);
			//System.out.println("At clock" + clock + ":" + busImpl.getLog());
			logger.info("At clock" + clock + ":" + busImpl.getLog());
			//System.out.println("At clock" + clock + ":" + busImpl.getBoardedPassengerLog());
			busImpl.clearLog();
			
			computeQueueSize(clock);
			if(clock%3600 == 0){
				computeAverage(clock, counter);
				computeMaximum(clock, counter);
				computeMinimum(clock, counter);
				counter = counter + 1;
			}
		}
		
		printAverageTable();
		printMinimumTable();
		printMaximumTable();
	}
	
	public void computeQueueSize(int clock){
		for(int i=0; i<15; i++){
			queueStatus[i][clock] = allStops.getQueueSize(i);
		}
	}
	
	public void computeAverage(int clock, int counter){
		for(int stopNumber = 0; stopNumber<15;stopNumber++){
			int temp = 0;
			for(int start = clock-3600; start < clock; start++){
				temp = temp + queueStatus[stopNumber][start];
			}
			averageByHour[counter][stopNumber] = temp / 3600;
		}
	}
	
	public void computeMaximum(int clock, int counter){		
		for(int stopNumber = 0; stopNumber<15;stopNumber++){
			int temp = 0;
			for(int start = clock-3600; start < clock; start++){
				if(queueStatus[stopNumber][start] > temp){
					temp = queueStatus[stopNumber][start];
				}
			}
			maximumByHour[counter][stopNumber] = temp ;
		}
	}
	
	public void computeMinimum(int clock, int counter){	
		for(int stopNumber = 0; stopNumber<15;stopNumber++){
			int tempVar = clock-3600;
			int temp = queueStatus[stopNumber][tempVar];
			for(int start = clock-3600; start < clock; start++){
				if(queueStatus[stopNumber][start] < temp){
					temp = queueStatus[stopNumber][start];
				}
			}
			minimumByHour[counter][stopNumber] = temp;
		}
	}
	
	public void printAverageTable(){
		int temp =0;
		System.out.println();
		System.out.println("y axis represents hour no.");
		System.out.println("x axis represents bus stop number");
		System.out.println("Average queue size of bus stops for hours (1 to 8)...");
		for(int i=0; i<8;i++){
			System.out.print((i+1)+" |");
			for(int stopNumber = 0; stopNumber < 15; stopNumber++){
				System.out.print(" " + averageByHour[i][stopNumber]);
			}
			System.out.println();
			}
	}
	
	public void printMinimumTable(){
		System.out.println();
		System.out.println("Minimum queue size of bus stops for hours (1 to 8)...");
		for(int i = 0; i<8;i++){
			System.out.print((i+1)+" |");
			for(int stopNumber = 0; stopNumber < 15; stopNumber++){
				System.out.print(" " + minimumByHour[i][stopNumber]);
			}
			System.out.println();
			}
	}
	
	public void printMaximumTable(){
		System.out.println();
		System.out.println("Maximum queue size of bus stops for hours (1 to 8)...");
		for(int i = 0; i<8;i++){
			System.out.print((i+1)+" |");
			for(int stopNumber = 0; stopNumber < 15; stopNumber++){
				System.out.print(" " + maximumByHour[i][stopNumber]);
			}
			System.out.println();
			}
	}

}
