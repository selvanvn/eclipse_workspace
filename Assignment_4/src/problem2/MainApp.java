package problem2;



public class MainApp {

	public static void main(String[] args) {
		
		String filePathForProblem2a = System.getProperty("user.home") + "/Desktop/jobQueueProblem2a.csv";
		String filePathForProblem2b = System.getProperty("user.home") + "/Desktop/jobQueueProblem2b.csv";
		
		System.out.println("5.3 a) First come first serve \nGantt chart: \n");
		CPUFCFS cpu = new CPUFCFS(filePathForProblem2a);
		cpu.startSimulate();
		
		Process.nextPID = 0;
		System.out.println("\n5.3 b) Shortest Job First \nGantt chart: \n");
		CPUSJF cpu2 = new CPUSJF(filePathForProblem2a);
		cpu2.startSimulate();
		
		Process.nextPID = 0;
		System.out.println("\n5.3 c) Shortest Job First \nGantt chart: \n");
		FutureKnowledge cpu3 = new FutureKnowledge(filePathForProblem2a);
		cpu3.startSimulate();
		
		Process.nextPID = 0;
		System.out.println("\n5.12 a) First come first serve \nGantt chart: \n");
		CPUFCFS2b cpu4 = new CPUFCFS2b(filePathForProblem2b);
		cpu4.startSimulate();
		
		Process.nextPID = 0;
		System.out.println("\n5.12 b) Shortest Job First \nGantt chart: \n");
		SJF2b cpu5 = new SJF2b(filePathForProblem2b);
		cpu5.startSimulate();
		
		Process.nextPID = 0;
		System.out.println("\n5.12 c) Non Premptive Priority  \nGantt chart: \n");
		CPUPriority2b cpu6 = new CPUPriority2b(filePathForProblem2b);
		cpu6.startSimulate();
		
		Process.nextPID = 0;
		System.out.println("\n5.12 d) Round Robin  \nGantt chart: \n");
		CPURR2b cpu7 = new CPURR2b(filePathForProblem2b);
		cpu7.startSimulate();
		
		}

}
