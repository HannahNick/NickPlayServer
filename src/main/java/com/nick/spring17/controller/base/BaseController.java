package com.nick.spring17.controller.base;

import com.nick.spring17.vo.base.BaseVo;

public class BaseController {

    protected <T> BaseVo<T> wrapperSuccessResult(T data){
        BaseVo<T> tBaseVo = new BaseVo<>();
        tBaseVo.setCode(200);
        tBaseVo.setData(data);
        tBaseVo.setMessage("");
        return tBaseVo;
    }

    protected <T> BaseVo<T> wrapperFailResult(T data,String msg){
        BaseVo<T> tBaseVo = new BaseVo<>();
        tBaseVo.setData(data);
        tBaseVo.setCode(500);
        tBaseVo.setMessage(msg);

        return tBaseVo;
    }

    protected <T> BaseVo<T> wrapperFailResult(String msg){
        BaseVo<T> tBaseVo = new BaseVo<>();
        tBaseVo.setData(null);
        tBaseVo.setCode(500);
        tBaseVo.setMessage(msg);

        return tBaseVo;
    }
}
