package model.repository;

import java.util.concurrent.CompletionStage;

import com.google.inject.ImplementedBy;

import model.schema.Statistic;
import model.schema.Transaction;

/**
 * This interface provides a non-blocking API for possibly blocking operations.
 */
@ImplementedBy(TransactionRepositoryImpl.class)
public interface TransactionRepository {

	public CompletionStage<Statistic> add(Transaction transaction);

	public CompletionStage<Statistic> get();
}
