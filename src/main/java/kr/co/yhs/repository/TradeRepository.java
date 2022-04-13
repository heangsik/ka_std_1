package kr.co.yhs.repository;

import kr.co.yhs.dto.entity.AbleTradeDto;
import kr.co.yhs.dto.entity.TradeDetailSum;
import kr.co.yhs.entity.TradeEntity;

import java.util.List;

public interface TradeRepository {


    public List<TradeDetailSum> getTradeAmount(long parentId);

    public List<AbleTradeDto> getAbleTrade();

}
