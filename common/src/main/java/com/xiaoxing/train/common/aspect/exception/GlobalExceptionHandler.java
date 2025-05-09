package com.xiaoxing.train.common.aspect.exception;


import com.xiaoxing.train.common.aspect.result.BaseResponse;
import com.xiaoxing.train.common.aspect.result.ErrorCode;
import com.xiaoxing.train.common.aspect.result.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("businessException: " + e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage(), e.getDescription());
    }


    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, ErrorCode.SYSTEM_ERROR.getMessage(), "");
    }

    @ExceptionHandler(BindException.class)
    public BaseResponse<?> bindExceptionHandler(BindException e) {
        log.error("bindException", e);
        return ResultUtils.errorList(ErrorCode.PARAMS_ERROR, ErrorCode.PARAMS_ERROR.getMessage(),
                e.getBindingResult().getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()));
    }
}
