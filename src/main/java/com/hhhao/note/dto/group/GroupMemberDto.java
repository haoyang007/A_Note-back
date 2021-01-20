package com.hhhao.note.dto.group;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 群成员信息
 * 
 * @author haoy
 *
 */
@ApiModel(description = "群成员信息")
@Data
public class GroupMemberDto {
    @ApiModelProperty("群组id")
    private Integer groupId;

    @ApiModelProperty("成员列表")
    private List<MemberDetail> memberList;
}
