package model.schema;

import static org.junit.Assert.*;

import java.time.ZonedDateTime;

import org.junit.Test;

public class TransactionTest {

	@Test
	public void testAnInValidTimeStamp() {
		Long timeStamp = ZonedDateTime.now().minusMinutes(2).toInstant().toEpochMilli();
		Transaction transaction = new Transaction(10.0, timeStamp);
		
		assertFalse(transaction.isValid());
	}
	
	@Test
	public void testAValidTimeStamp() {
		Long timeStamp = ZonedDateTime.now().minusSeconds(20).toInstant().toEpochMilli();
		Transaction transaction = new Transaction(10.0, timeStamp);
		
		assertTrue(transaction.isValid());
	}

}
