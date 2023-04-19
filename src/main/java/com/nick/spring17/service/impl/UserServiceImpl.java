package com.nick.spring17.service.impl;

import com.nick.spring17.dao.UserDao;
import com.nick.spring17.entity.User;
import com.nick.spring17.service.UserService;
import com.nick.spring17.vo.UserVo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserDao userDao;

    @Override
    public UserVo getUser(String id) {
        User user = userDao.findById(id).get();
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setName(user.getName());
        userVo.setPassword(user.getPwd());
        return userVo;
    }
}
