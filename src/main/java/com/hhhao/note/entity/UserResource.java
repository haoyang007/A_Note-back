package com.hhhao.note.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户事件资源信息
 * 
 * @author haoy
 *
 */
@TableName("user_resource")
@ApiModel(description = "用户事件资源信息")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(staticName = "instance")
public class UserResource extends Model<UserResource> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("资源主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty("资源类型(1--图片,2--音频,3--视频)")
    private Integer type;

    @ApiModelProperty("资源URL")
    private String url;

    @ApiModelProperty("所属用户id(不属于任何用户则为null)")
    private Integer userId;

    @ApiModelProperty("所属事件id(不属于任何事件则为null)")
    private Integer eventId;
}