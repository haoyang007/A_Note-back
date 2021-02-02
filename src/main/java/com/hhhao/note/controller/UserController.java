package com.hhhao.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhhao.note.annotation.CurrentUser;
import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.dto.user.UserUpdateDto;
import com.hhhao.note.entity.NotesUser;
import com.hhhao.note.mapper.NotesUserMapper;
import com.hhhao.note.util.enums.RespondEnum;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 用户相关API
 * 
 * @author haoy
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    NotesUserMapper userMapper;

    @ApiOperation(value = "更改用户信息", httpMethod = "POST")
    @PostMapping("/update")
    public Result<String> alterUserInfo(@RequestBody UserUpdateDto userUpdate,
            @ApiIgnore @CurrentUser UserInfo userInfo) {
        if (userUpdate == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "用户信息不能为空");
        }
        NotesUser user = NotesUser.instance();
        user.setUsername(userUpdate.getUsername());
        user.setPassword(userUpdate.getPassword());
        user.setId(userInfo.getId());
        userMapper.updateById(user);
        return Result.ok();
    }

}