package TestProjects;

import org.apache.logging.log4j.*;
import org.testng.annotations.Test;

public class Test1 {
	
	Logger log= LogManager.getLogger(Test1.class.getName());
	
	@Test
	public void test1Method1()
	{
		System.out.println("test1Method1");
		log.debug("Debug Message : testmethod1");
		log.error("Error msg");	}
	@Test
	public void test1Method2()
	{
		System.out.println("test1Method2");
		log.fatal("fatal msg");
	}

}
