package com.hhhao.note.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.login.LoginParameter;
import com.hhhao.note.entity.User;
import com.hhhao.note.mapper.UserMapper;
import com.hhhao.note.service.LoginService;
import com.hhhao.note.util.JwtUtils;
import com.hhhao.note.util.enums.RespondEnum;

/**
 * 用户登录相关服务实现
 * 
 * @author haoy
 *
 */
@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public Result<String> loginCheck(LoginParameter param) {

        if (param == null || param.isEmpty()) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "邮箱或密码不能为空！");
        }
        User user = userMapper.selectByEmail(param.getEmail());
        if (user == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "用户不存在或密码错误！");
        } else {
            if (param.getPassword().equals(user.getPassword())) {
                return Result.ok(jwtUtils.generateJwt(user));
            } else {
                return Result.error(RespondEnum.BAD_REQUEST.getCode(), "密码错误！");
            }
        }
    }
}
