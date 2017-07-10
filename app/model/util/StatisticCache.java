package model.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.google.inject.Singleton;

import model.schema.Statistic;
import model.schema.Transaction;

@Singleton
public class StatisticCache {
	private volatile static Statistic statistic = new Statistic();
	private static ReadWriteLock lock = new ReentrantReadWriteLock();
	
	private StatisticCache() {}
	
	public static CompletionStage<Statistic> update(Transaction transaction) {
		return CompletableFuture.supplyAsync(() -> {
			lock.writeLock().lock();
			try {			
				statistic = statistic.update(transaction); 
				return statistic;
		    } finally {
		        lock.writeLock().unlock();
		    }
		});
	}
	
	public static CompletionStage<Statistic> get() {
		return CompletableFuture.supplyAsync(() -> {
			lock.readLock().lock();	
			try {
				return statistic;
			} finally {
				lock.readLock().unlock();
			} 
		});
	}
}
