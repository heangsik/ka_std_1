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
    public ResultDto selectTrade(@RequestBody InverstmentSelectDto dto)
    {
        log.info("select all select");
        return new ResultDto();

    }
    @PostMapping(value = "/api")
    public ResultDto home(@RequestBody InverstmentSelectDto dto)
    {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
        log.info("homt"+dto.toString());
        return new ResultDto();

    }
}
