package com.lwx.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//生成有参数构造方法
@AllArgsConstructor
//生成无参数构造
@NoArgsConstructor
public class LWXException extends RuntimeException {
    //状态码
    private Integer code;
    //异常信息
    private String msg;
}
