package kr.co.yhs;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.yhs.config.code.TRADE_STATE;
import kr.co.yhs.entity.TradeEntity;
import kr.co.yhs.repository.RepositoryTradeList;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@DisplayName("Rest Api Test")
public class RestApiTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    RepositoryTradeList rm;

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
    void ableTradeSelect() throws Exception {
        System.out.println(this.mockMvc);
        insertRawData();
        String request = "{\"user_id\":\"user_test_id\"}";
        ObjectMapper om = new ObjectMapper();
        postObj po = new postObj();
        po.setAmount("2000");
        po.setUserId("TestId");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ableTrade")
                .content(om.writeValueAsString(po))
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-USER-ID", "header_user_id")
                .accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN)

//                .accept(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8), MediaType.TEXT_PLAIN)
                .characterEncoding(StandardCharsets.UTF_8);
        MockHttpServletResponse mockHttpServletResponse = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        log.info(mockHttpServletResponse.getContentAsString());
        ;

    }

    @Data
    static class postObj {
        String amount;
        String userId;
        public void setUserId(String _v)
        {
            this.userId = _v;
        }
        public String getUserId(){
            return userId;
        }
        public void setAmount(String v){
            amount = v;
        }
        public String getAmount(){
            return amount;
        }
    }

}

