package Demo;

import java.util.List;

public class DataReaders {
	public static void main(String[] args)
	{
		xlsReader();
	}

	public static void xlsReader()
	{
		String xname = "D:\\Selenium\\UserLogin.xls";
		String[][] records = Utilities.Excel.get(xname);
		//Review this
		for(String[] record: records)
		{
			System.out.println(record[0]);
			System.out.println(record[1]);
			System.out.println(record[2]);
		}
	}
	
	public static void csvReader()
	{
	String fname = "D:\\Selenium\\UserAccounts.csv";
	List <String[]> records = Utilities.CSV.get(fname);
	for(String[] record: records)
	{
		System.out.println(record);
		for(String value: record){
			System.out.println(value);
		}
	}
}

}
