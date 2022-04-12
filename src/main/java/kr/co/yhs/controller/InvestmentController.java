package kr.co.yhs.controller;

import kr.co.yhs.dto.InverstmentSelectDto;
import kr.co.yhs.dto.ResultDto;
import kr.co.yhs.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class InvestmentController {

    private final InvestmentService investmentService;

    @GetMapping(value = "/select")
    public ResultDto selectTrade(@RequestBody InverstmentSelectDto dto)
    {
        return null;

    }
}
