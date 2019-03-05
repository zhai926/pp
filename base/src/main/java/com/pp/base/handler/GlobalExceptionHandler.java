package com.pp.base.handler;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.pp.base.common.RJResponse;
import com.pp.base.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.ConnectException;
import java.sql.SQLException;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(BusinessException.class);

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public RJResponse businessException(Exception e){

        String msg = "请求错误";
        if (e instanceof BusinessException){
            msg = ((BusinessException) e).getErrorCodeMsg();
        }
        logger.error("find exception:e={}",e.getMessage());
        e.printStackTrace();
        return RJResponse.fail(msg);
    }

    /**
     * SQLException例外通知
     * 例外
     * @param e
     * @return
     */
    @ExceptionHandler(value = SQLException.class)
    public void databaseError(Exception e) {
        logger.error("Request raised " + e.getClass().getSimpleName());
        e.printStackTrace();
    }

    @ExceptionHandler(value = ConnectException.class)
    public void connectException(Exception e) {
        logger.error("Request raised " + e.getClass().getSimpleName());
        e.printStackTrace();
    }
    @ExceptionHandler(value = CommunicationsException.class)
    public void communicationsException(Exception e) {
        logger.error("Request raised " + e.getClass().getSimpleName());
        e.printStackTrace();
    }
    @ExceptionHandler(value = Exception.class)
    public void exception(Exception e){
        logger.error("find exception:e={}",e.getMessage());
        e.printStackTrace();
//        return "error/400";
    }

}
