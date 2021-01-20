package com.hhhao.note.dto.friend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 好友相关信息
 * 
 * @author haoy
 *
 */
@ApiModel(description = "好友相关信息")
@Data
public class FriendDto {
    @ApiModelProperty("好友邮箱")
    private String email;

    @ApiModelProperty("好友昵称")
    private String username;

    @ApiModelProperty("好友头像URL")
    private String userImage;
}
