package com.nick.spring17.service.impl;

import com.nick.spring17.dao.MusicDao;
import com.nick.spring17.dao.UserDao;
import com.nick.spring17.entity.Music;
import com.nick.spring17.entity.User;
import com.nick.spring17.service.MusicService;
import com.nick.spring17.service.UserService;
import com.nick.spring17.vo.UserVo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService {

    MusicDao musicDao;

    @Transactional
    @Override
    public String initLocalData() {
        File musicDir = new File("/data/data/com.termux/files/home/storage/music");
        List<Music> musicName = new ArrayList<>();
        File[] files = musicDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            File tempFile = files[i];
            if (tempFile.isFile()&&tempFile.getName().endsWith(".mp3")){
                musicName.add(createMusicDate(i,files[i].getName()));
            }
        }
        musicDao.saveAll(musicName);
        return "";
    }

    @Override
    public List<Music> findAll() {
        return musicDao.findAll();
    }

    private Music createMusicDate(int id,String fileName){
        log.info(String.format("文件名:%1$s,.mp3下标:%2$s",fileName,fileName.indexOf(".mp3")));
        Music music = new Music();
        music.setId(String.format("%1$s",id+1));
        music.setPath(String.format("/music/%1$s",fileName));
        music.setAlbumName(fileName.substring(0,fileName.indexOf(".mp3")));
        music.setMainActors(fileName.substring(0,fileName.indexOf(".mp3")));
        music.setImgPath("");
        music.setCreateTime(new Date());
        music.setUpdateTime(new Date());
        return music;
    }
}
