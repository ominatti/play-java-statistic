package model.service;

import java.util.concurrent.CompletionStage;

import com.google.inject.Singleton;

import model.schema.Statistic;
import model.schema.Transaction;
import model.util.StatisticCache;

@Singleton
public class TransactionServiceImpl implements TransactionService {
	
	@Override
	public CompletionStage<Statistic> add(Transaction transaction) {
		return StatisticCache.update(transaction);
	}

	@Override
	public CompletionStage<Statistic> get() {
		return StatisticCache.get();
	}

}
