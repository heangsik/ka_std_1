package kr.co.yhs.dto;

import kr.co.yhs.entity.TradeList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ResultDto {
    @Builder.Default
    private String resultCode = "00";
    @Builder.Default
    private String resultMsg = "success";
    private List<TradeList> tradeList;
}
