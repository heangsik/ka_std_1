package kr.co.yhs.dto.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TradeDetailDto {
    private Long id;

    private Long parentId;
    private Long tradeAmount;
    private String userId;
    private LocalDateTime tradeDt;

}
