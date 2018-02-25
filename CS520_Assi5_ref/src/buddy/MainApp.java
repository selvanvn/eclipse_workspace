package buddy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MainApp {
	static String s1 = "";
	static String s2 = "";
	static String s3 = "";
	static List<TreeNode> freeList = new ArrayList<TreeNode>();
	static TreeNode temp = null;
	
	public static void main(String args[]){
		
		TreeNode node0 = new TreeNode(1, 2048);
		
		computeFreeList(node0);
		printInConsole(node0);
		insert(20, "A", node0);
		printInConsole(node0);
		insert(35, "B", node0);
		printInConsole(node0);
		insert(90, "C", node0);
		printInConsole(node0);
		insert(40, "D", node0);
		printInConsole(node0);
		insert(240, "E", node0);
		printInConsole(node0);
		
		remove(40, "D", node0);
		printInConsole(node0);
		remove(20, "A", node0);
		printInConsole(node0);
		remove(90, "C", node0);
		printInConsole(node0);
		remove(35, "B", node0);
		printInConsole(node0);
		remove(240, "E", node0);
		printInConsole(node0);
	}
	
	public static void remove(int memorySize, String allocatedBlockName, TreeNode node){
		removeAllocatedBlock(node, memorySize, allocatedBlockName);
		traverseNodes(node);
		computeFreeList(node);
	}
	
	public static void traverseNodes(TreeNode node){
		if(node == null){
			return;
		}
		
		if(node.left == null && node.right == null)
			return;
	
		traverseNodes(node.left);
		traverseNodes(node.right);
		
		if(node.left.flag == 1 && node.right.flag == 1){
			node.flag = 1;
			node.left = null;
			node.right = null;
			return;
		}
	}
	
	public static void removeAllocatedBlock(TreeNode node, int i, String name) {
		if(node == null){
			return;
		}
		if(node.allocatedNodeName == name && node.allocatedMemory == i){
			node.flag = 1;
			node.allocatedMemory = 0;
			node.allocatedNodeName = null;
			return;
		}
		else{
			removeAllocatedBlock(node.left, i, name);
			removeAllocatedBlock(node.right, i, name);
		}
	}
	
	static int countNodes( TreeNode root ) {
		   if ( root == null )
		      return 0; 
		   else {
		      int count = 1;   
		      count += countNodes(root.left);  
		      count += countNodes(root.right); 
		      return count; 
		   }
		}
	
	public static void insert(int memory, String name, TreeNode parentNode){
		
		int nearest = 0;
		
		if((memory & (memory - 1)) == 0){
			nearest = memory;
		}
		else{
		 nearest = (int)(Math.pow(2, (int)(Math.log(memory)/Math.log(2)+1e-10) + 1));
		}
		boolean check = true;
		
		Iterator i = freeList.iterator();
		while(i.hasNext()){
			TreeNode temp = (TreeNode)i.next();
			if(temp.memorySize == nearest){
				temp.flag = 0;
				temp.allocatedNodeName = name;
				temp.allocatedMemory = memory;
				freeList.remove(temp);
				check = false;
				Collections.sort(freeList);
				break;
			}
		}
		
		if(check == true){
			Iterator i2 = freeList.iterator();
			while(i2.hasNext()){
				TreeNode temp = (TreeNode)i2.next();
				if(temp.memorySize > nearest){
					temp.flag = 0;
					breakThat(temp, temp.memorySize / 2, nearest, memory, name);
					check = false;
					computeFreeList(parentNode);
					Collections.sort(freeList);
					break;
				}
			}
		}
		
		if(check == true){
			System.out.println("There is some error in insert function");
		}
	}
	
	public static void breakThat(TreeNode t, int i, int nearest, int memory, String name){
		t.flag = 0;
		t.left = new TreeNode(1, i);
		t.right = new TreeNode(1, i);
		
		if(i == nearest){
			t.left.flag = 0;
			t.left.allocatedMemory = memory;
			t.left.allocatedNodeName = name;
			return;
		}
		else{
			breakThat(t.left, i/2, nearest, memory, name);
		}
	}

	public static void search(TreeNode node, int ID){
		if(node == null)       
		      return;
		   if(node.ID == ID)    {  
		      temp = node;
		   }
		   search(node.left, ID); 
		   search(node.right, ID);
	}
	
	public static void computeFreeList(TreeNode node){
		boolean f = true;
		boolean f2 = true;
		if(node == null)       
		      return;
		
		 if(node.flag == 0){
	    	  Iterator i2 = freeList.iterator();
	  		  TreeNode temp;
	  		  while(i2.hasNext()){
	  			temp = (TreeNode)i2.next();
	  			if(node.ID == temp.ID){
	  				f2 = false;
	  				}
	  			}
	  		  if(f2 == false){
	  			freeList.remove(node);
	  			Collections.sort(freeList);
	  		  }	  
	      }
		
		   if(node.left == null &&  node.right==null)    {   
		      if(node.flag == 1){
		    	  Iterator i = freeList.iterator();
		  		  TreeNode temp;
		  		  while(i.hasNext()){
		  			temp = (TreeNode)i.next();
		  			if(node.ID == temp.ID){
		  				f = false;
		  				}
		  			}
		  		  if(f == true){
		  			freeList.add(node);
		  			Collections.sort(freeList);
		  		  }	  
		      }
		     
		   }
		   computeFreeList(node.left); 
		   computeFreeList(node.right);
	}
	
	public static void printFreeList(TreeNode node){
		Iterator i = freeList.iterator();
		  TreeNode temp;
		  while(i.hasNext()){
			temp = (TreeNode)i.next();
			System.out.println(temp.memorySize);
		  }
	}
	
	public static void printLeafNodes(TreeNode node) {
		   if(node == null)       
		      return;
		   if(node.left == null &&  node.right==null)    {   
		      draw(node.memorySize, node.allocatedNodeName);
		   }
		   printLeafNodes(node.left); 
		   printLeafNodes(node.right);
	  }
	
	public static void draw(int memorySize, String name){
		
		if(name == null){	
			if(memorySize == 32 ){
				s1 = s1 + " -------- ";
				s2 = s2 + "|32(free)|";
				s3 = s3 + " -------- ";
			}
			
			if(memorySize == 64){
				s1 = s1 + " ------------ ";
				s2 = s2 + "|  64(free)  |";
				s3 = s3 + " ------------ ";
			}
			
			if(memorySize == 128){
				s1 = s1 + " ------------------- ";
				s2 = s2 + "|     128(free)     |";
				s3 = s3 + " ------------------- ";
			}
			
			if(memorySize == 256){
				s1 = s1 + " ---------------------- ";
				s2 = s2 + "|       256(free)      |";
				s3 = s3 + " ---------------------- ";
			}
			
			if(memorySize == 512){
				s1 = s1 + " -------------------------- ";
				s2 = s2 + "|         512(free)        |";
				s3 = s3 + " -------------------------- ";
			}
			
			if(memorySize == 1024){
				s1 = s1 + " ----------------------------- ";
				s2 = s2 + "|           1024(free)        |";
				s3 = s3 + " ----------------------------- ";
			}
			
			if(memorySize == 2048){
				s1 = s1 + " ---------------------------------------------------------- ";
				s2 = s2 + "|                      2048(free)                          |";
				s3 = s3 + " ---------------------------------------------------------- ";
			}
		}
		else{
			if(memorySize == 32){
				s1 = s1 + " -------- ";
				s2 = s2 + "| 32("+ name +")  |";
				s3 = s3 + " -------- ";
			}
			if(memorySize == 64){
				s1 = s1 + " ------------ ";
				s2 = s2 + "|   64("+ name +")    |";
				s3 = s3 + " ------------ ";
			}
			
			if(memorySize == 128){
				s1 = s1 + " ------------------- ";
				s2 = s2 + "|      128("+ name +")       |";
				s3 = s3 + " ------------------- ";
			}
			
			if(memorySize == 256){
				s1 = s1 + " ---------------------- ";
				s2 = s2 + "|        256("+ name +")        |";
				s3 = s3 + " ---------------------- ";
			}
			
			if(memorySize == 512){
				s1 = s1 + " -------------------------- ";
				s2 = s2 + "|          512("+ name +")          |";
				s3 = s3 + " -------------------------- ";
			}
			
			if(memorySize == 1024){
				s1 = s1 + " ----------------------------- ";
				s2 = s2 + "|            1024("+ name +")          |";
				s3 = s3 + " ----------------------------- ";
			}
		}
	}
	
	public static void printInConsole(TreeNode node){
		printLeafNodes(node);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		s1 = "";
		s2 = "";
		s3 = "";
	}
}
