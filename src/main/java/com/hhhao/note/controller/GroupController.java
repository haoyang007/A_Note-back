package com.hhhao.note.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhhao.note.annotation.CurrentUser;
import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.group.CreateGroupDto;
import com.hhhao.note.dto.group.GroupDto;
import com.hhhao.note.dto.group.GroupInfoDto;
import com.hhhao.note.dto.group.GroupMemberDto;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.service.NotesGroupService;
import com.hhhao.note.util.enums.RespondEnum;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 群组相关API
 * 
 * @author haoy
 *
 */
@RestController
@RequestMapping("/api/group")
public class GroupController {
    @Autowired
    NotesGroupService groupService;

    @ApiOperation(value = "创建群组", httpMethod = "POST")
    @PostMapping("/create")
    public Result<String> createGroup(@RequestBody CreateGroupDto groupInfo,
            @ApiIgnore @CurrentUser UserInfo userInfo) {
        if (groupInfo == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组信息不能为空");
        }
        return groupService.createGroup(groupInfo, userInfo);
    }

    @ApiOperation(value = "加入群组", httpMethod = "GET")
    @GetMapping("/join/{id}")
    @ApiImplicitParam(name = "id", value = "群组id", dataType = "Integer")
    public Result<String> joinGroup(@PathVariable Integer id, @ApiIgnore @CurrentUser UserInfo userInfo) {
        if (id == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组id不能为空");
        }
        return groupService.joinGroup(id, userInfo);
    }

    @ApiOperation(value = "退出群组", httpMethod = "GET")
    @GetMapping("/quit/{id}")
    @ApiImplicitParam(name = "id", value = "群组id", dataType = "Integer")
    public Result<String> quitGroup(@PathVariable Integer id, @ApiIgnore @CurrentUser UserInfo userInfo) {
        if (id == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组id不能为空");
        }
        return groupService.quitGroup(id, userInfo);
    }

    @ApiOperation(value = "删除群组成员", httpMethod = "GET")
    @GetMapping("/delete/member")
    public Result<String> deleteMember(Integer groupId, Integer memberId, @ApiIgnore @CurrentUser UserInfo userInfo) {
        if (groupId == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组id不能为空");
        }
        if (memberId == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "成员id不能为空");
        }
        return groupService.deleteMember(groupId, memberId, userInfo);
    }

    @ApiOperation(value = "获取群组成员列表", httpMethod = "GET")
    @GetMapping("/{id}/member")
    @ApiImplicitParam(name = "id", value = "群组id", dataType = "Integer")
    public Result<GroupMemberDto> deleteMember(@PathVariable Integer id) {
        if (id == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组id不能为空");
        }
        return groupService.getMemberList(id);
    }

    @ApiOperation(value = "获取当前登录用户群组列表", httpMethod = "GET")
    @GetMapping("/list")
    public Result<List<GroupInfoDto>> groupList(@ApiIgnore @CurrentUser UserInfo userInfo) {
        return groupService.getGroupList(userInfo);
    }

    @ApiOperation(value = "群组用户打卡", httpMethod = "GET")
    @GetMapping("/clock")
    public Result<String> groupClock(Integer groupId, @ApiIgnore @CurrentUser UserInfo userInfo) {
        if (groupId == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组id不能为空");
        }
        return groupService.groupClock(groupId, userInfo);
    }

    @ApiOperation(value = "搜索群组", httpMethod = "POST")
    @PostMapping("/search")
    public Result<List<GroupInfoDto>> groupClock(@RequestBody GroupDto groupInfo,
            @ApiIgnore @CurrentUser UserInfo userInfo) {
        if (groupInfo == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组信息不能为空");
        }
        return groupService.searchGroup(groupInfo);
    }

    @ApiOperation(value = "删除群组", httpMethod = "GET")
    @GetMapping("/delete/{id}")
    @ApiImplicitParam(name = "id", value = "群组id", dataType = "Integer")
    public Result<String> deleteGroup(@PathVariable Integer id, @ApiIgnore @CurrentUser UserInfo userInfo) {
        if (id == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组id不能为空");
        }
        return groupService.deleteGroup(id, userInfo);
    }

    @ApiOperation(value = "群组管理员为群组成员打卡", httpMethod = "GET")
    @GetMapping("{groupId}/clock/{memberId}")
    @ApiImplicitParams({ @ApiImplicitParam(name = "groupId", value = "群组id", dataType = "Integer"),
            @ApiImplicitParam(name = "memberId", value = "群成员id", dataType = "Integer") })

    public Result<String> memberClock(@PathVariable Integer groupId, @PathVariable Integer memberId,
            @ApiIgnore @CurrentUser UserInfo userInfo) {
        if (groupId == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "群组id不能为空");
        }
        if (memberId == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "成员id不能为空");
        }
        return groupService.memberClock(groupId, userInfo, memberId);
    }
}
