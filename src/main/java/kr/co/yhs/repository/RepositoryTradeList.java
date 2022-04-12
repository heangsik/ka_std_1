package kr.co.yhs.repository;

import kr.co.yhs.entity.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RepositoryTradeList extends JpaRepository<TradeEntity, Long> {

    List<TradeEntity> findByTitle(String title);

    @Query("select t from TradeEntity t where t.startAt <= :now_dt and t.finishAt >= :now_dt")
    List<TradeEntity> findAbleTradeList(@Param("now_dt") LocalDateTime _nowTime);

//    List<TradeList> findByStartAtLessThanEqualAndFinishAtGreaterThanEqual(LocalDateTime now);
}
