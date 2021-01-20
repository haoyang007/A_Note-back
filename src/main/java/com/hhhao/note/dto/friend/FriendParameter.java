package com.hhhao.note.dto.friend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 添加好友信息参数
 * 
 * @author haoy
 *
 */
@ApiModel(description = "添加好友信息参数")
@Data
public class FriendParameter {
    @ApiModelProperty("好友邮箱")
    private String email;
}
