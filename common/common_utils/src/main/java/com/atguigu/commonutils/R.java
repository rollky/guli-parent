package com.atguigu.commonutils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName R
 * @Description 统一返回结果
 * @Author zb
 * @Date 2021/1/12 22:24
 **/
@Data
public class R {
    private Boolean success;
    private Integer code;
    private String msg;
    private Map<String,Object> data = new HashMap<>();


    private R(){};

    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMsg("成功");
        return r;
    }

    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMsg("失败");
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R msg(String msg){
        this.setMsg(msg);
        return this;
    }

    public R data(Map<String,Object> data){
        this.setData(data);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key,value);
        return this;
    }
}
