package buddy;

public class TreeNode implements Comparable<TreeNode>{
	int flag;
	int memorySize;
	int ID;
	int allocatedMemory;
	String allocatedNodeName = null;
	static int nextID = 0;
	TreeNode left;
	TreeNode right;
	
	TreeNode(int flag, int memorySize){
		ID = nextID;
		nextID++;
		this.flag = flag;
		this.memorySize = memorySize;
	}

	public int getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(int memorySize) {
		this.memorySize = memorySize;
	}

	@Override
	public int compareTo(TreeNode node) {
		Integer memorySize1 = this.memorySize;
		Integer memorySize2 = node.memorySize;
		return memorySize1.compareTo(memorySize2);
	}
}
