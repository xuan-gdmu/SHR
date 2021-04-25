package com.lwx.common;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lwx
 * @Create 2021-03-30-19:23
 * @Version V
 */
@Data
public class MyResult implements ResultCode{
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //把构造方法私有
    private MyResult() {}

    //成功静态方法
    public static MyResult ok() {
        MyResult r = new MyResult();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    //失败静态方法
    public static MyResult error() {
        MyResult r = new MyResult();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public MyResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public MyResult message(String message){
        this.setMessage(message);
        return this;
    }

    public MyResult code(Integer code){
        this.setCode(code);
        return this;
    }

    public MyResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public MyResult data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
