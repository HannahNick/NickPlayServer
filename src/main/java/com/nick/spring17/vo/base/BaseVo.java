package com.nick.spring17.vo.base;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class BaseVo<T> {

    public BaseVo(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    int code;
    String message;
    T data;
}
