package kr.co.yhs.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.yhs.config.code.TRADE_STATE;
import kr.co.yhs.dto.entity.AbleTradeDto;
import kr.co.yhs.dto.entity.MyTradeDto;
import kr.co.yhs.dto.entity.TradeDetailSum;
import kr.co.yhs.entity.QTradeDetailEntity;
import kr.co.yhs.entity.QTradeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
@Repository
@AllArgsConstructor
public class TradeImplByH2 implements TradeRepository{
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public List<TradeDetailSum> getTradeAmount(long parentId){
        QTradeDetailEntity qTradeDetail = QTradeDetailEntity.tradeDetailEntity;

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
        QTradeDetailEntity tradeDetail = QTradeDetailEntity.tradeDetailEntity;
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
                                ExpressionUtils.as(JPAExpressions.select(tradeDetail.tradeAmount.sum()).from(tradeDetail).where(tradeDetail.parentId.eq(tradeEntity.id)), "totalInvestingAmount"),
                                ExpressionUtils.as(JPAExpressions.select(tradeDetail.tradeAmount.count()).from(tradeDetail).where(tradeDetail.parentId.eq(tradeEntity.id)), "nowInvestingAmount"),
                                tradeEntity.id.count().as("traderCount"),
                                tradeEntity.status.as("status"),
                                tradeEntity.startAt.as("startAt"),
                                tradeEntity.finishAt.as("finishAt")

                        )

                );
        return jpaQuery.fetch();
    }

//    @Override
//    public List<AbleTradeDto> getNativeAbleTrade() {
//        String sql =
// "select l.id as productId,"+
// "       l.title as title,"+
// "       l.total_invasting_amount as totalInvestingAmount,"+
// "       (select sum(trade_amount) from trade_detail d where d.parent_id = l.id) as nowInvestingAmount,"+
// "       (select count(1) from trade_detail d where d.parent_id = l.id) as traderCount,"+
// "       l.status as status,"+
// "       l.start_at as startAt,"+
// "       l.finish_at as finishAt"+
// "  from trade_list l"+
// " where l.status='ST01'"+
// "   and l.finish_at<= ?"+
// "   and l.finish_at< ?";
//
//        Query nativeQuery = entityManager.createNativeQuery(sql, AbleTradeDto.class)
//                .setParameter(1, LocalDateTime.now())
//                .setParameter(2, LocalDateTime.now());
//        List<AbleTradeDto> list = nativeQuery.getResultList();
//        return list;
//    }

    @Override
    public List<MyTradeDto> getMyTrade(String userId) {
        QTradeDetailEntity tradeDetail = QTradeDetailEntity.tradeDetailEntity;
        QTradeEntity tradeEntity = QTradeEntity.tradeEntity;
        JPAQuery<MyTradeDto> jpaQuery = jpaQueryFactory.from(tradeEntity).join(tradeDetail)
                .on(tradeDetail.parentId.eq(tradeEntity.id))
                .where(
                        tradeDetail.userId.eq(userId)

                )
                .select(
                        Projections.bean(MyTradeDto.class,
                                tradeEntity.id.as("productId"),
                                tradeEntity.title.as("title"),
                                tradeEntity.totalInvastingAmount.as("totalInvastingAmount"),
                                tradeDetail.tradeAmount.as("tradeAmount"),
                                tradeDetail.tradeDt.as("tradeDt")
                        )
                );
        return jpaQuery.fetch();
    }
    //QTradeEntity tradeEntity = QTradeEntity.tradeEntity;

}
