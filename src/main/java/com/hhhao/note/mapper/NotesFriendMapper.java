package com.hhhao.note.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhhao.note.dto.friend.FriendDto;
import com.hhhao.note.entity.NotesFriend;

/**
 * 用户好友交互mapper
 * 
 * @author haoy
 *
 */
public interface NotesFriendMapper extends BaseMapper<NotesFriend> {
    /**
     * 获取当前登录用户的好友列表
     * 
     * @param userInfo
     * @return
     */
    List<FriendDto> getFriends(@Param("friendId") Integer friendId);
}
