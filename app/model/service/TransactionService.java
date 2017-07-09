package model.service;

import java.util.concurrent.CompletionStage;

import com.google.inject.ImplementedBy;

import model.schema.Statistic;
import model.schema.Transaction;

/**
 * This interface provides a non-blocking API for possibly blocking operations.
 */
@ImplementedBy(TransactionServiceImpl.class)
public interface TransactionService {
	public CompletionStage<Statistic> add(Transaction transaction);
	public CompletionStage<Statistic> get();
}
