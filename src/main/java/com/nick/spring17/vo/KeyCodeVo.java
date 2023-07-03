package com.nick.spring17.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KeyCodeVo {
    String id;

    String keycode;

    String token;

    Date createTime;

    Date updateTime;
}
