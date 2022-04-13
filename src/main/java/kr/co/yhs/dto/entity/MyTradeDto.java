package kr.co.yhs.dto.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MyTradeDto {
    private long productId;
    private String title;
    private long totalInvastingAmount;
    private long tradeAmount;
    private LocalDateTime tradeDt;

}
