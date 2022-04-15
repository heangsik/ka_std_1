package kr.co.yhs;

import kr.co.yhs.controller.InvestmentController;
import kr.co.yhs.service.InvestmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

//@SpringBootTest
//@WebMvcTest(InvestmentController.class)
//@DisplayName("Rest Api Test")
public class RestApiTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvestmentService investmentService;

//    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new InvestmentController(investmentService))
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }
    @Test
    void ableTradeSelect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/ableTrade"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(""));
    }
}
