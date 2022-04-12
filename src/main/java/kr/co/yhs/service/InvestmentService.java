package kr.co.yhs.service;

import kr.co.yhs.entity.TradeList;
import kr.co.yhs.repository.RepositoryTradeList;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvestmentService {
    final RepositoryTradeList rm;

    public List<TradeList> getAbleTrade() {
        return  rm.findAll();
    }

    //    public
}
