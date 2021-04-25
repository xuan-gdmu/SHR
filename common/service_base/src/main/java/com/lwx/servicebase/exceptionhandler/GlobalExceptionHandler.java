package com.lwx.servicebase.exceptionhandler;


import com.alibaba.excel.exception.ExcelAnalysisException;
import com.lwx.common.MyResult;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //自定义异常
    @ExceptionHandler(LWXException.class)
    @ResponseBody //为了返回数据
    public MyResult error(LWXException e) {

        e.printStackTrace();
        return MyResult.error().code(e.getCode()).message(e.getMsg());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public MyResult error(DataIntegrityViolationException e) {
        e.printStackTrace();
        return MyResult.error().code(20001).message("请填写必填项!");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public MyResult error(DuplicateKeyException e) {
        e.printStackTrace();
        return MyResult.error().code(20001).message("所填项目已存在,请检查!");
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseBody
    public MyResult error(SQLIntegrityConstraintViolationException e) {
        e.printStackTrace();
        return MyResult.error().code(20001).message("所填项目已存在!");
    }

    @ExceptionHandler(ExcelAnalysisException.class)
    @ResponseBody //为了返回数据
    public MyResult error(ExcelAnalysisException e) {
        e.printStackTrace();
        return MyResult.error().code(20001).message("Excel处理异常，请检查文件是否填写正确!");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public MyResult error(ArithmeticException e) {
        e.printStackTrace();
        return MyResult.error().message("执行了ArithmeticException异常处理..");
    }

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public MyResult error(Exception e) {
        e.printStackTrace();
        return MyResult.error().message("执行了全局异常处理..");
    }

}
