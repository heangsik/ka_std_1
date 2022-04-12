package kr.co.yhs.repository;

import kr.co.yhs.entity.TradeEntity;

import java.util.List;

public interface TradeRepository {
    List<TradeEntity> getTradeList();
}
