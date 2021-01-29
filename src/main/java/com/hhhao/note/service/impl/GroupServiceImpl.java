package com.hhhao.note.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.group.CreateGroupDto;
import com.hhhao.note.dto.group.GroupDto;
import com.hhhao.note.dto.group.GroupInfoDto;
import com.hhhao.note.dto.group.GroupMemberDto;
import com.hhhao.note.dto.group.MemberDetail;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.entity.Group;
import com.hhhao.note.entity.GroupManager;
import com.hhhao.note.entity.GroupUser;
import com.hhhao.note.mapper.GroupManagerMapper;
import com.hhhao.note.mapper.GroupMapper;
import com.hhhao.note.mapper.GroupUserMapper;
import com.hhhao.note.service.GroupService;
import com.hhhao.note.util.enums.RespondEnum;

/**
 * 群组相关服务实现
 * 
 * @author haoy
 *
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private GroupUserMapper groupUserMapper;
    @Autowired
    private GroupManagerMapper groupManagerMapper;

    @Override
    public Result<String> createGroup(CreateGroupDto groupInfo, UserInfo userInfo) {
        String groupName = groupInfo.getGroupName();
        String groupImage = groupInfo.getGroupImage();
        if (groupName == null || groupName.isEmpty()) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组名称不能为空");
        }
        if (groupImage == null || groupImage.isEmpty()) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组头像不能为空");
        }
        Group group = Group.instance();
        group.setName(groupName);
        group.setImageUrl(groupImage);
        group.setLeaderId(userInfo.getId());
        groupMapper.insert(group);
        GroupManager manager = new GroupManager();
        manager.setGroupId(group.getId());
        manager.setManagerId(userInfo.getId());
        groupManagerMapper.insert(manager);
        return Result.ok();
    }

    @Override
    public Result<String> joinGroup(Integer groupId, UserInfo userInfo) {
        if (groupMapper.selectById(groupId) == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组(id)不存在");
        }
        groupUserMapper.insert(GroupUser.instance().setGroupId(groupId).setUserId(userInfo.getId()));
        return Result.ok();
    }

    @Override
    public Result<String> quitGroup(Integer groupId, UserInfo userInfo) {
        if (groupMapper.selectById(groupId) == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组(id)不存在");
        }
        GroupUser groupUser = GroupUser.instance();
        groupUser.setGroupId(groupId);
        groupUser.setUserId(userInfo.getId());
        if (groupUserMapper.selectList(new QueryWrapper<>(groupUser)) == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "当前用户不在指定群组中");
        }
        groupUserMapper.delete(new QueryWrapper<>(groupUser));
        return Result.ok();
    }

    @Override
    public Result<String> deleteMember(Integer groupId, Integer memberId, UserInfo userInfo) {
        Group group = groupMapper.selectById(groupId);
        if (group == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组(id)不存在");
        }
        if (!userInfo.getId().equals(group.getLeaderId())) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "当前用户不是群主或管理员，无法删除");
        }
        GroupUser groupUser = GroupUser.instance();
        groupUser.setGroupId(groupId);
        groupUser.setUserId(memberId);
        if (groupUserMapper.selectList(new QueryWrapper<>(groupUser)) == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "删除用户不在指定群组中");
        }
        groupUserMapper.delete(new QueryWrapper<>(groupUser));
        return Result.ok();
    }

    @Override
    public Result<GroupMemberDto> getMemberList(Integer groupId) {
        Group group = groupMapper.selectById(groupId);
        if (group == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组(id)不存在");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Long startTime = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        Long endTime = calendar.getTimeInMillis();
        List<MemberDetail> memberDetails = groupMapper.getMemberDetails(groupId, startTime, endTime);
        GroupMemberDto groupMembers = new GroupMemberDto();
        groupMembers.setGroupId(groupId);
        groupMembers.setMemberList(memberDetails);
        return Result.ok(groupMembers);
    }

    @Override
    public Result<List<GroupInfoDto>> getGroupList(UserInfo userInfo) {
        GroupUser groupUser = GroupUser.instance();
        groupUser.setUserId(userInfo.getId());
        List<GroupInfoDto> groupList = groupUserMapper.getGroupListInfo(userInfo.getId());
        return Result.ok(groupList);
    }

    @Override
    public Result<String> groupClock(Integer groupId, UserInfo userInfo) {
        Long time = Calendar.getInstance().getTimeInMillis();
        groupUserMapper.updateClockTime(time, groupId, userInfo.getId());
        return Result.ok();
    }

    @Override
    public Result<List<GroupInfoDto>> searchGroup(GroupDto groupDto) {
        Integer groupId = groupDto.getGroupId();
        String groupName = groupDto.getGroupName();
        List<GroupInfoDto> groupList = groupMapper.getGroupInfo(groupId, groupName);
        return Result.ok(groupList);
    }

    @Override
    public Result<String> deleteGroup(Integer groupId, UserInfo userInfo) {
        Group group = groupMapper.selectById(groupId);
        if (group == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组(id)不存在");
        }
        if (group.getLeaderId() != userInfo.getId()) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "您没有权限删除该群组");
        }
        groupMapper.deleteById(groupId);
        return Result.ok();
    }
}
