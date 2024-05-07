package io.github.jxch.market.index.dataviz;

import com.alibaba.fastjson2.JSONObject;
import io.github.jxch.market.index.config.MarketIndexAutoConfig;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Qualifier(MarketIndexAutoConfig.MARKET_INDEX_API)
public class DatavizGraphDataApi {
    private final OkHttpClient client;
    private final Request request;

    public DatavizGraphDataApi(@Qualifier(MarketIndexAutoConfig.OK_HTTP_CLIENT) OkHttpClient client,
                               @Qualifier(DatavizConfig.DATAVIZ_GRAPH_DATA_REQUEST) Request request) {
        this.request = request;
        this.client = client;
    }

    @SneakyThrows
    public DatavizGraphDataRes graphData() {
        try (Response response = client.newCall(request).execute()) {
            String jsonString = Objects.requireNonNull(response.body()).string();
            return JSONObject.parseObject(jsonString, DatavizGraphDataRes.class);
        }
    }

}
