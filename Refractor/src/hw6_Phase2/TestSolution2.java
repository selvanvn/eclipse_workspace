package hw6_Phase2;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hw6.Solution;

public class TestSolution2 {
	private Solution2 sJunit;
	LocalDate target1 = LocalDate.of(2000, 01, 01); //(2000-01-01);
	LocalDate target2 = LocalDate.of(2016, 10, 13);

	@Before
	public void setUp() throws Exception {
		this.sJunit = new Solution2();
	}

	@After
	public void tearDown() throws Exception {
		sJunit = null;
	}
	@Test
	public void testdays_between()
	{
		assertEquals("Test1","There are 6130 days between 2000-01-01 and 2016-10-13",sJunit.date_diff(target1, target2,1));
	}
	@Test
	public void testmonths_between()
	{
		assertEquals("Test2","There are 201 months between 2000-01-01 and 2016-10-13",sJunit.date_diff(target1, target2,2));
	}
	@Test
	public void testyear_between()
	{
		assertEquals("Test3","There are 16 years between 2000-01-01 and 2016-10-13",sJunit.date_diff(target1, target2,3));
	}
	@Test
	public void test0days_between()
	{
		assertEquals("Test4","There are 0 days between 2000-01-01 and 2000-01-01",sJunit.date_diff(target1, target1,1));
	}
	@Test
	public void test0months_between()
	{
		assertEquals("Test5","There are 0 months between 2000-01-01 and 2000-01-01",sJunit.date_diff(target1, target1,2));
	}
	@Test
	public void test0year_between()
	{
		assertEquals("Test6","There are 0 years between 2000-01-01 and 2000-01-01",sJunit.date_diff(target1, target1,3));
	}
	@Test
	public void testunit_between()
	{
		assertEquals("Test","Enter Valid Unit",sJunit.date_diff(target1, target1,4));
	}
		
}
