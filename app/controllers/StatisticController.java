package controllers;

import static play.libs.Json.toJson;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import model.service.TransactionService;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

public class StatisticController extends Controller {
	
    private final HttpExecutionContext ec;
    private final TransactionService service;
    
    @Inject
    public StatisticController(HttpExecutionContext ec, TransactionService service) {
        this.ec = ec;
        this.service = service;
    }


    public CompletionStage<Result> get() {
    	return service.get().thenApplyAsync( sta -> {
            return ok(toJson(sta));
        }, ec.current());
    }
}
