package io.github.jxch.market.index.dataviz;

import okhttp3.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatavizConfig {
    public final static String DATAVIZ_GRAPH_DATA_REQUEST = "DATAVIZ_GRAPH_DATA_REQUEST";

    @Bean(DATAVIZ_GRAPH_DATA_REQUEST)
    public Request datavizGraphDataRequest() {
        return new Request.Builder()
                .url("https://production.dataviz.cnn.io/index/fearandgreed/graphdata")
                .addHeader("sec-ch-ua", "\"Google Chrome\";v=\"123\", \"Not:A-Brand\";v=\"8\", \"Chromium\";v=\"123\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36")
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .addHeader("Sec-Fetch-Site", "none")
                .addHeader("Sec-Fetch-Mode", "navigate")
                .addHeader("Sec-Fetch-User", "?1")
                .addHeader("Sec-Fetch-Dest", "document")
                .addHeader("host", "production.dataviz.cnn.io")
                .get()
                .build();
    }

}
