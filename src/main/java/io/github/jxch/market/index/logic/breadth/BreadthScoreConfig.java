package io.github.jxch.market.index.logic.breadth;

import okhttp3.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BreadthScoreConfig {
    public final static String BREADTH_SCORE_REQUEST = "BREADTH_SCORE_REQUEST";

    @Bean(BREADTH_SCORE_REQUEST)
    public Request breadthScoreRequest() {
        return new Request.Builder().url("https://www.trading-logic.com/index.html").get().build();
    }

}
