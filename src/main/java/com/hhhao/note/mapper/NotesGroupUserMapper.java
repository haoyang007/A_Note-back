package com.hhhao.note.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhhao.note.dto.group.GroupInfoDto;
import com.hhhao.note.entity.NotesGroupUser;

/**
 * 群组用户信息Mapper
 * 
 * @author haoy
 *
 */
public interface NotesGroupUserMapper extends BaseMapper<NotesGroupUser> {

    /**
     * 获取用户的群组列表信息
     * 
     * @author haoy
     * @param userId
     * @return
     */
    List<GroupInfoDto> getGroupListInfo(@Param("userId") Integer userId);

    /**
     * 更新群组用户打卡时间
     * 
     * @author haoy
     * @param groupId
     * @param userId
     */
    void updateClockTime(@Param("time") Long time, @Param("groupId") Integer groupId, @Param("userId") Integer userId);

}
