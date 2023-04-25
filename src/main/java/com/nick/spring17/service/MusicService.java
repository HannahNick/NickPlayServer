package com.nick.spring17.service;

import com.nick.spring17.entity.Music;

import java.util.List;

public interface MusicService {

    /**
     * 初始化本地数据
     */
    String initLocalData();

    /**
     * 获取所有数据
     * @return 音乐数据
     */
    List<Music> findAll();
}
