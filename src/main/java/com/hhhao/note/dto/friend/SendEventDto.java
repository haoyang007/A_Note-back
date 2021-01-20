package com.hhhao.note.dto.friend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 向好友发送的事件信息
 * 
 * @author haoy
 *
 */
@ApiModel(description = "向好友发送的事件信息")
@Data
public class SendEventDto {
    @ApiModelProperty("接收方用户id")
    private Integer toUserId;

    @ApiModelProperty("要分享的事件id")
    private Integer eventId;
}
