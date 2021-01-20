package com.hhhao.note.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 常用变量枚举
 * 
 * @author haoy
 *
 */
@AllArgsConstructor
@Getter
public enum NormalEnum {

    /** 图片类型 */
    IMAGE("resource_type", 1, "用户事件图片资源"),
    /** 音频类型 */
    RECORD("resource_type", 2, "用户事件录音资源"),
    /** 视频类型 */
    VIDEO("resource_type", 3, "用户事件视频资源"),

    ;
    private String type;
    private Integer value;
    private String desc;
}
