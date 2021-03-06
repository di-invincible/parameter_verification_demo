package com.example.parameter.verification.demo.exception;

import com.example.parameter.verification.demo.common.result.CommonResult;
import com.example.parameter.verification.demo.common.result.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.List;


/**
 * 参数校验 和 异常捕获 统一返回
 * @author aaron
 * @since 2021-01-27
 */
@Slf4j
@RestControllerAdvice
@Order(0)
public class RestControllerAdviceHandler {


    /**
     * MethodArgumentNotValidException 参数不合法异常
     * 配和 @Valid 注解
     * @param e 参数不合法异常
     * @return CommonResult<Object>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    CommonResult<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();
        int firstIndex = 0;
        String message = objectErrors.get(firstIndex).getDefaultMessage();
        log.error("MethodArgumentNotValidException = {}",message);
        return CommonResult.failed(ErrorCode.A0400,message);
    }

    /**
     * MethodArgumentNotValidException 参数不合法异常
     * 配和 @Valid 注解
     * @param e 参数不合法异常
     * @return CommonResult<Object>
     */
    @ExceptionHandler(ConstraintViolationException.class)
    CommonResult<Object> handleMethodArgumentNotValidException(ConstraintViolationException e){
        Iterator<ConstraintViolation<?>>
                iterator = e.getConstraintViolations().iterator();
        String message = null;
        if (iterator.hasNext()) {
            message = iterator.next().getMessage();
        }
        log.error("ConstraintViolationException = {}",message);
        return CommonResult.failed(ErrorCode.A0400,message);
    }
}
