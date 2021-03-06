package kr.co.yhs.entity;

import kr.co.yhs.config.code.TRADE_STATE;
import kr.co.yhs.config.code.TradeStateConvert;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TRADE_LIST")
public class TradeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Long totalInvastingAmount;

    private LocalDateTime startAt;
    private LocalDateTime finishAt;
    private String status;


}
