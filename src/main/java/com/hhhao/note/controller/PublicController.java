package com.hhhao.note.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhhao.note.annotation.CurrentUser;
import com.hhhao.note.dto.login.UserInfo;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 公共API
 * 
 * @author haoy
 *
 */
@RestController
@RequestMapping("/api/public")
public class PublicController {
    @ApiOperation(value = "获取当前登录用户信息", httpMethod = "GET")
    @GetMapping("/user")
    public UserInfo getCurrentUser(@ApiIgnore @CurrentUser UserInfo userInfo) {
        return userInfo;
    }
}
