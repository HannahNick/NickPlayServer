package com.nick.spring17.controller;

import com.nick.spring17.controller.base.BaseController;
import com.nick.spring17.dto.KeyCodeDTO;
import com.nick.spring17.service.KeyCodeService;
import com.nick.spring17.vo.KeyCodeVo;
import com.nick.spring17.vo.base.BaseVo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/keycode", produces = MediaType.APPLICATION_JSON_VALUE)
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class KeycodeController extends BaseController {

    KeyCodeService keyCodeService;

    @PostMapping("/add")
    public BaseVo<Boolean> commitOrder( @RequestBody KeyCodeDTO keyCodeDTO){
        keyCodeService.addKeycode(keyCodeDTO);
        return wrapperSuccessResult(true);
    }

    @PostMapping("/login")
    public BaseVo<Boolean> login(@RequestBody KeyCodeDTO keyCodeDTO){
        try{
            return wrapperSuccessResult(keyCodeService.login(keyCodeDTO));
        }catch (Exception e){
            return wrapperFailResult(e.getMessage());
        }
    }

    @PostMapping("/checkToken")
    public BaseVo<Boolean> checkToken(@RequestBody KeyCodeDTO keyCodeDTO){
        try{
            return wrapperSuccessResult(keyCodeService.checkToken(keyCodeDTO));
        }catch (Exception e){
            return wrapperFailResult(e.getMessage());
        }
    }


    @PostMapping("/findAll")
    public BaseVo<List<KeyCodeVo>> getAllKeyCode(){
        return wrapperSuccessResult(keyCodeService.getAllKeycodeList());
    }
}
