package model.service;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import model.repository.TransactionRepository;
import model.schema.Statistic;
import model.schema.Transaction;

public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository repository;
	
    @Inject
    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }
    
	@Override
	public CompletionStage<Statistic> add(Transaction transaction) {
		return repository.add(transaction);
	}

	@Override
	public CompletionStage<Statistic> get() {
		return repository.get();
	}

}
