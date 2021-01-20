package com.hhhao.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.login.LoginParameter;
import com.hhhao.note.service.LoginService;

import io.swagger.annotations.ApiOperation;

/**
 * 用户登录API
 * 
 * @author hhhao
 *
 */
@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    LoginService loginService;

    @ApiOperation(value = "通过邮箱密码登录，返回用户信息或错误信息", httpMethod = "POST")
    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginParameter param) {
        return loginService.loginCheck(param);
    }
}
