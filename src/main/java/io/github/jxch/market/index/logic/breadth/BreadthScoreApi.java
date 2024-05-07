package io.github.jxch.market.index.logic.breadth;

import com.alibaba.fastjson2.JSONObject;
import io.github.jxch.market.index.api.MarketIndexApi;
import io.github.jxch.market.index.config.MarketIndexAutoConfig;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Qualifier(MarketIndexAutoConfig.MARKET_INDEX_API)
public class BreadthScoreApi implements MarketIndexApi<Void, BreadthScoreRes> {
    private final OkHttpClient client;
    private final Request request;

    public BreadthScoreApi(@Qualifier(MarketIndexAutoConfig.OK_HTTP_CLIENT) OkHttpClient client,
                           @Qualifier(BreadthScoreConfig.BREADTH_SCORE_REQUEST) Request request) {
        this.request = request;
        this.client = client;
    }

    @SneakyThrows
    public BreadthScoreRes breadthScore() {
        try (Response response = client.newCall(request).execute()) {
            String html = Objects.requireNonNull(response.body()).string();
            String jsonString = Objects.requireNonNull(Jsoup.parse(html).select("script[data-for=river]").first()).data();
            return JSONObject.parseObject(jsonString, BreadthScoreRes.class);
        }
    }

    @Override
    public BreadthScoreRes index(Void unused) {
        return breadthScore();
    }

}
