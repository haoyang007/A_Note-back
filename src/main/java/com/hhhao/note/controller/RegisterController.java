package com.hhhao.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.register.RegisterParameter;
import com.hhhao.note.service.NotesRegisterService;

import io.swagger.annotations.ApiOperation;

/**
 * 用户注册API
 * 
 * @author hhhao
 *
 */
@RestController
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    NotesRegisterService registerService;

    @ApiOperation(value = "设置邮箱密码注册账号，返回提示信息（包含错误信息）")
    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterParameter param) {

        return registerService.registerCheck(param);
    }
}
