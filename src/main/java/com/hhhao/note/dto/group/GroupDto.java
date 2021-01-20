package com.hhhao.note.dto.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 群组信息
 * 
 * @author haoy
 *
 */
@ApiModel(description = "群组信息")
@Data
public class GroupDto {
    @ApiModelProperty("群组id")
    private Integer groupId;

    @ApiModelProperty("群组名称")
    private String groupName;
}
