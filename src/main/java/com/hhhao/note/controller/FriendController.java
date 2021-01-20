package com.hhhao.note.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hhhao.note.annotation.CurrentUser;
import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.friend.FriendDto;
import com.hhhao.note.dto.friend.FriendParameter;
import com.hhhao.note.dto.friend.FriendSharedEventDto;
import com.hhhao.note.dto.friend.SearchParameter;
import com.hhhao.note.dto.friend.SendEventDto;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.service.FriendService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 好友相关API
 * 
 * @author haoy
 *
 */
@RestController
@RequestMapping("/api/friend")
public class FriendController {
    @Autowired
    FriendService friendService;

    @ApiOperation(value = "通过用户信息为当前登录用户添加好友", httpMethod = "POST")
    @PostMapping("/save")
    public Result<String> save(@ApiIgnore @CurrentUser UserInfo userInfo, @RequestBody FriendParameter param) {
        return friendService.saveFriend(userInfo, param);
    }

    @ApiOperation(value = "通过用户信息为当前登录用户删除好友", httpMethod = "POST")
    @PostMapping("/delete")
    public Result<String> delete(@ApiIgnore @CurrentUser UserInfo userInfo, @RequestBody FriendParameter param) {
        return friendService.deleteFriend(userInfo, param);
    }

    @ApiOperation(value = "获取当前用户的好友列表", httpMethod = "GET")
    @GetMapping("/get")
    public Result<List<FriendDto>> getFriendList(@ApiIgnore @CurrentUser UserInfo userInfo) {
        return friendService.getFriendList(userInfo);
    }

    @ApiOperation(value = "搜索邮箱获取用户信息", httpMethod = "POST")
    @PostMapping("/search")
    public Result<UserInfo> searchUser(@RequestBody SearchParameter param) {
        return friendService.searchUser(param);
    }

    @ApiOperation(value = "获取好友分享的事件", httpMethod = "GET")
    @GetMapping("/event/get")
    public Result<List<FriendSharedEventDto>> getSharedEvent(@ApiIgnore @CurrentUser UserInfo userInfo) {
        return friendService.getSharedEvent(userInfo);
    }

    @ApiOperation(value = "分享事件给好友", httpMethod = "POST")
    @PostMapping("/event/shared")
    public Result<String> sendEventToFriend(@ApiIgnore @CurrentUser UserInfo userInfo, SendEventDto sendEvent) {
        return friendService.sendEventToFriend(userInfo, sendEvent);
    }
}
