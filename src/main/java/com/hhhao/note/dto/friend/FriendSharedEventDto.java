package com.hhhao.note.dto.friend;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 好友分享事件信息
 * 
 * @author haoy
 *
 */
@ApiModel(description = "好友分享事件信息")
@Data
public class FriendSharedEventDto {
    @ApiModelProperty("事件id")
    private Integer eventId;

    @ApiModelProperty("事件标题")
    private String title;

    @ApiModelProperty("事件内容")
    private String content;

    @ApiModelProperty("事件图片列表")
    private List<String> imageList;

    @ApiModelProperty("分享者id")
    private Integer sharedUserId;

    @ApiModelProperty("分享时间（毫秒时间戳）")
    private Long sharedTime;

    @ApiModelProperty("是否完成（0-未完成，1-已完成）")
    private Integer finished;

    @ApiModelProperty("事件备注")
    private String memo;
}
