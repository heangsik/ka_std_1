package kr.co.yhs.dto.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TradeDetailSum {
    private long totalInverstmentAmount;
    private long tradeCount;

}
