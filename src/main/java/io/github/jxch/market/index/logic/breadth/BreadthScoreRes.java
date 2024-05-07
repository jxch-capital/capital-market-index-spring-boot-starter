package io.github.jxch.market.index.logic.breadth;

import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
public class BreadthScoreRes {
    private X x;

    public List<List<String>> getRawData() {
        return x.getOpts().getSeries().get(0).getData();
    }

    public List<BreadthScoreItem> getItemList() {
        return getRawData().stream().map(item -> BreadthScoreItem.builder()
                .date(DateUtil.parseDate(item.get(0)).toLocalDateTime().toLocalDate())
                .type(item.get(2))
                .score(Integer.valueOf(item.get(1).trim()))
                .build()).toList();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class X {
        private Opts opts;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class Opts {
        private List<Series> series;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class Series {
        private List<List<String>> data;
    }
}
