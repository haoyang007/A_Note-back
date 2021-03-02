package com.hhhao.note.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.bill.BillDto;
import com.hhhao.note.dto.bill.BillInfoDto;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.entity.NotesBill;
import com.hhhao.note.mapper.NotesBillMapper;
import com.hhhao.note.service.NotesBillService;

@Service
public class NotesBillServiceImpl extends ServiceImpl<NotesBillMapper, NotesBill> implements NotesBillService {
    @Autowired
    private NotesBillMapper notesBillMapper;

    @Override
    public Result<List<NotesBill>> getBills(UserInfo userInfo) {
        NotesBill bill = new NotesBill();
        bill.setUserId(userInfo.getId());
        List<NotesBill> billList = notesBillMapper.selectList(new QueryWrapper<>(bill));
        return Result.ok(billList);
    }

    @Override
    public Result<BillInfoDto> getBillsOfMonth(UserInfo userInfo, Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Long startTime = calendar.getTimeInMillis();
        calendar.add(Calendar.MONTH, 1);
        Long endTime = calendar.getTimeInMillis();
        List<NotesBill> billList = notesBillMapper.getBillsOfMonth(userInfo, startTime, endTime);
        BigDecimal amount = new BigDecimal(0);
        billList.stream().forEach(bill -> {
            amount.add(bill.getAmount());
        });
        BillInfoDto billInfo = new BillInfoDto();
        billInfo.setTotalAmount(amount);
        billInfo.setBillList(billList);
        return Result.ok(billInfo);
    }

    @Override
    public Result<String> addBill(UserInfo userInfo, BillDto bill) {
        NotesBill notesBill = new NotesBill();
        BeanUtils.copyProperties(bill, notesBill);
        notesBill.setUserId(userInfo.getId());
        notesBillMapper.insert(notesBill);
        return Result.ok();
    }

    @Override
    public Result<String> deleteBill(Long billId) {
        notesBillMapper.deleteById(billId);
        return Result.ok();
    }

}
