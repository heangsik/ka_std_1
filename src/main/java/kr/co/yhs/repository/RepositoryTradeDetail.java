package kr.co.yhs.repository;

import kr.co.yhs.entity.TradeDetail;
import kr.co.yhs.entity.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RepositoryTradeDetail extends JpaRepository<TradeDetail, Long> {

    List<TradeDetail> findByParentId(String id);


//    List<TradeList> findByStartAtLessThanEqualAndFinishAtGreaterThanEqual(LocalDateTime now);
}
