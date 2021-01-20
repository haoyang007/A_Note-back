package com.hhhao.note.dto.register;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户注册信息
 * 
 * @author haoy
 *
 */
@ApiModel(description = "用户注册信息")
@Data
public class RegisterParameter {
    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("用户昵称")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户头像URL")
    private String userImage;

    @JsonIgnore
    public boolean isEmpty() {
        return email == null || username == null || password == null || userImage == null;
    }
}
