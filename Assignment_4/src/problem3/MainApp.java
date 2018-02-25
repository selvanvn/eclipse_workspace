package problem3;

public class MainApp {

	public static void main(String[] args) {
		
		String filePathForProblem3 = System.getProperty("user.home") + "/Desktop/jobQueueProblem3.csv";
		
		System.out.println("3-a) First come first serve");
		CPUFCFS cpu = new CPUFCFS(filePathForProblem3);
		cpu.startSimulate();

		System.out.println("\n3-a) Shortest Job First: Alpha 1.0");
		CPUSJF cpuSjf1 = new CPUSJF(filePathForProblem3);
		cpuSjf1.startSimulate(1);


		System.out.println("\n3-a) Shortest job first: Alpha 0.5");
		CPUSJF5 cpuSjf5 = new CPUSJF5(filePathForProblem3);
		cpuSjf5.startSimulate(0.5);

		System.out.println("\n3-a) Shortest job first: Alpha 0.3");
		CPUSJF3 cpuSjf3 = new CPUSJF3(filePathForProblem3);
		cpuSjf3.startSimulate(0.3);

		for(int i = 1; i <20; i++){
			if(i % 2 != 0 ){
				System.out.println("\n3-c) Round Robin Time Slice : " + i);
				CPURR cpuRR = new CPURR(filePathForProblem3, i);
				cpuRR.startSimulate();
			}
		}

	}
}
