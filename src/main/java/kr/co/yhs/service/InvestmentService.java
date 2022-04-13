package kr.co.yhs.service;

import kr.co.yhs.mapper.CustomMapper;
import kr.co.yhs.dto.entity.TradeDto;
import kr.co.yhs.repository.RepositoryTradeList;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvestmentService {
    final RepositoryTradeList rm;
    final CustomMapper customMapper;

    public List<TradeDto> getAllTrade() {
        ModelMapper mm = customMapper.TradeDtoMapperForTradeList();
        return  rm.findAll().stream().map(unit-> mm.map(unit, TradeDto.class)).collect(Collectors.toList());
    }
    public List<TradeDto> getAbleTrade() {
        ModelMapper mm = customMapper.TradeDtoMapperForTradeList();
        return  rm.findAbleTradeList(LocalDateTime.now()).stream().map(unit-> mm.map(unit, TradeDto.class)).collect(Collectors.toList());
    }

    //    public
}
