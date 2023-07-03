package com.nick.spring17.service;

import com.nick.spring17.dto.KeyCodeDTO;
import com.nick.spring17.vo.KeyCodeVo;

import java.util.List;

public interface KeyCodeService {
    void addKeycode(KeyCodeDTO keyCodeDTO);
    Boolean login(KeyCodeDTO keyCodeDTO);

    Boolean checkToken(KeyCodeDTO keyCodeDTO);
    List<KeyCodeVo> getAllKeycodeList();
}
