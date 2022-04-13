package kr.co.yhs.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
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
import javax.persistence.Query;
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
        JPAQuery<AbleTradeDto> jpaQuery = jpaQueryFactory.from(tradeEntity)
                //.leftJoin(tradeDetail).on(tradeEntity.id.eq(tradeDetail.parentId))
                .where(
                        tradeEntity.status.eq(TRADE_STATE.ST01.getCd()),
                        tradeEntity.startAt.loe(LocalDateTime.now()),
                        tradeEntity.finishAt.gt(LocalDateTime.now())
                )
                .groupBy(tradeEntity.id)
                .select(
                        Projections.bean(AbleTradeDto.class,
                                tradeEntity.id.as("productId"),
                                tradeEntity.title.as("title"),
                                //tradeEntity.totalInvastingAmount.as("totalInvestingAmount"),
                                ExpressionUtils.as(JPAExpressions.select(tradeDetail.tradeAmount.sum()).from(tradeDetail).where(tradeDetail.parentId.eq(tradeEntity.id)), "totalInvestingAmount"),
                                ExpressionUtils.as(JPAExpressions.select(tradeDetail.tradeAmount.count()).from(tradeDetail).where(tradeDetail.parentId.eq(tradeEntity.id)), "nowInvestingAmount"),
                                //tradeDetail.tradeAmount.sum().as("nowInvestingAmount"),
                                tradeEntity.id.count().as("traderCount"),
                                tradeEntity.status.as("status"),
                                tradeEntity.startAt.as("startAt"),
                                tradeEntity.finishAt.as("finishAt")

                        )

                );
        return jpaQuery.fetch();
    }

    @Override
    public List<AbleTradeDto> getNativeAbleTrade() {
        String sql =
 "select l.id as productId,"+
 "       l.title as title,"+
 "       l.total_invasting_amount as totalInvestingAmount,"+
 "       (select sum(trade_amount) from trade_detail d where d.parent_id = l.id) as nowInvestingAmount,"+
 "       (select count(1) from trade_detail d where d.parent_id = l.id) as traderCount,"+
 "       l.status as status,"+
 "       l.start_at as startAt,"+
 "       l.finish_at as finishAt"+
 "  from trade_list l"+
 " where l.status='ST01'"+
 "   and l.finish_at<= ?"+
 "   and l.finish_at< ?";

        Query nativeQuery = entityManager.createNativeQuery(sql, AbleTradeDto.class)
                .setParameter(1, LocalDateTime.now())
                .setParameter(2, LocalDateTime.now());
        List<AbleTradeDto> list = nativeQuery.getResultList();
        return list;
    }
    //QTradeEntity tradeEntity = QTradeEntity.tradeEntity;

}
