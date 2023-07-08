package com.nick.spring17.dao;

import com.nick.spring17.entity.KeyCode;
import com.nick.spring17.entity.enu.EnableFlag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyCodeDao extends CrudRepository<KeyCode,String> {

    List<KeyCode> findAllByEnableFlagOrderByCreateTimeDesc(EnableFlag enableFlag);


    KeyCode findByKeycodeAndEnableFlag(String keycode, EnableFlag enableFlag);

}
