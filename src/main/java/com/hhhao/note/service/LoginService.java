package com.hhhao.note.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.login.LoginParameter;
import com.hhhao.note.entity.User;

/**
 * 用户登录相关服务
 * 
 * @author haoy
 *
 */
public interface LoginService extends IService<User> {
    /**
     * 验证登录信息，若合法，则返回用户信息
     * 
     * @param param
     * @return
     */
    Result<String> loginCheck(LoginParameter param);
}
