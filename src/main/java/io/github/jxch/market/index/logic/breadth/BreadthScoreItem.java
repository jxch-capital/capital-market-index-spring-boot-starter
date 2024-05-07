package io.github.jxch.market.index.logic.breadth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BreadthScoreItem {
    private LocalDate date;
    private String type;
    private Integer score;
}
