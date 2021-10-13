package com.achao.common.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseResult {

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap();

    private ResponseResult(){};

    /**
     * 返回成功
     */
    public static ResponseResult ok(){
        ResponseResult result = new ResponseResult();
        result.setCode(ResponseEnum.SUCCESS.getCode());
        result.setMessage(ResponseEnum.SUCCESS.getMessage());
        return result;
    }

    /**
     * 返回失败
     */
    public static ResponseResult error(){
        ResponseResult result = new ResponseResult();
        result.setCode(ResponseEnum.ERROR.getCode());
        result.setMessage(ResponseEnum.ERROR.getMessage());
        return result;
    }

    /**
     * 设置特定结果
     */
    public static ResponseResult setResult(ResponseEnum responseEnum){
        ResponseResult result = new ResponseResult();
        result.setCode(responseEnum.getCode());
        result.setMessage(responseEnum.getMessage());
        return result;
    }

    /**
     * 设置特定响应数据
     */
    public ResponseResult data(String key,Object value){
        this.data.put(key,value);
        return this;
    }

    /**
     * 设置特定响应消息
     */
    public ResponseResult message(String message){
        this.setMessage(message);
        return this;
    }

    /**
     * 设置特定响应码
     */
    public ResponseResult code(Integer code){
        this.setCode(code);
        return this;
    }

    /**
     * 设置特定响应数据集
     */
    public ResponseResult data(Map<String, Object> map){
        this.setData(map);
        return this;
    }


}
