package com.hhhao.note.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("notes_group_manager")
@ApiModel(description = "群组管理员信息")
@Data
@Accessors(chain = true)
public class NotesGroupManager {
    @ApiModelProperty("id主键")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("群组id")
    private Integer groupId;

    @ApiModelProperty("管理员id")
    private Integer managerId;
}
