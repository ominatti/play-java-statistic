package controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import model.schema.Transaction;
import model.util.StatisticCache;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

public class TransactionController extends Controller {

    private final FormFactory formFactory;
    private final HttpExecutionContext ec;

    @Inject
    public TransactionController(FormFactory formFactory, HttpExecutionContext ec) {
        this.formFactory = formFactory;
        this.ec = ec;
    }

    public Result index() {
    	return ok();
    }
    
    public CompletionStage<Result> addTransaction() {    	
    	Form<Transaction> form = formFactory.form(Transaction.class).bindFromRequest();
    	
    	if(form.hasErrors()){
    		return CompletableFuture.supplyAsync(()	-> badRequest());
    	} else {
    		Transaction transaction = form.get();
    		if(transaction.isValid()){
    			return StatisticCache.update(transaction).thenApplyAsync( s -> {    				
    				return ok();
    			}, ec.current());
    		} else {
    			return CompletableFuture.supplyAsync(()	-> noContent());
    		}
    	}
    }
}