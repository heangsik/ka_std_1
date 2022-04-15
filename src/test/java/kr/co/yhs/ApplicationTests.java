package kr.co.yhs;

import kr.co.yhs.config.code.TRADE_STATE;
import kr.co.yhs.dto.entity.AbleTradeDto;
import kr.co.yhs.dto.entity.TradeDetailDto;
import kr.co.yhs.dto.entity.TradeDetailSum;
import kr.co.yhs.dto.request.InverstmentDto;
import kr.co.yhs.dto.result.ResultDto;
import kr.co.yhs.entity.TradeDetailEntity;
import kr.co.yhs.entity.TradeEntity;
import kr.co.yhs.handler.exception.ServiceException;
import kr.co.yhs.config.CustomMapper;
import kr.co.yhs.repository.RepositoryTradeDetail;
import kr.co.yhs.repository.RepositoryTradeList;
import kr.co.yhs.repository.TradeRepository;
import kr.co.yhs.service.InvestmentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@DisplayName("서비스 테스트")
class ApplicationTests {
    @Autowired
    RepositoryTradeList rm;
    @Autowired
    RepositoryTradeDetail rtd;
    @Autowired
    TradeRepository tradeRepository;
    @Autowired
    InvestmentService investmentService;
    @Autowired
    RepositoryTradeDetail repositoryTradeDetail;
    @Autowired
    CustomMapper customMapper;

    void removeAll() {
        rm.deleteAll();
        rtd.deleteAll();

    }

    //	@Test
    void insertRawData() {
        LocalDate toDay = LocalDate.now();
        LocalTime zeroTime = LocalTime.of(00, 00, 00);
        LocalDateTime nowDt = LocalDateTime.of(toDay, zeroTime);
        LocalDateTime b1Dt = LocalDateTime.of(toDay.minusDays(1), zeroTime);
        LocalDateTime b2Dt = LocalDateTime.of(toDay.minusDays(2), zeroTime);
        LocalDateTime a1Dt = LocalDateTime.of(toDay.plusDays(1), zeroTime);
        LocalDateTime a2Dt = LocalDateTime.of(toDay.plusDays(2), zeroTime);
//		System.out.println(befor2Day);
//		System.out.println(befor1Day);
//		System.out.println(nowDt);
//		System.out.println(after1Day);
//		System.out.println(after2Day);

        TradeEntity tl = new TradeEntity();
        tl.setTitle("개인신용 포트폴리오 n ~ a1 st01");
        tl.setTotalInvastingAmount(100000l);
        tl.setStartAt(nowDt);
        tl.setFinishAt(a1Dt);
        tl.setStatus(TRADE_STATE.ST01.getCd());
        rm.save(tl);

        TradeEntity t2 = new TradeEntity();
        t2.setTitle("개인신용 포트폴리오 b1 ~ a1 st01");
        t2.setTotalInvastingAmount(100000l);
        t2.setStartAt(b1Dt);
        t2.setFinishAt(a1Dt);
        t2.setStatus(TRADE_STATE.ST01.getCd());
        rm.save(t2);

        TradeEntity t3 = new TradeEntity();
        t3.setTitle("개인신용 포트폴리오 b1 ~ a2 st01");
        t3.setTotalInvastingAmount(100000l);
        t3.setStartAt(b1Dt);
        t3.setFinishAt(a2Dt);
        t3.setStatus(TRADE_STATE.ST01.getCd());
        rm.save(t3);

        TradeEntity t4 = new TradeEntity();
        t4.setTitle("개인신용 포트폴리오 b1 ~ a2 st02");
        t4.setTotalInvastingAmount(100000l);
        t4.setStartAt(b1Dt);
        t4.setFinishAt(a2Dt);
        t4.setStatus(TRADE_STATE.ST02.getCd());
        rm.save(t4);

        TradeEntity t5 = new TradeEntity();
        t5.setTitle("개인신용 포트폴리오 a1 ~ a2 st01");
        t5.setTotalInvastingAmount(100000l);
        t5.setStartAt(a1Dt);
        t5.setFinishAt(a2Dt);
        t5.setStatus(TRADE_STATE.ST01.getCd());
        rm.save(t5);

//		List<TradeEntity> list = rm.findAll();
//		list.forEach(u-> System.out.println(u.toString()));
//		Assertions.assertThat(list.size()).isEqualTo(5);


    }

