package com.qxy.exception;

import com.qxy.response.HttpResult;
import com.qxy.response.ResultCodeEnum;
import com.qxy.response.ResultResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author:qxy
 * @Date:2020/4/7 14:41
 */
@ControllerAdvice
public class GlobalException {

    /**
     * 处理自定义异常：项目业务抛出
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public HttpResult handleCustomException(CustomException e){
        Integer code = e.getCode();
        String message = e.getMessage();
        ResultCodeEnum resultCodeEnum = ResultCodeEnum.BUSINESS_EXCEPTION;
        resultCodeEnum.setCode(code);
        resultCodeEnum.setMessage(message);
        return ResultResponse.failure(resultCodeEnum);
    }


    /**
     * 处理运行期异常，开发人员未知(不可预料)情况
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public HttpResult handleException(Exception e) {
        String message = e.getMessage();
        ResultCodeEnum resultCodeEnum = ResultCodeEnum.SERVER_ERROR;
        resultCodeEnum.setMessage(message);
        return ResultResponse.failure(resultCodeEnum);
    }


}
