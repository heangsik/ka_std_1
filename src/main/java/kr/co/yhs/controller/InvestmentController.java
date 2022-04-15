package kr.co.yhs.controller;

import kr.co.yhs.dto.request.BodyDto;
import kr.co.yhs.dto.request.InverstmentDto;
import kr.co.yhs.dto.result.ResultDto;
import kr.co.yhs.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Slf4j
@Controller
@RequiredArgsConstructor
@RestController
@Validated
public class InvestmentController {


    private final InvestmentService investmentService;
    @GetMapping(value = "get_mode")
    public String getTest(){
        log.info("get test input");
        return "get test success";
    }
    @PostMapping(value = "/allTrade")
    public ResultDto selectAllTrade()
    {
        log.info("select all select");
        ResultDto rd = ResultDto.success();
        rd.setTradeList(investmentService.getAllTrade());
        return rd;

    }
    @PostMapping(value = "/ableTrade")
    public ResultDto selectAbleTrade(@RequestHeader("X-USER-ID") String header, @RequestBody BodyDto dto)
    {
        log.info("select able select x-user-id={}, body={}", header, dto.toString());
        return investmentService.getAbleTrade();

    }
    @PostMapping(value = "/requestTrade")
    public ResultDto requestTrade(@RequestHeader(value = "X-USER-ID", required = true) @NotEmpty String hUserId, @Valid @RequestBody InverstmentDto dto)
    {
        log.info("trade request x-user-id={}, body={}", hUserId, dto.toString());
        return investmentService.tradeRequest(hUserId, dto);

    }
    @PostMapping(value = "/myTrade")
    public ResultDto myTrade(@RequestHeader(value = "X-USER-ID", required = true) @NotEmpty String hUserId)
    {
        log.info("my trade request x-user-id={}", hUserId);
        return investmentService.getMyTrade(hUserId);

    }
    @PostMapping(value = "/api")
    public ResultDto home(@RequestBody BodyDto dto)
    {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        log.info("homt"+dto.toString());
        return ResultDto.success();

    }
}
