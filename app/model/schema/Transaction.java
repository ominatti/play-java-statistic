package model.schema;

import java.time.Instant;
import java.time.ZonedDateTime;

import play.data.validation.Constraints.Required;

public class Transaction {

	@Required
	public Double amount;	
	@Required
	public Long timeStamp;

	public Transaction() {
		super();
	}

	public Transaction(Double amount, Long timeStamp) {
		super();
		this.amount = amount;
		this.timeStamp = timeStamp;
	}
	
	public Boolean isValid() {
   		Instant transactionTime = Instant.ofEpochMilli(this.timeStamp);
		Instant aMinAgo = ZonedDateTime.now().minusMinutes(1).toInstant();
		
		return transactionTime.isAfter(aMinAgo);
	}
}
