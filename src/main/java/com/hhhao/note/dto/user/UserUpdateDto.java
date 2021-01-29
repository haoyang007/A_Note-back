package com.hhhao.note.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户更新信息
 * 
 * @author haoy
 *
 */
@ApiModel(description = "用户更新信息")
@Data
public class UserUpdateDto {
    @ApiModelProperty("用户名(昵称)")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;
}
