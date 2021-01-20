package com.hhhao.note.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.hhhao.note.dto.Result;
import com.hhhao.note.util.enums.RespondEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常捕获 处理器
 * 
 * @author luocc
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * post 请求没有body错误
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Void> handle(HttpMessageNotReadableException e) {
        log.error("bad request(HttpMessageNotReadableException)", e);
        return Result.error(RespondEnum.BAD_REQUEST.getCode(), e.getMessage());
    }

    /**
     * 不支持的请求路径
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<Void> handle(HttpRequestMethodNotSupportedException e) {
        log.error("not found", e);
        return Result.error(RespondEnum.NOT_FOUND.getCode(), "消失的请求");
    }

    /**
     * RequestParam 参数缺失异常
     * 
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<Void> handle(MissingServletRequestParameterException e) {
        log.error("bad request(MissingServletRequestParameterException)", e);
        return Result.error(RespondEnum.BAD_REQUEST.getCode(), e.getMessage());
    }

    /**
     * 路径参数不匹配错误
     * 
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<Void> handle(MethodArgumentTypeMismatchException e) {
        log.error("bad request(MethodArgumentTypeMismatchException)", e);
        return Result.error(RespondEnum.BAD_REQUEST.getCode(), "参数：" + e.getName() + " 类型错误");
    }

    /**
     * 路径参数类型错误
     * 
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result<Void> handle(HttpMediaTypeNotSupportedException e) {
        log.error("bad request(HttpMediaTypeNotSupportedException)", e);
        return Result.error(RespondEnum.BAD_REQUEST.getCode(), "参数类型错误");
    }

    /**
     * 参数错误
     * 
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<Void> handle(IllegalArgumentException e) {
        log.error("bad request(IllegalArgumentException)", e);
        return Result.error(RespondEnum.BAD_REQUEST.getCode(), "参数异常");
    }

    /**
     * 路径参数类型 绑定错误
     * 
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Result<Void> handle(BindException e) {
        log.error("bad request(BindException)", e);
        return Result.error(RespondEnum.BAD_REQUEST.getCode(),
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 处理自定义异常
     */
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(CustomizeException.class)
    public Result<Void> handle(CustomizeException e) {
        log.error("not acceptable", e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * HttpClientErrorException
     * 
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.class)
    public Result<Void> handle(HttpClientErrorException e) {
        log.error("bad request(HttpClientErrorException)", e);
        return Result.error(e.getStatusCode().value() + "", e.getStatusText());
    }

    /**
     * 数据完整性处理
     * 
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result<Void> handle(DataIntegrityViolationException e) {
        String dataTruncation = "com.mysql.cj.jdbc.exceptions.MysqlDataTruncation";
        if (e.getMessage().contains(dataTruncation)) {
            log.error("payload too large", e);
            return Result.error(RespondEnum.DATA_ERROR.getCode(), "数据超出限制");
        }
        log.error("payload too large", e);
        return Result.error(RespondEnum.DATA_ERROR.getCode(), "数据异常");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MybatisPlusException.class)
    public Result<Void> handle(MybatisPlusException e) {
        log.error("请检查代码:{}", e);
        return Result.error(RespondEnum.ERROR.getCode(), e.getMessage());
    }

    /**
     * 处理兜底异常
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Void> handle(Exception e) {
        log.error("请检查代码:{}", e);
        // "服务器异常"
        return Result.error(RespondEnum.ERROR.getCode(), e.getMessage());
    }

}
