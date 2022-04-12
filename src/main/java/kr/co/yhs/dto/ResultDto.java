package kr.co.yhs.dto;

import kr.co.yhs.entity.TradeList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ResultDto {
    private String resultCode = "00";
    private String resultMsg = "success";
    private List<TradeList> tradeList;
}
