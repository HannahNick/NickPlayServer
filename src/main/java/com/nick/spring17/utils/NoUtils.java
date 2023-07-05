package com.nick.spring17.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangyx
 * @date 2019/12/16
 */
@Component
public final class NoUtils {
    private static SnowFlakeKeyGen snowFlakeKeyGen;

    @Autowired
    public NoUtils(SnowFlakeKeyGen snowFlakeKeyGen){
        NoUtils.snowFlakeKeyGen = snowFlakeKeyGen;
    }

    /**
     * Id 生成器
     * @return Id 生成器
     */
    public static SnowFlakeKeyGen idGen(){
        return snowFlakeKeyGen;
    }
}
