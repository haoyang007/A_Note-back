package com.hhhao.note.dto.friend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 搜索用户参数
 * 
 * @author haoy
 *
 */
@ApiModel(description = "搜索用户参数")
@Data
public class SearchParameter {
    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("用户昵称")
    private String username;
}
