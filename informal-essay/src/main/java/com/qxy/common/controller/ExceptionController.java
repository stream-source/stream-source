package com.qxy.common.controller;

import com.qxy.exception.CustomException;
import com.qxy.response.HttpResult;
import com.qxy.response.ResultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:qxy
 * @Date:2020/4/7 14:53
 */
@RestController
public class ExceptionController {

    @GetMapping("/getExceptionByThrows")
    public HttpResult getExceptionByThrows() {
        int i = 1 / 0;
        return ResultResponse.success();
    }

    @GetMapping("/getExceptionByTryCatch")
    public HttpResult getExceptionInfoByTryCatch() {
        try {
            throw new CustomException(4002, "返回try-catch 捕获异常");
        } catch (CustomException e) {
            return ResultResponse.failure(e.getCode(), e.getMessage());
        }
    }

    @GetMapping("/getExceptionByThrow")
    public HttpResult getExceptionByThrow() {
        Integer throw123456 = Integer.valueOf("throw123456");
        return ResultResponse.success(throw123456);
    }


}
