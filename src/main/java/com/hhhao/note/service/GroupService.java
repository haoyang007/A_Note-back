package com.hhhao.note.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.group.CreateGroupDto;
import com.hhhao.note.dto.group.GroupDto;
import com.hhhao.note.dto.group.GroupInfoDto;
import com.hhhao.note.dto.group.GroupMemberDto;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.entity.Group;

/**
 * 群组相关服务
 * 
 * @author haoy
 *
 */
public interface GroupService extends IService<Group> {
    /**
     * 创建群组
     * 
     * @param groupName
     * @return
     */
    Result<String> createGroup(CreateGroupDto groupInfo, UserInfo userInfo);

    /**
     * 加入指定群组
     * 
     * @param group
     * @return
     */
    Result<String> joinGroup(Integer groupId, UserInfo userInfo);

    /**
     * 当前登录用户退出指定群组
     * 
     * @param userInfo
     * @return
     */
    Result<String> quitGroup(Integer groupId, UserInfo userInfo);

    /**
     * 删除指定群成员
     * 
     * @author haoy
     * @param groupId  群组id
     * @param memberId 群成员id
     * @param userInfo 当前登录用户
     * @return
     */
    Result<String> deleteMember(Integer groupId, Integer memberId, UserInfo userInfo);

    /**
     * 获取群成员列表
     * 
     * @author haoy
     * @param groupId
     * @return
     */
    Result<GroupMemberDto> getMemberList(Integer groupId);

    /**
     * 获取当前登录用户的群组列表
     * 
     * @author haoy
     * @param userInfo
     * @return
     */
    Result<List<GroupInfoDto>> getGroupList(UserInfo userInfo);

    /**
     * 群组打卡(当前登录用户)
     * 
     * @author haoy
     * @return
     */
    Result<String> groupClock(Integer groupId, UserInfo userInfo);

    /**
     * 搜索群组
     * 
     * @author haoy
     * @param groupDto
     * @return
     */
    Result<List<GroupInfoDto>> searchGroup(GroupDto groupDto);

    /**
     * 删除群组
     * 
     * @author haoy
     * @param groupId
     * @return
     */
    Result<String> deleteGroup(Integer groupId, UserInfo userInfo);
}
