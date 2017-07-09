package controllers;

import static play.libs.Json.toJson;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import model.util.StatisticCache;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

public class StatisticController extends Controller {
	
    private final HttpExecutionContext ec;

    @Inject
    public StatisticController(HttpExecutionContext ec) {
        this.ec = ec;
    }


    public CompletionStage<Result> get() {
    	return StatisticCache.get().thenApplyAsync( sta -> {
            return ok(toJson(sta));
        }, ec.current());
    }
}
