package com.nick.spring17.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "music")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Music implements Serializable {

    @Id
    String id;

    @Column(name = "album_name")
    String albumName;

    @Column(name = "main_actors")
    String mainActors;

    @Column(name = "path")
    String path;

    @Column(name = "img_path")
    String imgPath;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date createTime;

    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date updateTime;
}
