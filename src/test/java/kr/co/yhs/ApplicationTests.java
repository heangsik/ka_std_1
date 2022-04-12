package kr.co.yhs;

import kr.co.yhs.entity.TradeList;
import kr.co.yhs.repository.RepositoryTradeList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
	@Autowired
	RepositoryTradeList rm;


	@Test
	@DisplayName("Jpa insert test")
	private void insertJpa()
	{
		TradeList tl = new TradeList();

		tl.setTitle("test trade 1");
		TradeList stl = rm.save(tl);
		Assertions.assertThat(tl.getTitle()).isEqualTo(stl.getTitle());
	}

}
