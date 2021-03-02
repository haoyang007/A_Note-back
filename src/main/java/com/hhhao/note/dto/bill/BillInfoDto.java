package com.hhhao.note.dto.bill;

import java.math.BigDecimal;
import java.util.List;

import com.hhhao.note.entity.NotesBill;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 账单信息
 * 
 * @author haoy
 *
 */
@ApiModel("账单信息")
@Data
public class BillInfoDto {
    @ApiModelProperty("账单总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("账单列表")
    private List<NotesBill> billList;
}
