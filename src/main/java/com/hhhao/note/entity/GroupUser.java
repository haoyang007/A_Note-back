package com.hhhao.note.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 群组和成员的对应关系
 * 
 * @author haoy
 *
 */
@TableName("group_user")
@ApiModel(description = "群组和成员的对应关系")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(staticName = "instance")
public class GroupUser extends Model<GroupUser> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("群组id")
    private Integer groupId;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("用户打卡时间(未打卡则为null)")
    private Long clockTime;
}
