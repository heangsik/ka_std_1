package kr.co.yhs.entity;

import kr.co.yhs.configuration.TRADE_STATE;
import kr.co.yhs.configuration.TradeStateConvert;
import lombok.Data;
import lombok.Generated;

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
    @Convert(converter = TradeStateConvert.class)
    @Column(name = "STATUS")
    private TRADE_STATE status;


}
