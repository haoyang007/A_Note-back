package com.hhhao.note.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.group.CreateGroupDto;
import com.hhhao.note.dto.group.GroupDto;
import com.hhhao.note.dto.group.GroupInfoDto;
import com.hhhao.note.dto.group.GroupMemberDto;
import com.hhhao.note.dto.group.MemberDetail;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.entity.NotesGroup;
import com.hhhao.note.entity.NotesGroupUser;
import com.hhhao.note.mapper.NotesGroupManagerMapper;
import com.hhhao.note.mapper.NotesGroupMapper;
import com.hhhao.note.mapper.NotesGroupUserMapper;
import com.hhhao.note.service.NotesGroupService;
import com.hhhao.note.util.enums.RespondEnum;

/**
 * 群组相关服务实现
 * 
 * @author haoy
 *
 */
@Service
public class NotesGroupServiceImpl extends ServiceImpl<NotesGroupMapper, NotesGroup> implements NotesGroupService {
    @Autowired
    private NotesGroupMapper groupMapper;
    @Autowired
    private NotesGroupUserMapper groupUserMapper;
    @Autowired
    private NotesGroupManagerMapper groupManagerMapper;

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public Result<String> createGroup(CreateGroupDto groupInfo, UserInfo userInfo) {
        String groupName = groupInfo.getGroupName();
        String groupImage = groupInfo.getGroupImage();
        if (groupName == null || groupName.isEmpty()) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组名称不能为空");
        }
        if (groupImage == null || groupImage.isEmpty()) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组头像不能为空");
        }
        NotesGroup group = NotesGroup.instance();
        group.setName(groupName);
        group.setImageUrl(groupImage);
        group.setLeaderId(userInfo.getId());
        groupMapper.insert(group);
        NotesGroupUser groupMember = new NotesGroupUser();
        groupMember.setGroupId(group.getId());
        groupMember.setUserId(userInfo.getId());
        groupUserMapper.insert(groupMember);
        return Result.ok();
    }

    @Override
    public Result<String> joinGroup(Integer groupId, UserInfo userInfo) {
        if (groupMapper.selectById(groupId) == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组(id)不存在");
        }
        NotesGroupUser groupUser = new NotesGroupUser();
        groupUser.setGroupId(groupId).setUserId(userInfo.getId());
        if (groupUserMapper.selectOne(new QueryWrapper<>(groupUser)) != null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组成员已存在");
        }
        groupUserMapper.insert(groupUser);
        return Result.ok();
    }

    @Override
    public Result<String> quitGroup(Integer groupId, UserInfo userInfo) {
        if (groupMapper.selectById(groupId) == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组(id)不存在");
        }
        NotesGroupUser groupUser = new NotesGroupUser();
        groupUser.setGroupId(groupId);
        groupUser.setUserId(userInfo.getId());
        if (groupUserMapper.selectOne(new QueryWrapper<>(groupUser)) == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "当前用户不在指定群组中");
        }
        groupUserMapper.delete(new QueryWrapper<>(groupUser));
        return Result.ok();
    }

    @Override
    public Result<String> deleteMember(Integer groupId, Integer memberId, UserInfo userInfo) {
        NotesGroup group = groupMapper.selectById(groupId);
        if (group == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组(id)不存在");
        }
        if (!userInfo.getId().equals(group.getLeaderId())) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "当前用户不是群主或管理员，无法删除");
        }
        NotesGroupUser groupUser = new NotesGroupUser();
        groupUser.setGroupId(groupId);
        groupUser.setUserId(memberId);
        if (groupUserMapper.selectOne(new QueryWrapper<>(groupUser)) == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "删除用户不在指定群组中");
        }
        groupUserMapper.delete(new QueryWrapper<>(groupUser));
        return Result.ok();
    }

    @Override
    public Result<GroupMemberDto> getMemberList(Integer groupId) {
        NotesGroup group = groupMapper.selectById(groupId);
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
        NotesGroupUser groupUser = new NotesGroupUser();
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
        NotesGroup group = groupMapper.selectById(groupId);
        if (group == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组(id)不存在");
        }
        if (group.getLeaderId() != userInfo.getId()) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "您没有权限删除该群组");
        }
        groupMapper.deleteById(groupId);
        NotesGroupUser groupUser = new NotesGroupUser();
        groupUser.setGroupId(groupId);
        groupUserMapper.delete(new QueryWrapper<>(groupUser));
        return Result.ok();
    }

    @Override
    public Result<String> memberClock(Integer groupId, UserInfo userInfo, Integer memberId) {
        NotesGroup group = groupMapper.selectById(groupId);
        if (group == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组(id)不存在");
        }
        if (group.getLeaderId() != userInfo.getId()) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "您没有权限为群成员打卡");
        }
        NotesGroupUser clockUser = new NotesGroupUser();
        clockUser.setGroupId(groupId).setUserId(memberId);
        NotesGroupUser groupUser = groupUserMapper.selectOne(new QueryWrapper<>(clockUser));
        if (groupUser == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组成员不存在");
        }
        groupUser.setClockTime(Calendar.getInstance().getTimeInMillis());
        groupUserMapper.updateById(groupUser);

        return Result.ok();
    }
}
