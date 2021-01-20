package com.hhhao.note.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.hhhao.note.dto.event.EventInfoDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户保存的事件信息
 * 
 * @author haoy
 *
 */
@TableName("event")
@ApiModel(description = "用户保存的事件信息")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(staticName = "instance")
public class Event extends Model<Event> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("事件主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("事件标题")
    private String title;

    @ApiModelProperty("事件内容")
    private String content;

    @ApiModelProperty("事件创建时间（毫秒时间戳）")
    private Long time;

    @ApiModelProperty("事件所属用户id")
    private Integer userId;

    public Event(EventInfoDto eventInfo) {
        this.id = eventInfo.getEventId();
        this.title = eventInfo.getTitle();
        this.content = eventInfo.getContent();
    }
}
