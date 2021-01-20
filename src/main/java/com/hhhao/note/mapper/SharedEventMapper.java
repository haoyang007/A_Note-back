package com.hhhao.note.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhhao.note.dto.friend.FriendSharedEventDto;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.entity.SharedEvent;

/**
 * 分享的事件信息
 * 
 * @author haoy
 *
 */
public interface SharedEventMapper extends BaseMapper<SharedEvent> {

    /**
     * 获取被分享的事件信息
     * 
     * @return
     */
    List<FriendSharedEventDto> getSharedEvent(@Param("userInfo") UserInfo userInfo);
}
