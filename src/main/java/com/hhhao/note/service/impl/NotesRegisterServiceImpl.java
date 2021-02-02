package com.hhhao.note.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.register.RegisterParameter;
import com.hhhao.note.entity.NotesUser;
import com.hhhao.note.mapper.NotesUserMapper;
import com.hhhao.note.service.NotesRegisterService;
import com.hhhao.note.util.enums.RespondEnum;

/**
 * 用户注册相关服务实现
 * 
 * @author haoy
 *
 */
@Service
public class NotesRegisterServiceImpl extends ServiceImpl<NotesUserMapper, NotesUser> implements NotesRegisterService {
    @Autowired
    NotesUserMapper userMapper;

    public Result<String> registerCheck(RegisterParameter param) {
        if (param == null || param.isEmpty()) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "用户信息不完整！");
        }
        NotesUser user = userMapper.selectByEmail(param.getEmail());
        if (user == null) {
            userMapper.insert(new NotesUser(param));
            return Result.ok();
        } else {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "用户已存在（该邮箱已注册）！");
        }
    }
}
