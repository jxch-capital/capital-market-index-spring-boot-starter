package io.github.jxch.market.index.dataviz;

import com.alibaba.fastjson2.JSON;
import io.github.jxch.market.index.config.MarketIndexAutoConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(classes = MarketIndexAutoConfig.class)
class DatavizGraphDataApiTest {
    @Autowired
    private DatavizGraphDataApi datavizGraphDataApi;

    @Test
    void graphData() {
        DatavizGraphDataRes res = datavizGraphDataApi.graphData();
        log.info("{}", JSON.toJSONString(res));
    }

}