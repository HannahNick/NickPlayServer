package com.nick.spring17.controller;

import com.nick.spring17.entity.Music;
import com.nick.spring17.service.MusicService;
import com.nick.spring17.vo.base.BaseVo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/music", produces = MediaType.APPLICATION_JSON_VALUE)
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class MusicController {

    MusicService musicService;

    @GetMapping("/initLocalData")
    public String commitOrder(){
        return musicService.initLocalData();
    }

    @GetMapping("/getAllMusic")
    public BaseVo<List<Music>> getAllMusic(){
        return new BaseVo<>(200,"success",musicService.findAll());
    }
}
