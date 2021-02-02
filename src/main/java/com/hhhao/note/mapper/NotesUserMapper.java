package com.hhhao.note.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.entity.NotesUser;

/**
 * 用户信息操作
 * 
 * @author hhhao
 *
 */
public interface NotesUserMapper extends BaseMapper<NotesUser> {
    /**
     * 通过邮箱查询用户信息
     * 
     * @param email
     * @return
     */
    NotesUser selectByEmail(@Param("email") String email);

    /**
     * 通过邮箱获取当前登录用户信息
     * 
     * @param email
     * @return
     */
    UserInfo selectCurrentUser(@Param("email") String email);
}
