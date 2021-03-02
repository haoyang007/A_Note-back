package com.hhhao.note.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hhhao.note.annotation.CurrentUser;
import com.hhhao.note.dto.Result;
import com.hhhao.note.dto.bill.BillDto;
import com.hhhao.note.dto.bill.BillInfoDto;
import com.hhhao.note.dto.login.UserInfo;
import com.hhhao.note.entity.NotesBill;
import com.hhhao.note.service.NotesBillService;
import com.hhhao.note.util.enums.RespondEnum;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 账单相关API
 * 
 * @author haoy
 *
 */
@RestController
@RequestMapping("/api/bill")
public class BillController {
    @Autowired
    private NotesBillService notesBillService;

    @ApiOperation(value = "获取当前用户的所有账单", httpMethod = "GET")
    @GetMapping("/get")
    Result<List<NotesBill>> getBills(@ApiIgnore @CurrentUser UserInfo userInfo) {
        return notesBillService.getBills(userInfo);
    }

    @ApiOperation(value = "获取当前用户的月账单信息", httpMethod = "GET")
    @GetMapping("/get/month")
    Result<BillInfoDto> getBillsOfMonth(@RequestParam Integer year, @RequestParam Integer month,
            @ApiIgnore @CurrentUser UserInfo userInfo) {
        if (year == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "年份不能为空");
        }
        if (month == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "月份不能为空");
        }
        return notesBillService.getBillsOfMonth(userInfo, year, month);
    }

    @ApiOperation(value = "添加账单", httpMethod = "POST")
    @PostMapping("/add")
    Result<String> addBill(@RequestBody BillDto bill, @ApiIgnore @CurrentUser UserInfo userInfo) {
        if (bill == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "账单信息不能为空");
        }
        return notesBillService.addBill(userInfo, bill);
    }

    @ApiOperation(value = "删除账单", httpMethod = "GET")
    @GetMapping("/delete")
    Result<String> deleteBill(@RequestParam Long billId) {
        if (billId == null) {
            return Result.error(RespondEnum.BAD_REQUEST.getCode(), "账单id不能为空");
        }
        return notesBillService.deleteBill(billId);
    }
}
