package com.hhhao.note.dto.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建群组所需信息
 * 
 * @author haoy
 *
 */
@ApiModel(description = "创建群组所需信息")
@Data
public class CreateGroupDto {
    @ApiModelProperty("群组名称")
    private String groupName;

    @ApiModelProperty("群组头像URL")
    private String groupImage;
}
