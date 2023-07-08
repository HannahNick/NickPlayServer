package com.nick.spring17.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nick.spring17.entity.enu.EnableFlag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Table(name = "keycode")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KeyCode {

    @Id
    String id;

    @Column(name = "keycode")
    String keycode;

    @Column(name = "token")
    String token;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date createTime;

    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date updateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "enable_flag")
    private EnableFlag enableFlag = EnableFlag.Y;
}
