package com.hhhao.note.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.event.EventInfoDto;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.entity.Event;

/**
 * 事件相关服务
 * 
 * @author haoy
 *
 */
public interface EventService extends IService<Event> {
    /**
     * 获取当前登录用户的事件列表
     * 
     * @author haoy
     * @return
     */
    Result<List<EventInfoDto>> getEvents(UserInfo userInfo);

    /**
     * 添加事件
     * 
     * @author haoy
     * @param eventInfo
     * @param userInfo
     * @return
     */
    Result<String> addEvent(EventInfoDto eventInfo, UserInfo userInfo);

    /**
     * 删除事件
     * 
     * @author haoy
     * @param eventId
     * @return
     */
    Result<String> deleteEvent(Integer eventId, UserInfo userInfo);

    /**
     * 更新事件
     * 
     * @author haoy
     * @param eventInfo
     * @param userInfo
     * @return
     */
    Result<String> updateEvent(EventInfoDto eventInfo, UserInfo userInfo);
}
