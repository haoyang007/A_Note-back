package com.hhhao.note.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 群组管理员信息
 * 
 * @author haoy
 *
 */
@ApiModel(description = "群组管理员信息")
@Data
@Accessors(chain = true)
public class GroupManager {
    @ApiModelProperty("id主键")
    private Integer id;

    @ApiModelProperty("群组id")
    private Integer groupId;

    @ApiModelProperty("管理员id")
    private Integer managerId;
}
