package model.schema;

/** 
 * Thread-safe.
 */
public final class Statistic {
	
	private final Double avg;
	private final Double sum;
	private final Double max;
	private final Double min;
	private final Long count;
	
	public Statistic() {
		super();
		this.avg = 0d;
		this.sum = 0d;
		this.max = Double.NaN;
		this.min = Double.NaN;
		this.count = 0l;		
	}

	public Statistic(final Transaction transaction) {
		super();
		this.avg = transaction.amount;
		this.sum = transaction.amount;
		this.max = transaction.amount;
		this.min = transaction.amount;
		this.count = 1l;

	}
	
	public Statistic(Double avg, Double sum, Double max, Double min, Long count) {
		super();
		this.avg = avg;
		this.sum = sum;
		this.max = max;
		this.min = min;
		this.count = count;
	}
	
	public Double getAvg() {
		return avg;
	}

	public Double getSum() {
		return sum;
	}

	public Double getMax() {
		return max;
	}
	
	public Double getMin() {
		return min;
	}
	
	public Long getCount() {
		return count;
	}
		
	public synchronized Statistic update(final Transaction transaction) {
		Double sum = this.sum + transaction.amount;
		Double avg = sum  / (this.count + 1);
		Double max = ( this.max.isNaN() || this.max < transaction.amount ) ? transaction.amount : this.max;  
	    Double min = ( this.min.isNaN() || this.min > transaction.amount ) ? transaction.amount : this.min;

	    return new Statistic(avg, sum, max, min, this.count + 1 );
	}
}
