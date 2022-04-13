package kr.co.yhs.dto.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AbleTradeDto {
    private long productId;
    private String title;
    private long totalInvestingAmount;
    private long nowInvestingAmount;
    private long traderCount;
    private String status;
    private LocalDateTime startAt;
    private LocalDateTime finishAt;

}
