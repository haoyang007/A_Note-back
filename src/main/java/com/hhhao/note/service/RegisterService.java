package com.hhhao.note.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.register.RegisterParameter;
import com.hhhao.note.entity.User;

/**
 * 用户注册相关服务
 * 
 * @author haoy
 *
 */
public interface RegisterService extends IService<User> {
    /**
     * 用户注册
     * 
     * @param param 用户信息
     * @return
     */
    Result<String> registerCheck(RegisterParameter param);
}
