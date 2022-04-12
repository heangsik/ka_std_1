package kr.co.yhs;

import kr.co.yhs.entity.TradeList;
import kr.co.yhs.repository.RepositoryTradeList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class StdTestModule {
    @Autowired
    RepositoryTradeList rm;

    @DisplayName("Jpa insert test")
    void JpaInsert() {

        for(int i = 0 ; i < 10 ; i++)
        {
            TradeList tl = new TradeList();
            tl.setTitle("test trade "+i);
            TradeList stl = rm.save(tl);
        }

    }
    @Test
    void insertRawData()
    {

        TradeList tl = new TradeList();
        tl.setTitle("개인신용 포트폴리오");
        tl.setTotalInvastingAmount(1000000L);
        tl.setStartAt(LocalDateTime.of(2022, 04, 12, 0, 0));
        tl.setFinishAt(LocalDateTime.of(2022, 04, 13, 0, 0));
        rm.save(tl);
        TradeList tl2 = new TradeList();
        tl2.setTitle("개인신용 포트폴리오");
        tl2.setTotalInvastingAmount(1000000L);
        tl2.setStartAt(LocalDateTime.of(2022, 04, 12, 0, 0));
        tl2.setFinishAt(LocalDateTime.of(2022, 04, 13, 0, 0));
        rm.save(tl2);

        List<TradeList> list = rm.findAll();
        Assertions.assertThat(list.size()).isEqualTo(2);


    }
    @Test
    @DisplayName("select test")
    void JpaSelect(){
        JpaInsert();
        List<TradeList> op = rm.findByTitle("test trade 1");
        System.out.println(op.get(0).toString());
        Assertions.assertThat(op.size()).isEqualTo(1);
        List<TradeList> list = rm.findAll();
        for(TradeList u : list)
        {
            System.out.println(u.toString());
        }
    }

}
