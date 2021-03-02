package com.hhhao.note.service.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.event.EventInfoDto;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.entity.NotesEvent;
import com.hhhao.note.entity.NotesUserResource;
import com.hhhao.note.mapper.NotesEventMapper;
import com.hhhao.note.mapper.NotesUserResourceMapper;
import com.hhhao.note.service.NotesEventService;
import com.hhhao.note.util.enums.NormalEnum;
import com.hhhao.note.util.enums.RespondEnum;

/**
 * 事件相关服务实现
 * 
 * @author haoy
 *
 */
@Service
public class NotesEventServiceImpl extends ServiceImpl<NotesEventMapper, NotesEvent> implements NotesEventService {
    @Autowired
    NotesEventMapper eventMapper;
    @Autowired
    NotesUserResourceMapper resourceMapper;

    @Override
    public Result<List<EventInfoDto>> getEvents(UserInfo userInfo) {
        List<EventInfoDto> eventList = eventMapper.getEventsInfo(userInfo.getId());
        if (eventList != null) {
            eventList.stream().forEach(event -> {
                String images = event.getImageStr();
                String records = event.getRecordStr();
                Calendar calendar = Calendar.getInstance();
                Date date = new Date();
                date.setTime(event.getTime());
                calendar.setTimeInMillis(event.getTime());
                event.setTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                if (images != null) {
                    event.setImageList(Arrays.asList(images.trim().split(",")));
                }
                if (records != null) {
                    event.setRecordList(Arrays.asList(records.trim().split(",")));
                }
            });
        }
        return Result.ok(eventList);
    }

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public Result<String> addEvent(EventInfoDto eventInfo, UserInfo userInfo) {
        NotesEvent event = new NotesEvent(eventInfo);
        event.setUserId(userInfo.getId());
        event.setTime(Calendar.getInstance().getTimeInMillis());
        eventMapper.insert(event);
        Integer eventId = event.getId();
        List<String> imageList = eventInfo.getImageList();
        if (imageList != null) {
            imageList.stream().forEach(url -> {
                NotesUserResource resource = new NotesUserResource();
                resource.setType(NormalEnum.IMAGE.getValue());
                resource.setUrl(url);
                resource.setEventId(eventId);
                resourceMapper.insert(resource);
            });
        }
        List<String> recordList = eventInfo.getRecordList();
        if (recordList != null) {
            recordList.stream().forEach(url -> {
                NotesUserResource resource = new NotesUserResource();
                resource.setType(NormalEnum.RECORD.getValue());
                resource.setUrl(url);
                resource.setEventId(eventId);
                resourceMapper.insert(resource);
            });
        }
        return Result.ok();
    }

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public Result<String> deleteEvent(Integer eventId, UserInfo userInfo) {
        NotesEvent event = eventMapper.selectById(eventId);
        if (event == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "事件id不存在");
        }
        if (event.getUserId() != userInfo.getId()) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "该事件不属于当前用户");
        }
        eventMapper.deleteById(eventId);
        resourceMapper.delete(new QueryWrapper<>(new NotesUserResource().setEventId(eventId)));
        return Result.ok();
    }

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public Result<String> updateEvent(EventInfoDto eventInfo, UserInfo userInfo) {
        Integer eventId = eventInfo.getEventId();
        if (eventId == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "事件id不能为空");
        }
        NotesEvent event = eventMapper.selectById(eventId);
        if (event == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "事件id不存在");
        }
        if (event.getUserId() != userInfo.getId()) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "该事件不属于当前用户");
        }
        resourceMapper.delete(new QueryWrapper<>(new NotesUserResource().setEventId(eventId)));
        List<String> imageList = eventInfo.getImageList();
        if (imageList != null) {
            imageList.stream().forEach(url -> {
                NotesUserResource resource = new NotesUserResource();
                resource.setType(NormalEnum.IMAGE.getValue());
                resource.setUrl(url);
                resource.setEventId(eventId);
                resourceMapper.insert(resource);
            });
        }
        List<String> recordList = eventInfo.getRecordList();
        if (recordList != null) {
            recordList.stream().forEach(url -> {
                NotesUserResource resource = new NotesUserResource();
                resource.setType(NormalEnum.RECORD.getValue());
                resource.setUrl(url);
                resource.setEventId(eventId);
                resourceMapper.insert(resource);
            });
        }
        eventMapper.updateById(new NotesEvent(eventInfo).setTime(Calendar.getInstance().getTimeInMillis()));
        return Result.ok();
    }

}