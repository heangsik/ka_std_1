package kr.co.yhs.repository;

import kr.co.yhs.entity.TradeDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryTradeDetail extends JpaRepository<TradeDetailEntity, Long> {

    List<TradeDetailEntity> findByParentId(long id);

    List<TradeDetailEntity> findByUserId(String userId);


//    List<TradeList> findByStartAtLessThanEqualAndFinishAtGreaterThanEqual(LocalDateTime now);
}
