package com.hhhao.note.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.entity.NotesBill;

/**
 * 用户账单Mapper
 * 
 * @author haoy
 *
 */
public interface NotesBillMapper extends BaseMapper<NotesBill> {
    /**
     * 获取月账单列表
     * 
     * @author haoy
     * @param userInfo
     * @param startTime
     * @param endTime
     * @return
     */
    List<NotesBill> getBillsOfMonth(UserInfo user, Long startTime, Long endTime);
}
