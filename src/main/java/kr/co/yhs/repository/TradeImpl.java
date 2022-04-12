package kr.co.yhs.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.yhs.entity.QTradeEntity;
import kr.co.yhs.entity.TradeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
@Repository
@AllArgsConstructor
public class TradeImpl implements TradeRepository{
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    @Override
    public List<TradeEntity> getTradeList() {
        return null;
    }
    /*
    
     */
    QTradeEntity tradeEntity = QTradeEntity.tradeEntity;

}
