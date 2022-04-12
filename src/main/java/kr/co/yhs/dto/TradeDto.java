package kr.co.yhs.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TradeDto {
    private Long product_id;

    private String title;

    private Long totalInvastingAmount;

    private LocalDateTime startAt;
    private LocalDateTime finishAt;

    private String status;
}
