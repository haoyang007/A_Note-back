package com.hhhao.note.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhhao.note.dto.event.EventInfoDto;
import com.hhhao.note.entity.Event;

/**
 * 用户事件信息Mapper
 * 
 * @author haoy
 *
 */
public interface EventMapper extends BaseMapper<Event> {
    /**
     * 获取用户事件列表
     * 
     * @author haoy
     * @param userId 用户id
     * @return
     */
    List<EventInfoDto> getEventsInfo(@Param("userId") Integer userId);
}
