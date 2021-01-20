package com.hhhao.note.dto.image;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 图片信息
 * 
 * @author haoy
 *
 */
@ApiModel(description = "图片信息")
@Data
public class ImageInfo {
    @ApiModelProperty("图片URL")
    private String imageUrl;

    @ApiModelProperty("图片名称")
    private String imageName;
}
