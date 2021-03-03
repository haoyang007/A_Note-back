package com.hhhao.note.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.friend.FriendDto;
import com.hhhao.note.dto.friend.FriendParameter;
import com.hhhao.note.dto.friend.FriendSharedEventDto;
import com.hhhao.note.dto.friend.SearchParameter;
import com.hhhao.note.dto.friend.SendEventDto;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.entity.NotesFriend;

/**
 * 好友相关服务
 * 
 * @author haoy
 *
 */
public interface NotesFriendService extends IService<NotesFriend> {
    /**
     * 添加好友
     * 
     * @param param
     * @return
     */
    Result<String> saveFriend(UserInfo userInfo, FriendParameter param);

    /**
     * 删除好友
     * 
     * @param param
     * @return
     */
    Result<String> deleteFriend(UserInfo userInfo, FriendParameter param);

    /**
     * 获取好友列表
     * 
     * @return 好友信息列表
     */
    Result<List<FriendDto>> getFriendList(UserInfo userInfo);

    /**
     * 获取好友分享的事件
     * 
     * @param userInfo
     * @param pageParam
     * @return 被分享的事件列表
     */
    Result<List<FriendSharedEventDto>> getSharedEvent(UserInfo userInfo);

    /**
     * 给好友分享事件
     * 
     * @param userInfo
     * @param sendEventDto
     * @return
     */
    Result<String> sendEventToFriend(UserInfo userInfo, SendEventDto sendEventDto);

    /**
     * 搜索用户信息
     * 
     * @param param
     * @return
     */
    Result<UserInfo> searchUser(SearchParameter param);

    Result<String> deleteSharedEvent(Integer eventId, UserInfo userInfo);
}
