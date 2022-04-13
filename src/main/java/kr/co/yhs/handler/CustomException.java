package kr.co.yhs.handler;

import kr.co.yhs.config.code.RESPONSE_CODE;
import kr.co.yhs.dto.result.ResultDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class CustomException {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResultDto requestValidExepton(MethodArgumentNotValidException e){
        List<FieldError> errorList = e.getBindingResult().getFieldErrors();
        for(FieldError fe : errorList)
        {
            log.info("validError filed={}, message={}, value={}", fe.getField(), fe.getDefaultMessage(), fe.getRejectedValue());
        }
        return ResultDto.fail(RESPONSE_CODE.R_10);
    }
}
