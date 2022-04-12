package kr.co.yhs;

import kr.co.yhs.configuration.TRADE_STATE;
import kr.co.yhs.dto.TradeDto;
import kr.co.yhs.entity.TradeEntity;
import kr.co.yhs.repository.RepositoryTradeList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class StdTestModule {
    @Autowired
    RepositoryTradeList rm;
    @Autowired
    ModelMapper modelMapper;

    @DisplayName("Jpa insert test")
    void JpaInsert() {

        for(int i = 0 ; i < 10 ; i++)
        {
            TradeEntity tl = new TradeEntity();
            tl.setTitle("test trade "+i);
            TradeEntity stl = rm.save(tl);
        }

    }
    @Test
    void insertRawData()
    {

        TradeEntity tl = new TradeEntity();
        tl.setTitle("개인신용 포트폴리오");
        tl.setTotalInvastingAmount(1000000L);
        tl.setStartAt(LocalDateTime.of(2022, 04, 12, 0, 0));
        tl.setFinishAt(LocalDateTime.of(2022, 04, 13, 0, 0));
        rm.save(tl);
        TradeEntity tl2 = new TradeEntity();
        tl2.setTitle("부동산 포트폴리오");
        tl2.setTotalInvastingAmount(1000000L);
        tl2.setStartAt(LocalDateTime.of(2022, 04, 12, 0, 0));
        tl2.setFinishAt(LocalDateTime.of(2022, 04, 13, 0, 0));
        rm.save(tl2);

        List<TradeEntity> list = rm.findAll();
        Assertions.assertThat(list.size()).isEqualTo(2);


    }
    @Test
    @DisplayName("거래 테이블 dto 변환")
    void TradeListEntityToDto(){
        TradeEntity tl = new TradeEntity();
        tl.setTitle("개인신용 포트폴리오");
        tl.setTotalInvastingAmount(1000000L);
        tl.setStartAt(LocalDateTime.of(2022, 04, 12, 0, 0));
        tl.setFinishAt(LocalDateTime.of(2022, 04, 13, 0, 0));
        tl.setStatus(TRADE_STATE.ST01);
        rm.save(tl);
        TradeEntity tl2 = new TradeEntity();
        tl2.setTitle("개인신용 포트폴리오");
        tl2.setTotalInvastingAmount(1000000L);
        tl2.setStartAt(LocalDateTime.of(2022, 04, 12, 0, 0));
        tl2.setFinishAt(LocalDateTime.of(2022, 04, 13, 0, 0));
        tl2.setStatus(TRADE_STATE.ST01);
        rm.save(tl2);
        List<TradeEntity> list = rm.findAll();

        List<TradeDto> dlist = list.stream().map(unit-> modelMapper.map(unit, TradeDto.class)).collect(Collectors.toList());

        for(TradeDto td:dlist)
        {
            System.out.println(td.toString());
        }
//        dlist.stream().forEach(unit->System.out.println(unit.toString()));

    }
    @Test
    @DisplayName("select test")
    void JpaSelect(){
        JpaInsert();
        List<TradeEntity> op = rm.findByTitle("test trade 1");
        System.out.println(op.get(0).toString());
        Assertions.assertThat(op.size()).isEqualTo(1);
        List<TradeEntity> list = rm.findAll();
        for(TradeEntity u : list)
        {
            System.out.println(u.toString());
        }
    }

}
