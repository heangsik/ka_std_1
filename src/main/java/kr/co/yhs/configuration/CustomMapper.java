package kr.co.yhs.configuration;

import kr.co.yhs.dto.TradeDto;
import kr.co.yhs.entity.TradeEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomMapper {

    private final ModelMapper modelMapper = new ModelMapper();
    @Bean
    public ModelMapper modelMapper(){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    public ModelMapper TradeDtoMapperForTradeList(){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addMappings(new PropertyMap<TradeEntity, TradeDto>() {
            @Override
            protected void configure(){
                map().setProduct_id(source.getId());
            }});
        return modelMapper;
    }


}
