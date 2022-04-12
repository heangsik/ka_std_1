package kr.co.yhs.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResultDto {
    private String resultCode = "00";
    private String resultMsg = "success";
    private List<TradeDto> tradeList;
}
