package kr.co.yhs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        ObjectMapper om = new ObjectMapper();
        om.writeValueAsString(rm.findAll().get(0))
        return  ;
    }

    //    public
}