    @Test
    @DisplayName("단위-거래 가능한 데이터 조회 성공")
    void selectAbleTrade() {
        removeAll();
        insertRawData();

        List<AbleTradeDto> list = tradeRepository.getAbleTrade();
        System.out.println("조회된 거래 =" + list.size());
        list.forEach(u -> System.out.println(u.toString()));
        Assertions.assertThat(list.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("단위-거래 가능한 데이터 조회 0건")
    void selectAbleTradeNoCount() {
        removeAll();
        List<AbleTradeDto> list = tradeRepository.getAbleTrade();
        System.out.println("조회된 거래 =" + list.size());
        Assertions.assertThat(list.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("서비스-거래 가능한 목록 조회")
    void serviceSelectAbleTrade() {
        removeAll();
        insertRawData();
        ResultDto resultDto = investmentService.getAbleTrade();
        System.out.println("응답코드 =" + resultDto.getResultCode());
        System.out.println(resultDto.toString());
        Assertions.assertThat(resultDto.getResultCode()).isEqualTo("00");
        Assertions.assertThat(resultDto.getTradeList().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("서비스-거래 가능한 목록 조회 내용 없음")
    void serviceSelectAbleTradeNoData() {
        removeAll();
        ResultDto resultDto = investmentService.getAbleTrade();
        System.out.println("응답코드 =" + resultDto.getResultCode());
        System.out.println(resultDto.toString());
        Assertions.assertThat(resultDto.getResultCode()).isEqualTo("23");
    }

    @Test
    @DisplayName("단위-거래 데이터 찾기 by id ")
    void findTradeById() {
        removeAll();

        TradeEntity rtl = insertOneData();

        Optional<TradeEntity> obj = rm.findById(rtl.getId());
        Assertions.assertThat(obj.isEmpty()).isEqualTo(false);

        TradeEntity stl = obj.get();

        Assertions.assertThat(rtl.getTitle()).isEqualTo(stl.getTitle());
    }

    TradeEntity insertOneData() {
        return insertOneData("개인신용 포트폴리오 n ~ a1 st01", 100000l);
    }

    TradeEntity insertOneData(String tradeNm, long amount) {
        return insertOneData(tradeNm, amount, LocalDateTime.of(LocalDate.now(), LocalTime.of(00, 00)), LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(00, 00)));
    }

    TradeEntity insertOneData(String tradeNm, long amount, LocalDateTime startAt, LocalDateTime finishAt) {
        TradeEntity tl = new TradeEntity();
        tl.setTitle(tradeNm);
        tl.setTotalInvastingAmount(amount);
        tl.setStartAt(startAt);
        tl.setFinishAt(finishAt);
        tl.setStatus(TRADE_STATE.ST01.getCd());
        TradeEntity rtl = rm.save(tl);
        return rtl;
    }

    @Test
    @DisplayName("단위-거래 데이터 찾기 by id 데이터 없음")
    void findTradeByIdNotFound() {
        removeAll();
        insertOneData();
        Optional<TradeEntity> obj = rm.findById(111l);
        Assertions.assertThat(obj.isEmpty()).isEqualTo(true);
    }

    @Test
    @DisplayName("단위-거래내역 조회 데이터 없음")
    void selectTradeRequestListNoData() {
        removeAll();
        List<TradeDetailSum> tradeAmount = tradeRepository.getTradeAmount(1);
        Assertions.assertThat(tradeAmount.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("단위-투자 거래요청 DB")
    void requestTrade() {
        removeAll();
        TradeEntity te = insertOneData();
        List<TradeDetailSum> tradeAmount = tradeRepository.getTradeAmount(1);
        TradeDetailEntity btd = getTradeEntity(te, "user_id", 2000l);
        TradeDetailEntity rtd = repositoryTradeDetail.save(btd);
        ModelMapper mm = customMapper.modelMapper();
        TradeDetailDto tde = mm.map(rtd, TradeDetailDto.class);
        Optional<TradeDetailEntity> std = repositoryTradeDetail.findById(rtd.getId());
        System.out.println("btd obj=" + btd.toString());
        System.out.println("rtd obj=" + tde.toString());
        System.out.println("std obj=" + std.get().toString());
        Assertions.assertThat(btd.getTradeAmount()).isEqualTo(std.get().getTradeAmount());
        Assertions.assertThat(btd.getUserId()).isEqualTo(std.get().getUserId());
    }


    @Test
    @DisplayName("단위-거래내역 조회")
    void selectTradeRequestListNo() {
        removeAll();
        TradeEntity te = insertOneData();
        List<TradeDetailSum> tradeAmount = tradeRepository.getTradeAmount(1);
        TradeDetailEntity btd1 = getTradeEntity(te, "user_id", 2000l);
        TradeDetailEntity btd2 = getTradeEntity(te, "user_id", 2000l);
        TradeDetailEntity btd3 = getTradeEntity(te, "user_id", 2000l);
        TradeDetailEntity rtd1 = repositoryTradeDetail.save(btd1);
        TradeDetailEntity rtd2 = repositoryTradeDetail.save(btd2);
        TradeDetailEntity rtd3 = repositoryTradeDetail.save(btd3);

        List<TradeDetailEntity> list = repositoryTradeDetail.findByParentId(te.getId());
        System.out.println("거래내역 조회=" + list.size());
        list.forEach(u -> System.out.println(u.toString()));
        Assertions.assertThat(list.size()).isEqualTo(3);

    }

    @Test
    @DisplayName("서비스-투자 요청")
    void selectServiceTradeRequestList() {
        removeAll();
        TradeEntity tradeEntity = insertOneData();
        InverstmentDto dto = new InverstmentDto();
        dto.setProductId(String.valueOf(tradeEntity.getId()));
        dto.setInverstmentAmount("2000");

        ResultDto resultDto = investmentService.tradeRequest("test_id", dto);

        System.out.println("투자 요청=" + resultDto.toString());
        Assertions.assertThat(resultDto.getResultCode()).isEqualTo("00");

    }

    @Test
    @DisplayName("서비스-투자 요청(투자 요청한 거래 없음)")
    void selectServiceTradeRequestListFailException() {
        removeAll();
        InverstmentDto dto = new InverstmentDto();
        dto.setProductId("11");
        dto.setInverstmentAmount("2000");


        org.junit.jupiter.api.Assertions.assertThrows(ServiceException.class, () -> {
            investmentService.tradeRequest("test_id", dto);
        });

    }


    @Test
    @DisplayName("서비스-투자 요청 거래 가능 일자 아님")
    void selectServiceTradeRequestListNotAbleDate() {
        removeAll();
        TradeEntity tradeEntity = insertOneData("일자 초과거래", 20000, LocalDateTime.of(LocalDate.now().minusDays(3), LocalTime.of(00, 00)), LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(00, 00)));
        InverstmentDto dto = new InverstmentDto();
        dto.setProductId(String.valueOf(tradeEntity.getId()));
        dto.setInverstmentAmount("2000");


        org.junit.jupiter.api.Assertions.assertThrows(ServiceException.class, () -> {
            investmentService.tradeRequest("test_id", dto);
        });

    }


    @Test
    @DisplayName("서비스-투자 요청 금액 초과")
    void selectServiceTradeRequestListOverAmount() {
        removeAll();
        TradeEntity tradeEntity = insertOneData();
        InverstmentDto dto = new InverstmentDto();
        dto.setProductId(String.valueOf(tradeEntity.getId()));
        dto.setInverstmentAmount("22000");

        investmentService.tradeRequest("test_id", dto);
        investmentService.tradeRequest("test_id", dto);
        investmentService.tradeRequest("test_id", dto);
        investmentService.tradeRequest("test_id", dto);
        investmentService.tradeRequest("test_id", dto);
        org.junit.jupiter.api.Assertions.assertThrows(ServiceException.class, () -> {
            investmentService.tradeRequest("test_id", dto);
        });
    }

    TradeDetailEntity getTradeEntity(TradeEntity entity, String userId, long amount) {
        TradeDetailEntity tradeDetail = new TradeDetailEntity();
        tradeDetail.setParentId(entity.getId());
        tradeDetail.setTradeAmount(amount);
        tradeDetail.setUserId(userId);
        tradeDetail.setTradeDt(LocalDateTime.now());
        return tradeDetail;
    }

    @Test
    @DisplayName("서비스-나의 거래 내역 조회")
    void selectMyTrade() {
        removeAll();
        TradeEntity tradeEntity1 = insertOneData();
        TradeEntity tradeEntity2 = insertOneData("거래 2", 200000);
        InverstmentDto dto1 = new InverstmentDto();
        dto1.setProductId(String.valueOf(tradeEntity1.getId()));
        dto1.setInverstmentAmount("22000");
        investmentService.tradeRequest("test_id", dto1);
        dto1.setInverstmentAmount("22200");
        investmentService.tradeRequest("test_id", dto1);

        InverstmentDto dto2 = new InverstmentDto();
        dto2.setProductId(String.valueOf(tradeEntity2.getId()));
        dto2.setInverstmentAmount("11000");
        investmentService.tradeRequest("test_id", dto2);
        dto2.setInverstmentAmount("11100");
        investmentService.tradeRequest("test_id", dto2);

        ResultDto resultDto = investmentService.getMyTrade("test_id");
        System.out.println("내 거래내역 조회:" + resultDto.toString());
        Assertions.assertThat(resultDto.getResultCode()).isEqualTo("00");
    }

    @Test
    @DisplayName("서비스-나의 거래 내역 조회 내역 없음")
    void selectMyTradeNoData() {
        removeAll();
        TradeEntity tradeEntity1 = insertOneData();

        org.junit.jupiter.api.Assertions.assertThrows(ServiceException.class, () -> {
            investmentService.getMyTrade("test_id");
        });
    }
}
