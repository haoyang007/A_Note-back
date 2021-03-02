package com.hhhao.note.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.bill.BillDto;
import com.hhhao.note.dto.bill.BillInfoDto;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.entity.NotesBill;

/**
 * 账单相关服务
 * 
 * @author haoy
 *
 */
public interface NotesBillService extends IService<NotesBill> {

    /**
     * 获取当前用户的所有账单
     * 
     * @author haoy
     * @param userInfo
     * @return
     */
    Result<List<NotesBill>> getBills(UserInfo userInfo);

    /**
     * 获取当前用户的月账单
     * 
     * @author haoy
     * @param userInfo
     * @param date
     * @return
     */
    Result<BillInfoDto> getBillsOfMonth(UserInfo userInfo, Integer year, Integer month);

    /**
     * 添加账单
     * 
     * @author haoy
     * @param userInfo
     * @param bill
     * @return
     */
    Result<String> addBill(UserInfo userInfo, BillDto bill);

    /**
     * 删除账单
     * 
     * @author haoy
     * @param userInfo
     * @param billId
     * @return
     */
    Result<String> deleteBill(Long billId);

}
