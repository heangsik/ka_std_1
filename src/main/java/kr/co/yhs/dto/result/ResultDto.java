package kr.co.yhs.dto.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import kr.co.yhs.config.code.RESPONSE_CODE;
import kr.co.yhs.dto.entity.AbleTradeDto;
import kr.co.yhs.dto.entity.MyTradeDto;
import kr.co.yhs.dto.entity.TradeDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResultDto {
    public static ResultDto fail(RESPONSE_CODE code)
    {
        return new ResultDto(code);
    }
    public static ResultDto success()
    {
        return  new ResultDto(RESPONSE_CODE.R_00);
    }
    private ResultDto(RESPONSE_CODE code)
    {
        resultCode = code.getCode();
        resultMsg = code.getDesc();
    }
    private String resultCode;
    private String resultMsg;
    private List<AbleTradeDto> tradeList;
    private List<TradeDto> allTradeList;
    private List<MyTradeDto> myTradeList;
    private LocalDateTime tradeDt;
    private long productId;
}
