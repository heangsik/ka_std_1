package kr.co.yhs.handler.exception;

import kr.co.yhs.config.code.RESPONSE_CODE;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceException extends RuntimeException{
    private RESPONSE_CODE responseCode;

    public static ServiceException getServiceException(RESPONSE_CODE code, String msg)
    {
        return new ServiceException(code, new RuntimeException(msg));
    }
    public static ServiceException getServiceException(RESPONSE_CODE code)
    {
        return new ServiceException(code, new RuntimeException("service exception"));
    }
    private ServiceException(RESPONSE_CODE responseCode)
    {
        this.responseCode = responseCode;
    }
    private ServiceException(RESPONSE_CODE responseCode, Exception e)
    {
        super(e);
        this.responseCode = responseCode;
    }
}
