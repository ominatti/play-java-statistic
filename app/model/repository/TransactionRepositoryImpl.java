package model.repository;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import model.schema.Statistic;
import model.schema.Transaction;
import play.cache.AsyncCacheApi;

public class TransactionRepositoryImpl implements TransactionRepository {

	private AsyncCacheApi cache;

	@Inject
	public TransactionRepositoryImpl(AsyncCacheApi cache) {
		this.cache = cache;
	}

	@Override
	public CompletionStage<Statistic> add(Transaction transaction) {
		CompletionStage<Statistic> statistic = cache.get("key");

		return statistic.thenApplyAsync(sta -> {
			Statistic staNew = sta == null ? new Statistic(transaction) : sta.update(transaction);
			cache.set("key", staNew);
			return staNew;			
		});
	}
	
	@Override
	public CompletionStage<Statistic> get() {
		CompletionStage<Statistic> statistic = cache.get("key");

		return statistic.thenApplyAsync(sta -> {			
			return sta == null ? new Statistic() : sta;
		});
	}

}
