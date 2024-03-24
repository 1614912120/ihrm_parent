package com.ihrm.common.exception;

/**
 * ClassName: CommonException
 * Package: com.ihrm.common.exception
 * Description:
 *
 * @Author R
 * @Create 2024/3/22 10:33
 * @Version 1.0
 */

import com.ihrm.common.entity.ResultCode;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 自定义异常
 */
@Getter
public class CommonException extends Exception{

    private ResultCode resultCode;

    public CommonException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

}
