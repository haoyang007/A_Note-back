package com.hhhao.note.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhhao.note.dto.group.GroupInfoDto;
import com.hhhao.note.dto.group.MemberDetail;
import com.hhhao.note.entity.Group;

/**
 * 群组信息Mapper
 * 
 * @author haoy
 *
 */
public interface GroupMapper extends BaseMapper<Group> {
    /**
     * 获取群组成员信息
     * 
     * @author haoy
     * @param groupId
     * @return
     */
    List<MemberDetail> getMemberDetails(@Param("groupId") Integer groupId, @Param("startTime") Long startTime,
            @Param("endTime") Long endTime);

    /**
     * 搜索群组信息
     * 
     * @author haoy
     * @param groupId
     * @param groupName
     * @return
     */
    List<GroupInfoDto> getGroupInfo(@Param("groupId") Integer groupId, @Param("groupName") String groupName);
}
