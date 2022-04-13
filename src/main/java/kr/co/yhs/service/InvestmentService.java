package kr.co.yhs.service;

import kr.co.yhs.config.code.RESPONSE_CODE;
import kr.co.yhs.config.code.TRADE_STATE;
import kr.co.yhs.dto.entity.AbleTradeDto;
import kr.co.yhs.dto.entity.MyTradeDto;
import kr.co.yhs.dto.entity.TradeDetailSum;
import kr.co.yhs.dto.request.InverstmentDto;
import kr.co.yhs.dto.result.ResultDto;
import kr.co.yhs.entity.TradeDetail;
import kr.co.yhs.entity.TradeEntity;
import kr.co.yhs.handler.exception.ServiceException;
import kr.co.yhs.mapper.CustomMapper;
import kr.co.yhs.dto.entity.TradeDto;
import kr.co.yhs.repository.RepositoryTradeDetail;
import kr.co.yhs.repository.RepositoryTradeList;
import kr.co.yhs.repository.TradeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@AllArgsConstructor
public class InvestmentService {
    final RepositoryTradeList repositoryTradeList;
    final RepositoryTradeDetail repositoryTradeDetail;
    final CustomMapper customMapper;
    final TradeRepository tradeRepository;

    public List<TradeDto> getAllTrade() {
        ModelMapper mm = customMapper.TradeDtoMapperForTradeList();
        return  repositoryTradeList.findAll().stream().map(unit-> mm.map(unit, TradeDto.class)).collect(Collectors.toList());
    }
    public ResultDto getAbleTrade() {

        log.info("get abble trade service");
        List<AbleTradeDto> list = tradeRepository.getAbleTrade();
        for(AbleTradeDto u:list)
            log.info("get trade select fnin={}", u.toString());
        ResultDto rd = ResultDto.success();
        rd.setTradeList(list);
//        rd.setTradeList(repositoryTradeList.findAbleTradeList(LocalDateTime.now()).stream().map(unit-> mm.map(unit, TradeDto.class)).collect(Collectors.toList()));
        return  rd;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResultDto tradeRequest(String userId, InverstmentDto dto)
    {
        Optional<TradeEntity> obj = repositoryTradeList.findById(dto.getProductIdLong());
        if( obj.isEmpty() )
            throw ServiceException.getServiceException(RESPONSE_CODE.R_21);

        TradeEntity tradeEntity = obj.get();
        log.info("거래 조회 성공 product_id={} status={}", tradeEntity.getId(), tradeEntity.getStatus());

        if( !tradeEntity.getStatus().equals(TRADE_STATE.ST01.getCd()) ) {
            throw ServiceException.getServiceException(RESPONSE_CODE.R_22);
        }


        List<TradeDetailSum> list = tradeRepository.getTradeAmount(dto.getProductIdLong());
        if( list.size() > 0)
        {
            TradeDetailSum tds = list.get(0);
            log.info("거래 내역 조회 성공 product_id={}, total_amount={}, trade_count={}", tradeEntity.getId(), tds.getTotalInverstmentAmount(), tds.getTradeCount());
            log.info("거래 한도 체크 able_amount={}", tradeEntity.getTotalInvastingAmount());
            log.info("거래 내역[{}]", tds.toString());

            if(tds.getTotalInverstmentAmount() >= tradeEntity.getTotalInvastingAmount() )
            {
                log.info("거래금액 초과");
                throw ServiceException.getServiceException(RESPONSE_CODE.R_22);
            }
            log.info("최종 거래 금액={}", tds.getTotalInverstmentAmount() + dto.getInverstmentAmountLong());
            if( tds.getTotalInverstmentAmount() + dto.getInverstmentAmountLong() >= tradeEntity.getTotalInvastingAmount() )
            {
                log.info("거래 상태 업데이트 ");
                tradeEntity.setStatus(TRADE_STATE.ST02.getCd());
            }
        }
        else{
            log.info("거래 한도 체크 통과 product_id={}", tradeEntity.getId());
        }



        TradeDetail tradeDetail = repositoryTradeDetail.save(getTradeEntity(tradeEntity, userId, dto.getInverstmentAmountLong()));
        log.info("거래 등록 성공 product_id={}, trade_dt={}, trade_count={}", tradeEntity.getId(), tradeDetail.getTradeDt());
        ResultDto resultDto = ResultDto.success();
        resultDto.setTradeDt(tradeDetail.getTradeDt());
        resultDto.setProductId(tradeDetail.getParentId());
        return resultDto;

    }

    public ResultDto getMyTrade(String userId)
    {
        List<MyTradeDto> list = tradeRepository.getMyTrade(userId);
        ResultDto resultDto;
        if( list.size() == 0 )
        {
            resultDto = ResultDto.fail(RESPONSE_CODE.R_23);
        }
        else{
            resultDto = ResultDto.success();
            resultDto.setMyTradeList(list);
        }
        return resultDto;
    }
    private TradeDetail getTradeEntity(TradeEntity entity, String userId, long amount)
    {
        TradeDetail tradeDetail = new TradeDetail();
        tradeDetail.setParentId(entity.getId());
        tradeDetail.setTradeAmount(amount);
        tradeDetail.setUserId(userId);
        tradeDetail.setTradeDt(LocalDateTime.now());
        return tradeDetail;
    }



    //    public
}
