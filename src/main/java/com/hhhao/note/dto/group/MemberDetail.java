package com.hhhao.note.dto.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 群组成员详情
 * 
 * @author haoy
 *
 */
@ApiModel(description = "群组成员详情")
@Data
public class MemberDetail {
    @ApiModelProperty("成员id")
    private Integer memberId;

    @ApiModelProperty("成员email")
    private String email;

    @ApiModelProperty("成员用户名")
    private String username;

    @ApiModelProperty("成员头像")
    private String userImage;

    @ApiModelProperty("是否打卡(0--未打卡,1--已打卡)")
    private Integer clockStatus;

    @ApiModelProperty("群组职务(0--普通成员,1--群组管理员,2--群主)")
    private Integer post;
}