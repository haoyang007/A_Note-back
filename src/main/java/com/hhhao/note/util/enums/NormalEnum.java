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

    /** 其他支出 */
    OTHER("expense_type", 0, "其他支出"),
    /** 生活支出 */
    LIVE("expense_type", 1, "生活支出"),
    /** 学业支出 */
    STUDY("expense_type", 2, "学业支出"),
    /** 娱乐支出 */
    ENTERTAINMENT("expense_type", 3, "娱乐支出"),
    /** 交通支出 */
    TRAFFIC("expense_type", 4, "交通支出"),

    ;
    private String type;
    private Integer value;
    private String desc;
}
