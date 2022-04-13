package kr.co.yhs.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.yhs.config.code.TRADE_STATE;
import kr.co.yhs.dto.entity.AbleTradeDto;
import kr.co.yhs.dto.entity.TradeDetailSum;
import kr.co.yhs.entity.QTradeDetail;
import kr.co.yhs.entity.QTradeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
@Repository
@AllArgsConstructor
public class TradeImpl implements TradeRepository{
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public List<TradeDetailSum> getTradeAmount(long parentId){
        QTradeDetail qTradeDetail = QTradeDetail.tradeDetail;

        JPAQuery<TradeDetailSum> jpaQuery = jpaQueryFactory.from(qTradeDetail)
                .where(
                        qTradeDetail.parentId.eq(parentId)
                )
                .groupBy(qTradeDetail.parentId)
                .select(
                        Projections.bean(TradeDetailSum.class,
                                qTradeDetail.tradeAmount.sum().as("totalInverstmentAmount"),
                                qTradeDetail.parentId.count().as("tradeCount")
                        )
                );
        return jpaQuery.fetch();
    }

    @Override
    public List<AbleTradeDto> getAbleTrade() {
        QTradeDetail tradeDetail = QTradeDetail.tradeDetail;
        QTradeEntity tradeEntity = QTradeEntity.tradeEntity;
        JPAQuery<AbleTradeDto> jpaQuery = jpaQueryFactory.from(tradeEntity).join(tradeDetail)
                .where(
                        tradeEntity.id.eq(tradeDetail.parentId),
                        tradeEntity.status.eq(TRADE_STATE.ST01),
                        tradeEntity.finishAt.loe(LocalDateTime.now()),
                        tradeEntity.finishAt.gt(LocalDateTime.now())
                )
                .groupBy(tradeEntity.id)
                .select(
                        Projections.bean(AbleTradeDto.class,
                                tradeEntity.id.as("productId"),
                                tradeEntity.title.as("title"),
                                tradeEntity.totalInvastingAmount.as("totalInvestingAmount"),
                                tradeDetail.tradeAmount.sum().as("nowInvestingAmount"),
                                tradeEntity.id.count().as("traderCount"),
                                tradeEntity.status.as("status"),
                                tradeEntity.startAt.as("startAt"),
                                tradeEntity.finishAt.as("finishAt")

                        )

                );
        return null;
    }
    //QTradeEntity tradeEntity = QTradeEntity.tradeEntity;

}
