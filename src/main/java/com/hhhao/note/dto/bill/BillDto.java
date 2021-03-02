package com.hhhao.note.dto.bill;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 账单信息
 * 
 * @author haoy
 *
 */
@ApiModel(description = "账单信息")
@Data
public class BillDto {
    @ApiModelProperty("消费金额")
    private BigDecimal amount;

    @ApiModelProperty("消费时间")
    private Long time;

    @ApiModelProperty("消费类型（0-其他支出，1-生活支出，2-学业支出，3-娱乐支出，4-交通支出）")
    private Integer type;

    @ApiModelProperty("备注")
    private String memo;
}
