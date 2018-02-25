package buddySystem;

import java.util.ArrayList;
import java.util.Scanner;



public class BuddySystem {
	static private int total_mem;
	private int total_alloc;
	private int twon;
	Allocation al=new Allocation();
	static ArrayList <Allocation>alloc_requests=new ArrayList<Allocation>();
	
	static ArrayList <MemoryAllocated> allocated=new ArrayList<MemoryAllocated>();
	
	Scanner sc=new Scanner(System.in);
	
	public static void main(String [] args)
	{
		BuddySystem bs=new BuddySystem();
		bs.input();
		bs.process();
		
	}
	public void display()
	{
		for(int i=0;i<allocated.size();i++)
		{
			System.out.print("|  "+allocated.get(i).allocatedBy+""+allocated.get(i).sizeAllocated+"   ");
		}
		System.out.println("||");
		//System.out.println("--------------------------------------------------------------------------------------------------");
	}
	public void process()
	{
		BuddySystem call=new BuddySystem();
		int available_mem=total_mem;
		MemoryAllocated ac=new MemoryAllocated();
		ac.sizeAllocated=total_mem;
		allocated.add(ac);
		for(int i=0;i<alloc_requests.size();i++)
		{
			switch(alloc_requests.get(i).request)
			{
			case "+":
					Allocate(i);
					break;
			case "-":
				deAllocate(i);
				break;
				
			}
		
		}
		
		
	}
	public void Allocate(int reqIndex)
	{
		BuddySystem bk=new BuddySystem();
		int index=bk.searchin(alloc_requests.get(reqIndex).alloc);
		int highAlloc=allocated.get(index).sizeAllocated;
		
		int request=new BuddySystem().findn(alloc_requests.get(reqIndex).alloc);
		int searchReq=bk.searchInArray(request);
		
		if(searchReq!=-1)
		{
			allocated.get(searchReq).allocated=true;
			allocated.get(searchReq).allocatedBy=alloc_requests.get(reqIndex).processID;
		}
		else
		{
			int count =0;
			while(request != highAlloc)
			{
				if (count==0)
					allocated.remove(index);
				
				MemoryAllocated ni=new MemoryAllocated();
				highAlloc=highAlloc/2;
				ni.sizeAllocated=highAlloc;
				allocated.add(index, ni);
				count++;
			}
			
			MemoryAllocated forCurrent=new MemoryAllocated();
			forCurrent.sizeAllocated=highAlloc;
			forCurrent.allocated=true;
			forCurrent.allocatedBy=alloc_requests.get(reqIndex).processID;
			allocated.add(index,forCurrent);
		}
		System.out.println("\n");
		System.out.println("Request of Process "+alloc_requests.get(reqIndex).processID+" for "+alloc_requests.get(reqIndex).alloc+" bytes of memory");
		System.out.print(""+alloc_requests.get(reqIndex).processID+": "+alloc_requests.get(reqIndex).request+""+alloc_requests.get(reqIndex).alloc+" |");
		bk.display();
	}
	public void deAllocate(int reqIndex)
	{
		BuddySystem bs=new BuddySystem();
		for(int s=0;s<allocated.size();s++)
		{
			if(allocated.get(s).allocatedBy.equals(alloc_requests.get(reqIndex).processID))
			{
				allocated.get(s).allocated=false;
				allocated.get(s).allocatedBy="";
				if(s==0)
				{
					while(allocated.get(s+1).allocated==false && allocated.get(s+1).sizeAllocated==allocated.get(s).sizeAllocated)
					{
						allocated.get(s).sizeAllocated*=2;
						allocated.remove(s+1);
						
					}
					break;
				}
				else if(s==allocated.size()-1)
				{
					while(allocated.get(s-1).allocated==false && allocated.get(s-1).sizeAllocated==allocated.get(s).sizeAllocated)
					{
						allocated.get(s).sizeAllocated*=2;
						allocated.remove(s-1);
					}
					break;
				}
				else
				{
					
						while(allocated.get(s-1).sizeAllocated==allocated.get(s).sizeAllocated && allocated.get(s-1).allocated==false || allocated.get(s+1).sizeAllocated==allocated.get(s).sizeAllocated && allocated.get(s+1).allocated==false)
						{
							if(allocated.get(s+1).sizeAllocated==allocated.get(s).sizeAllocated)
							{
								allocated.get(s).sizeAllocated*=2;
								allocated.remove(s+1);
							}
							else if(allocated.get(s-1).sizeAllocated==allocated.get(s).sizeAllocated)
							{
								allocated.get(s).sizeAllocated*=2;
								allocated.remove(s-1);
							}
						}
					
					break;
				}
			}
		}
		System.out.println("\n");
		System.out.println("Request of Process "+alloc_requests.get(reqIndex).processID+" to de allocate "+alloc_requests.get(reqIndex).alloc+" bytes of memory");
		System.out.print(""+alloc_requests.get(reqIndex).processID+":"+alloc_requests.get(reqIndex).request+""+alloc_requests.get(reqIndex).alloc+" |");
		bs.display();
	}
	
	public int searchInArray(int request)
	{
		for(int i=0;i<allocated.size();i++)
		{
			if(request==allocated.get(i).sizeAllocated && allocated.get(i).allocated==false)
			{
				return i;
			}
		}
		return -1;
	}
	public int searchin(int allocationForI)
	{
		int index=0;
		int min=total_mem;
		int diff=0;
		for(int i=0;i<allocated.size();i++)
		{
			if(allocated.get(i).sizeAllocated>allocationForI && allocated.get(i).allocated==false)
			{
				diff=allocated.get(i).sizeAllocated-allocationForI;
				if(diff<min)
				{
					min=diff;
					index=i;
				}
			}
		}
		return index;
		
	}
	
	public int findn(int total_mem)
	{
		
		int comp=1;
		while(comp<total_mem)
		{
			comp=comp*2;
			twon++;
		}
		return (int)Math.pow(2,twon);
	}
	public void input()
	{
		System.out.println("Please enter the total memory that will be available in Kilobytes:");
		total_mem=sc.nextInt();
		System.out.println("Please enter the number of allocations/deallocations you want to do:");
		total_alloc=sc.nextInt();
		System.out.println("Please enter the Process ID, memory amount to allocate/deallocate "
				+ "\nand  '+' for allocation or '-' for deallocation "
				+ "\nin exact order as string. For eg : (A 20 +) or (B 35 -)");
		for(int i=0;i<total_alloc;i++)
		{
			Allocation al=new Allocation();
			al.processID=sc.next();
			al.alloc=sc.nextInt();
			al.request=sc.next();
			alloc_requests.add(al);
		}
	}
}
class Allocation
{
	int alloc;
	String request="";
	String processID="";
	
}
class MemoryAllocated
{
	String allocatedBy="";
	int sizeAllocated=0;
	boolean allocated=false;
}
/*
 * A 20 +
B 35 +
C 90 +
D 40 +
E 240 +
D 40 -
A 20 -
C 90 -
B 35 -
E 240 -*/
