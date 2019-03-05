package com.pp.base.exception;

import com.pp.base.common.RJResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * 统一异常类
 */
public class BusinessException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(BusinessException.class);
    protected boolean errorCode;
    protected String errorCodeMsg;
    protected String[] errorMessageArguments;
    protected RJResponse rjResponse;

    protected BusinessException() {
        this("");
    }

    public BusinessException(String message) {
        super(message);
        this.errorCodeMsg = "fail";
        this.errorCode = false;
        this.errorMessageArguments = new String[0];
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.errorCodeMsg = "fail";
        this.errorCode = false;
        this.errorMessageArguments = new String[0];
    }

    public boolean getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(boolean errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCodeMsg() {
        return errorCodeMsg;
    }

    public void setErrorCodeMsg(String errorCodeMsg) {
        this.errorCodeMsg = errorCodeMsg;
    }

    public String[] getErrorMessageArguments() {
        return this.errorMessageArguments;
    }

    public void setErrorMessageArguments(String[] errorMessageArguments) {
        this.errorMessageArguments = errorMessageArguments;
    }

    public static BusinessException withErrorCode(boolean errorCode) {
        BusinessException businessException = new BusinessException();
        businessException.errorCode = errorCode;
        return businessException;
    }

    public static BusinessException withErrorCode(String errorCodeMsg) {
        BusinessException businessException = new BusinessException();
        businessException.errorCode = false;
        businessException.errorCodeMsg = errorCodeMsg;
        return businessException;
    }

    public static BusinessException fromAPIResponse(RJResponse apiResponse) {
        BusinessException businessException = new BusinessException();
        if(apiResponse == null) {
            apiResponse = RJResponse.fail("NULL");
        }

        businessException.rjResponse = apiResponse;
        return businessException;
    }

    public BusinessException withErrorMessageArguments(String... errorMessageArguments) {
        if(errorMessageArguments != null) {
            this.errorMessageArguments = errorMessageArguments;
        }

        return this;
    }
    public RJResponse response() {
        if(this.rjResponse != null) {
            return this.rjResponse;
        } else {
            this.rjResponse = RJResponse.fail().widthCode(this.getErrorCode());
            if(this.getErrorMessageArguments() != null && this.getErrorMessageArguments().length > 0) {
                try {
                    this.rjResponse.setMsg(MessageFormat.format(this.rjResponse.getMsg(), this.getErrorMessageArguments()));
                } catch (Exception var2) {
                    logger.error(var2.getMessage());
                }
            }

            return this.rjResponse;
        }
    }

}
