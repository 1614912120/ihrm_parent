package com.ihrm.common.handler;

/**
 * ClassName: BaseExceptionHandler
 * Package: com.ihrm.common.handler
 * Description:
 *
 * @Author R
 * @Create 2024/3/22 10:16
 * @Version 1.0
 */

import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 * 1.声明异常处理器
 * 2.对异常统一处理
 */
@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    //封装成json对象进行相应
    @ResponseBody
    public Result error(HttpServletRequest request, HttpServletResponse response,Exception e) {
        if(e.getClass() == CommonException.class) {
            CommonException ce = (CommonException) e;
            Result result = new Result(ce.getResultCode());
            return result;
        }
        Result result = new Result(ResultCode.SERVER_ERROR);
        return  result;
    }
}
