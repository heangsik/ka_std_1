package kr.co.yhs.controller;

import kr.co.yhs.dto.request.BodyDto;
import kr.co.yhs.dto.request.InverstmentDto;
import kr.co.yhs.dto.result.ResultDto;
import kr.co.yhs.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RestController
public class InvestmentController {

    private final InvestmentService investmentService;
    @PostMapping(value = "/allTrade")
    public ResultDto selectAllTrade(@RequestHeader("X-USER-ID") String header, @RequestBody BodyDto dto)
    {
        log.info("select all select x-user-id={}, body={}", header, dto.toString());
        ResultDto rd = ResultDto.success();
        rd.setTradeList(investmentService.getAllTrade());
        return rd;

    }
    @PostMapping(value = "/ableTrade")
    public ResultDto selectAbleTrade(@RequestHeader("X-USER-ID") String header, @RequestBody BodyDto dto)
    {
        log.info("select able select x-user-id={}, body={}", header, dto.toString());
        ResultDto rd = ResultDto.success();
        rd.setTradeList(investmentService.getAbleTrade());
        return rd;

    }
    @PostMapping(value = "/requestTrade")
    public ResultDto requestTrade(@RequestHeader("X-USER-ID") String header, @Valid @RequestBody InverstmentDto dto)
    {
        log.info("trade request x-user-id={}, body={}", header, dto.toString());
        ResultDto rd = ResultDto.success();
        rd.setTradeList(investmentService.getAbleTrade());
        return rd;

    }
    @PostMapping(value = "/api")
    public ResultDto home(@RequestBody BodyDto dto)
    {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        log.info("homt"+dto.toString());
        return ResultDto.success();

    }
}
