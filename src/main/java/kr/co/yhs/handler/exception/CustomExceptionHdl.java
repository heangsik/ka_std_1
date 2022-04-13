package kr.co.yhs.handler.exception;

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
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class CustomExceptionHdl {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResultDto requestValidExeptonV2(MethodArgumentNotValidException e){
        log.info("requestValidExepton");
        List<FieldError> errorList = e.getBindingResult().getFieldErrors();
        for(FieldError fe : errorList)
        {
            log.info("validError filed={}, message={}, value={}", fe.getField(), fe.getDefaultMessage(), fe.getRejectedValue());
        }
        return ResultDto.fail(RESPONSE_CODE.R_10);
    }
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResultDto requestValidExeptonV2(ConstraintViolationException e){
        log.info("requestValidExeptonV2");
        log.error(e.getMessage(), e);
        return ResultDto.fail(RESPONSE_CODE.R_11);
    }
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ServiceException.class)
    protected ResultDto serviceExepton(ServiceException e){
        log.info("serviceExepton");
        return ResultDto.fail(e.getResponseCode());
    }
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    protected ResultDto requestExepton(Exception e){
        log.info("requestExepton");
        if( null != e )
        {
            log.error(e.getMessage(), e);
        }
        return ResultDto.fail(RESPONSE_CODE.R_10);
    }
}
