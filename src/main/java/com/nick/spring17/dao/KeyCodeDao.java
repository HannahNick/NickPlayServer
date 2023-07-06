package com.nick.spring17.dao;

import com.nick.spring17.entity.KeyCode;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyCodeDao extends CrudRepository<KeyCode,String> {

    List<KeyCode> findAll(Sort sort);


    KeyCode findByKeycode(String keycode);

}
