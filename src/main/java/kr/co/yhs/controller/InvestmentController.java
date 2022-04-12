package kr.co.yhs.controller;

import kr.co.yhs.dto.InverstmentSelectDto;
import kr.co.yhs.dto.ResultDto;
import kr.co.yhs.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RestController
public class InvestmentController {

    private final InvestmentService investmentService;
    @GetMapping(value = "/")
    public String connectTest()
    {
        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "connection succesws";
    }
    @PostMapping(value = "/selectAll")
    public ResultDto selectAllTrade(@RequestHeader("X-USER-ID") String header, @RequestBody InverstmentSelectDto dto)
    {
        log.info("select all select x-user-id={}, body={}", header, dto.toString());
        ResultDto rd = new ResultDto();
        rd.setTradeList(investmentService.getAllTrade());
        return rd;

    }
    @PostMapping(value = "/selectAble")
    public ResultDto selectAbleTrade(@RequestHeader("X-USER-ID") String header, @RequestBody InverstmentSelectDto dto)
    {
        log.info("select able select x-user-id={}, body={}", header, dto.toString());
        ResultDto rd = new ResultDto();
        rd.setTradeList(investmentService.getAbleTrade());
        return rd;

    }
    @PostMapping(value = "/api")
    public ResultDto home(@RequestBody InverstmentSelectDto dto)
    {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        log.info("homt"+dto.toString());
        return new ResultDto();

    }
}
