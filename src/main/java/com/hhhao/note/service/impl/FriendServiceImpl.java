package com.hhhao.note.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.friend.FriendDto;
import com.hhhao.note.dto.friend.FriendParameter;
import com.hhhao.note.dto.friend.FriendSharedEventDto;
import com.hhhao.note.dto.friend.SearchParameter;
import com.hhhao.note.dto.friend.SendEventDto;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.entity.Friend;
import com.hhhao.note.entity.SharedEvent;
import com.hhhao.note.entity.User;
import com.hhhao.note.mapper.FriendMapper;
import com.hhhao.note.mapper.SharedEventMapper;
import com.hhhao.note.mapper.UserMapper;
import com.hhhao.note.service.FriendService;
import com.hhhao.note.util.enums.RespondEnum;

/**
 * 好友相关服务实现
 * 
 * @author haoy
 *
 */
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements FriendService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    FriendMapper friendMapper;
    @Autowired
    SharedEventMapper sharedEventMapper;

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public Result<String> saveFriend(UserInfo userInfo, FriendParameter param) {
        if (param == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "好友邮箱不能为空");
        }
        String email = param.getEmail();
        if (email == null || email.isEmpty()) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "好友邮箱不能为空");
        }
        User addUser = userMapper.selectByEmail(email);
        if (addUser == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "用户（邮箱）不存在");
        }
        Integer userId = userInfo.getId();
        Integer friendId = addUser.getId();
        Friend friend = Friend.instance();
        QueryWrapper<Friend> friendQuery = new QueryWrapper<>(friend.setUserId(friendId).setFriendId(userId));
        QueryWrapper<Friend> userQuery = new QueryWrapper<>(friend.setUserId(userId).setFriendId(friendId));
        if (friendMapper.selectOne(friendQuery) != null || friendMapper.selectOne(userQuery) != null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "好友已存在，请勿重复添加");
        }
        friend.setTime(Calendar.getInstance().getTimeInMillis());
        friendMapper.insert(friend);
        return Result.ok();
    }

    @Override
    public Result<String> deleteFriend(UserInfo userInfo, FriendParameter param) {
        if (param == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "好友邮箱不能为空");
        }
        String email = param.getEmail();
        if (email == null || email.isEmpty()) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "好友邮箱不能为空");
        }
        User user = userMapper.selectByEmail(email);
        if (user == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "用户（邮箱）不存在");
        }
        Integer userId = userInfo.getId();
        Integer friendId = user.getId();
        Friend friend = Friend.instance();
        friend.setUserId(userId);
        friend.setFriendId(friendId);
        friendMapper.delete(new QueryWrapper<Friend>(friend));
        friend.setUserId(friendId);
        friend.setFriendId(userId);
        friendMapper.delete(new QueryWrapper<Friend>(friend));
        return Result.ok();
    }

    @Override
    public Result<List<FriendDto>> getFriendList(UserInfo userInfo) {
        List<FriendDto> friendList = friendMapper.getFriends(userInfo.getId());
        return Result.ok(friendList);
    }

    @Override
    public Result<List<FriendSharedEventDto>> getSharedEvent(UserInfo userInfo) {
        List<FriendSharedEventDto> sharedEvents = sharedEventMapper.getSharedEvent(userInfo);
        return Result.ok(sharedEvents);
    }

    @Override
    public Result<String> sendEventToFriend(UserInfo userInfo, SendEventDto sendEventDto) {
        if (sendEventDto == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "事件分享信息不能为空");
        }
        Integer toUserId = sendEventDto.getToUserId();
        Integer eventId = sendEventDto.getEventId();
        if (toUserId == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "事件接收用户id不能为空");
        }
        if (eventId == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "被分享的事件id不能为空");
        }
        SharedEvent sharedEvent = new SharedEvent();
        sharedEvent.setUserIdFrom(userInfo.getId());
        sharedEvent.setSharedEventId(eventId);
        sharedEvent.setUserIdTo(toUserId);
        sharedEvent.setSharedTime(Calendar.getInstance().getTimeInMillis());
        sharedEventMapper.insert(sharedEvent);
        return Result.ok();
    }

    @Override
    public Result<UserInfo> searchUser(SearchParameter param) {
        if (param == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "搜索条件不能为空");
        }
        String email = param.getEmail();
        if (email == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "用户邮箱和昵称不能同时为空");
        }
        User user = userMapper.selectByEmail(email);
        if (user == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "用户（邮箱）不存在");
        }
        return Result.ok(new UserInfo(user));

    }

}
