package com.hhhao.note.dto.login;

import com.hhhao.note.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 当前登录用户信息
 * 
 * @author haoy
 *
 */
@ApiModel(description = "当前登录用户信息")
@Data
@AllArgsConstructor
public class UserInfo {
    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("用户登录邮箱")
    private String email;

    @ApiModelProperty("用户昵称")
    private String username;

    @ApiModelProperty("用户头像URL")
    private String userImage;

    public UserInfo(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.userImage = user.getImageUrl();
    }
}
