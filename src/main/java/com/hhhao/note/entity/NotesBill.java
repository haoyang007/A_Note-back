package com.hhhao.note.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户账单信息
 * 
 * @author haoy
 *
 */

@TableName("notes_bill")
@ApiModel(description = "用户账单信息")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class NotesBill extends Model<NotesBill> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("消费金额")
    private BigDecimal amount;

    @ApiModelProperty("消费时间")
    private Long time;

    @ApiModelProperty("消费类型（0-其他支出，1-生活支出，2-学业支出，3-娱乐支出，4-交通支出）")
    private Integer type;

    @ApiModelProperty("备注")
    private String memo;
}
