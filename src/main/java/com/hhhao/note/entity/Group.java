package com.hhhao.note.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 群组信息
 * 
 * @author haoy
 *
 */
@TableName("group")
@ApiModel(description = "群组信息")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(staticName = "instance")
public class Group extends Model<Group> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("群组主键id")
    private Integer id;

    @ApiModelProperty("群组头像URL")
    private String imageUrl;

    @ApiModelProperty("群组名")
    private String name;

    @ApiModelProperty("群组管理员id")
    private Integer leaderId;
}
