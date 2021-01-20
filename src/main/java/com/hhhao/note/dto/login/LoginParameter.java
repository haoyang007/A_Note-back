package com.hhhao.note.dto.login;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录参数信息
 * 
 * @author haoy
 *
 */
@ApiModel(description = "登录参数信息")
@Data
public class LoginParameter {

    @ApiModelProperty("登录邮箱")
    private String email;

    @ApiModelProperty("登录密码")
    private String password;

    @JsonIgnore // 在实体类向前台返回数据时用来忽略不想传递给前台的属性或接口。
    public boolean isEmpty() {
        return email == null || email.isEmpty() || password == null || password.isEmpty();
    }
}
