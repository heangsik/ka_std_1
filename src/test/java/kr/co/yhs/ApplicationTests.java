package kr.co.yhs;

import kr.co.yhs.dto.entity.AbleTradeDto;
import kr.co.yhs.entity.TradeEntity;
import kr.co.yhs.repository.RepositoryTradeList;
import kr.co.yhs.repository.TradeRepository;
import kr.co.yhs.service.InvestmentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationTests {
	@Autowired
	RepositoryTradeList rm;
	@Autowired
	TradeRepository tradeRepository;
	@Autowired
	InvestmentService investmentService;


	@Test
	@DisplayName("Jpa insert test")
	private void insertJpa()
	{
		TradeEntity tl = new TradeEntity();

		tl.setTitle("test trade 1");
		TradeEntity stl = rm.save(tl);
		Assertions.assertThat(tl.getTitle()).isEqualTo(stl.getTitle());
	}

	@Test
	private void AbleTradeSelectTest(){

		List<AbleTradeDto> list = tradeRepository.getAbleTrade();
		Assertions.assertThat(list.size()).isGreaterThan(1);
	}
}
