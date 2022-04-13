package kr.co.yhs.dto.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TradeDto {
    private Long productId;

    private String title;

    private Long totalInvastingAmount;

    private LocalDateTime startAt;
    private LocalDateTime finishAt;

    private String status;
}
