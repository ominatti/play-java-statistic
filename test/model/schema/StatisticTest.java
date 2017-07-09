package model.schema;

import static org.junit.Assert.*;

import org.junit.Test;

public class StatisticTest {

	@Test
	public void testInitialization() {
		Statistic sta = new Statistic();		
		assertEquals(sta.getAvg(), new Double(0.0));
		assertEquals(sta.getSum(), new Double(0.0));
		assertTrue(sta.getMax().isNaN());
		assertTrue(sta.getMin().isNaN());
		assertEquals(sta.getCount(), new Long(0));
	}
	
	@Test
	public void testUpdating() {
		Statistic sta = new Statistic(10.0, 20.0, 10.0, 10.0, 2l);
		
		Transaction transaction = new Transaction(40.0, 12l);
		Statistic staNew = sta.update(transaction);
		
		assertEquals(staNew.getAvg(), new Double(20.0));
		assertEquals(staNew.getSum(), new Double(60.0));
		assertEquals(staNew.getMax(), new Double(40.0));
		assertEquals(staNew.getMin(), new Double(10.0));
		assertEquals(staNew.getCount(), new Long(3));
	}
}
