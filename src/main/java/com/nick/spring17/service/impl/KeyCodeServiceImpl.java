package com.nick.spring17.service.impl;

import com.nick.spring17.dao.KeyCodeDao;
import com.nick.spring17.dto.KeyCodeDTO;
import com.nick.spring17.entity.KeyCode;
import com.nick.spring17.entity.enu.EnableFlag;
import com.nick.spring17.service.KeyCodeService;
import com.nick.spring17.utils.NoUtils;
import com.nick.spring17.vo.KeyCodeVo;
import com.nick.spring17.vo.LoginResultVo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class KeyCodeServiceImpl implements KeyCodeService {

    KeyCodeDao keyCodeDao;

    @Override
    public void addKeycode(KeyCodeDTO keyCodeDTO) {
        if (keyCodeDTO.getKeycode() == null){
            throw new RuntimeException("授权码不能为空");
        }
        if (keyCodeDTO.getKeycode().length()<=5){
            throw new RuntimeException("授权码要大于5位的数字");
        }
        KeyCode byKeycode = keyCodeDao.findByKeycodeAndEnableFlag(keyCodeDTO.getKeycode(),EnableFlag.Y);
        if (byKeycode!=null){
            throw new RuntimeException("授权码已存在");
        }

        KeyCode keyCode = new KeyCode();
        keyCode.setId(NoUtils.idGen().nextStringId());
        keyCode.setToken("");
        keyCode.setKeycode(keyCodeDTO.getKeycode());
        keyCode.setCreateTime(new Date());
        keyCode.setUpdateTime(new Date());
        keyCode.setEnableFlag(EnableFlag.Y);
        keyCodeDao.save(keyCode);
    }

    @Override
    public LoginResultVo login(KeyCodeDTO keyCodeDTO) {
        if (keyCodeDTO.getKeycode()==null){
            throw new RuntimeException("授权码不能为空");
        }

        KeyCode keyCode = keyCodeDao.findByKeycodeAndEnableFlag(keyCodeDTO.getKeycode().trim(),EnableFlag.Y);
        if (keyCode ==null){
            throw new RuntimeException("授权码不存在");
        }
        if (keyCodeDTO.getToken() ==null || "".equals(keyCodeDTO.getToken())){
            throw new RuntimeException("token不能为null");
        }
        LoginResultVo loginResultVo = new LoginResultVo();
        keyCode.setToken(keyCodeDTO.getToken());
        keyCode.setUpdateTime(new Date());
        keyCodeDao.save(keyCode);
        loginResultVo.setSuccess(true);
        loginResultVo.setKeycode(keyCode.getKeycode());

        return loginResultVo;
    }

    @Override
    public Boolean checkToken(KeyCodeDTO keyCodeDTO) {
        if (keyCodeDTO.getKeycode() == null){
            throw new RuntimeException("授权码不能为空");
        }
        if (keyCodeDTO.getToken() == null){
            throw new RuntimeException("token不能为空");
        }
        KeyCode keyCode = keyCodeDao.findByKeycodeAndEnableFlag(keyCodeDTO.getKeycode().trim(),EnableFlag.Y);
        if (keyCode ==null){
            throw new RuntimeException("授权码不存在");
        }
        return keyCode.getToken().equals(keyCodeDTO.getToken());
    }


    @Override
    public List<KeyCodeVo> getAllKeycodeList() {
        List<KeyCode> allData = keyCodeDao.findAllByEnableFlagOrderByCreateTimeDesc(EnableFlag.Y);
        return allData.stream().map(keyCode -> {
            KeyCodeVo keyCodeVo = new KeyCodeVo();
            keyCodeVo.setId(keyCode.getId());
            keyCodeVo.setKeycode(keyCode.getKeycode());
            keyCodeVo.setToken(keyCode.getToken());
            keyCodeVo.setCreateTime(keyCode.getCreateTime());
            keyCodeVo.setUpdateTime(keyCode.getUpdateTime());
            return keyCodeVo;
        }).toList();
    }

}
