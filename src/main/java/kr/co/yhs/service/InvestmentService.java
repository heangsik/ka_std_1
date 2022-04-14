package kr.co.yhs.service;

import kr.co.yhs.config.code.RESPONSE_CODE;
import kr.co.yhs.config.code.TRADE_STATE;
import kr.co.yhs.dto.entity.AbleTradeDto;
import kr.co.yhs.dto.entity.MyTradeDto;
import kr.co.yhs.dto.entity.TradeDetailSum;
import kr.co.yhs.dto.request.InverstmentDto;
import kr.co.yhs.dto.result.ResultDto;
import kr.co.yhs.entity.TradeDetailEntity;
import kr.co.yhs.entity.TradeEntity;
import kr.co.yhs.handler.exception.ServiceException;
import kr.co.yhs.config.CustomMapper;
import kr.co.yhs.dto.entity.TradeDto;
import kr.co.yhs.repository.RepositoryTradeDetail;
import kr.co.yhs.repository.RepositoryTradeList;
import kr.co.yhs.repository.TradeRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
    final TradeRepository queryTradeRepository;

    public List<TradeDto> getAllTrade() {
        ModelMapper mm = customMapper.TradeDtoMapperForTradeList();
        return  repositoryTradeList.findAll().stream().map(unit-> mm.map(unit, TradeDto.class)).collect(Collectors.toList());
    }
    public ResultDto getAbleTrade() {

        log.debug("get abble trade service");
        List<AbleTradeDto> list = queryTradeRepository.getAbleTrade();
        if(list.size() == 0)
            return ResultDto.fail(RESPONSE_CODE.R_23);

        for(AbleTradeDto u:list)
            log.debug("get trade select fnin={}", u.toString());
        ResultDto rd = ResultDto.success();
        rd.setTradeList(list);
//        rd.setTradeList(repositoryTradeList.findAbleTradeList(LocalDateTime.now()).stream().map(unit-> mm.map(unit, TradeDto.class)).collect(Collectors.toList()));
        return  rd;
    }

    public ResultDto tradeRequest(String userId, InverstmentDto requtesDto)
    {
        Optional<TradeEntity> obj = repositoryTradeList.findById(requtesDto.getProductIdLong());
        if( obj.isEmpty() )
            throw ServiceException.getServiceException(RESPONSE_CODE.R_21);

        TradeEntity tradeEntity = obj.get();
        log.info("거래 조회 성공 product_id={} status={}", tradeEntity.getId(), tradeEntity.getStatus());

        if( !tradeEntity.getStatus().equals(TRADE_STATE.ST01.getCd()) ) {
            throw ServiceException.getServiceException(RESPONSE_CODE.R_22);
        }
        InsertRequestDao iDao = InsertRequestDao.builder().userId(userId).tradeEntity(tradeEntity).requtesDto(requtesDto).build();
        return insertTrade(iDao);

    }
    
    @Transactional(isolation = Isolation.SERIALIZABLE)
    protected ResultDto insertTrade(InsertRequestDao iDao)
    {
        checkTradeTotalAmount(iDao.getTradeEntity(), iDao.getRequtesDto());

        TradeDetailEntity tradeDetail = repositoryTradeDetail.save(getTradeEntity(iDao.getTradeEntity(), iDao.getUserId(), iDao.getRequtesDto().getInverstmentAmountLong()));

        log.info("거래 등록 성공 product_id={}, trade_dt={}, trade_amount={}", iDao.getTradeEntity().getId(), tradeDetail.getTradeDt(), tradeDetail.getTradeAmount());
        ResultDto resultDto = ResultDto.success();
        resultDto.setTradeDt(tradeDetail.getTradeDt());
        resultDto.setProductId(tradeDetail.getParentId());
        return resultDto;
    }
    @Data
    @Builder
    static class InsertRequestDao{
        private String userId;
        private TradeEntity tradeEntity;
        private InverstmentDto requtesDto;
    }

    private void checkTradeTotalAmount(TradeEntity tradeEntity, InverstmentDto requtesDto)
    {

        List<TradeDetailSum> list = queryTradeRepository.getTradeAmount(requtesDto.getProductIdLong());

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
            log.info("최종 거래 금액={}", tds.getTotalInverstmentAmount() + requtesDto.getInverstmentAmountLong());
            if( tds.getTotalInverstmentAmount() + requtesDto.getInverstmentAmountLong() >= tradeEntity.getTotalInvastingAmount() )
            {
                log.info("거래 상태 업데이트 ");
                tradeEntity.setStatus(TRADE_STATE.ST02.getCd());
            }
        }
        else{
            log.info("거래 한도 체크 통과 product_id={}", tradeEntity.getId());
        }
    }

    public ResultDto getMyTrade(String userId)
    {
        List<MyTradeDto> list = queryTradeRepository.getMyTrade(userId);
        ResultDto resultDto;
        if( list.size() == 0 )
        {
            throw ServiceException.getServiceException(RESPONSE_CODE.R_23);
        }
        else{
            resultDto = ResultDto.success();
            resultDto.setTradeList(list);
        }
        return resultDto;
    }
    private TradeDetailEntity getTradeEntity(TradeEntity entity, String userId, long amount)
    {
        TradeDetailEntity tradeDetail = new TradeDetailEntity();
        tradeDetail.setParentId(entity.getId());
        tradeDetail.setTradeAmount(amount);
        tradeDetail.setUserId(userId);
        tradeDetail.setTradeDt(LocalDateTime.now());
        return tradeDetail;
    }



    //    public
}
