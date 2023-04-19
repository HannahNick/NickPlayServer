package com.nick.spring17.controller;

import com.nick.spring17.dto.UserDTO;
import com.nick.spring17.service.UserService;
import com.nick.spring17.vo.UserVo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class UserController {

    UserService userService;

    @PostMapping("/getUserVo")
    public UserVo getUserVo(@RequestBody UserDTO userDTO){
        return userService.getUser(userDTO.getId());
    }

    @GetMapping("/getUserVo2")
    public UserVo getUserVo2(@RequestParam("id") String id){
        return userService.getUser(id);
    }
}
