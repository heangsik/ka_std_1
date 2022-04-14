package kr.co.yhs.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TRADE_DETAIL")
public class TradeDetailEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentId;
    private Long tradeAmount;
    private String userId;
    private LocalDateTime tradeDt;

}
