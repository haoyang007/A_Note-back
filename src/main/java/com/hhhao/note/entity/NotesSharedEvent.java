package com.hhhao.note.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 被分享的事件信息
 * 
 * @author haoy
 *
 */
@TableName("notes_shared_event")
@ApiModel(description = "被分享的事件信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class NotesSharedEvent extends Model<NotesSharedEvent> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("分享者id")
    private Integer userIdFrom;

    @ApiModelProperty("接受者id")
    private Integer userIdTo;

    @ApiModelProperty("被分享的事件id")
    private Integer SharedEventId;

    @ApiModelProperty("分享时间（毫秒时间戳）")
    private Long sharedTime;
}
