package kr.co.yhs.dto.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.co.yhs.config.code.RESPONSE_CODE;
import kr.co.yhs.dto.entity.TradeDto;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private List<TradeDto> tradeList;
}
