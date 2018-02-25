package test;

import java.util.AbstractMap.SimpleEntry;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.LongestString;

public class LongestStringTest {
	
	LongestString ls;
	SimpleEntry<Integer, String> actualResult,expectedresult;
	
	@BeforeTest
	public void intialize(){
	ls=new LongestString();
	}
  
	@Parameters({ "sentence","longestWord","longestWordLength" })
	@Test
	public void testMethod(String sentence,String longestWord,Integer longestWordLength) {
		ls.setSentence(sentence);
		actualResult = ls.getLongestString();
		Assert.assertEquals(actualResult.getValue(), longestWord);
		Assert.assertEquals(actualResult.getKey(),longestWordLength);
  }
}
