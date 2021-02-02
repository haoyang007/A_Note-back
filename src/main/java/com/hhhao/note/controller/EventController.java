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
import com.hhhao.note.dto.event.EventInfoDto;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.service.NotesEventService;
import com.hhhao.note.util.enums.RespondEnum;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 事件相关API
 * 
 * @author haoy
 *
 */
@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    NotesEventService eventService;

    @ApiOperation(value = "获取当前用户的事件列表", httpMethod = "GET")
    @GetMapping("/get")
    public Result<List<EventInfoDto>> getEventList(@ApiIgnore @CurrentUser UserInfo userInfo) {
        return eventService.getEvents(userInfo);
    }

    @ApiOperation(value = "添加事件", httpMethod = "POST")
    @PostMapping("/add")
    public Result<String> addEvent(@RequestBody EventInfoDto eventInfo, @ApiIgnore @CurrentUser UserInfo userInfo) {
        if (eventInfo == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "事件信息不能为空");
        }
        return eventService.addEvent(eventInfo, userInfo);
    }

    @ApiOperation(value = "删除事件", httpMethod = "GET")
    @GetMapping("/delete/{id}")
    @ApiImplicitParam(name = "id", value = "事件id", dataType = "Integer")
    public Result<String> addEvent(@PathVariable Integer id, @ApiIgnore @CurrentUser UserInfo userInfo) {
        if (id == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "事件id不能为空");
        }
        return eventService.deleteEvent(id, userInfo);
    }

    @ApiOperation(value = "更新事件信息", httpMethod = "POST")
    @PostMapping("/update")
    public Result<String> updateEvent(@RequestBody EventInfoDto eventInfo, @ApiIgnore @CurrentUser UserInfo userInfo) {
        if (eventInfo == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "事件信息不能为空");
        }
        return eventService.updateEvent(eventInfo, userInfo);
    }
}
