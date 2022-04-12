package kr.co.yhs.repository;

import kr.co.yhs.entity.TradeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryTradeList extends JpaRepository<TradeList, Long> {

    List<TradeList> findByTitle(String title);


}
