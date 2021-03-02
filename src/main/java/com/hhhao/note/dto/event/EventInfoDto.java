package com.hhhao.note.dto.event;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户事件信息
 * 
 * @author haoy
 *
 */
@ApiModel(description = "用户事件信息")
@Data
public class EventInfoDto {
    @ApiModelProperty("事件id")
    private Integer eventId;

    @ApiModelProperty("事件标题")
    private String title;

    @ApiModelProperty("事件内容")
    private String content;

    @ApiModelProperty("是否完成（0-未完成，1-已完成）")
    private Integer finished;

    @ApiModelProperty("事件备注")
    private String memo;

    @ApiModelProperty("事件创建时间(格式为：yyyy-MM-dd HH:mm:ss)")
    private String timeStr;

    @JsonIgnore
    @ApiModelProperty("事件创建时间(毫秒时间戳)")
    private Long time;

    @JsonIgnore
    @ApiModelProperty("事件相关图片信息")
    private String imageStr;

    @ApiModelProperty("事件的图片url列表")
    private List<String> imageList;

    @JsonIgnore
    @ApiModelProperty("事件相关图片信息")
    private String recordStr;

    @ApiModelProperty("事件的录音url列表")
    private List<String> recordList;
}
