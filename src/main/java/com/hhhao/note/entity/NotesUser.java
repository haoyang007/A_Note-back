package com.hhhao.note.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.hhhao.note.dto.register.RegisterParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户信息
 * 
 * @author haoy
 *
 */
@TableName("notes_user")
@ApiModel(description = "用户信息")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(staticName = "instance")
public class NotesUser extends Model<NotesUser> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户登录邮箱")
    private String email;

    @ApiModelProperty("用户昵称")
    private String username;

    @ApiModelProperty("用户登录密码")
    private String password;

    @ApiModelProperty("用户头像URL")
    private String imageUrl;

    public NotesUser(RegisterParameter param) {
        this.email = param.getEmail();
        this.username = param.getUsername();
        this.password = param.getPassword();
        this.imageUrl = param.getUserImage();
    }
}
