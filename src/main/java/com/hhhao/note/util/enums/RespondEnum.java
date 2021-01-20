package com.hhhao.note.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回信息枚举
 *
 * @author haoy
 */
@Getter
@AllArgsConstructor
public enum RespondEnum {
    /** 请求成功 */
    OK("200", "请求成功"),
    /** 服务器异常 */
    ERROR("500", "服务器异常"),
    /** 参数错误 */
    BAD_REQUEST("400", "参数错误"),
    /** 数据异常 */
    DATA_ERROR("413", "数据异常"),
    /** 数据不存在 */
    NOT_FOUND("404", "数据不存在"),
    /** 未经授权 */
    UNAUTHORIZED("401", "你无权访问"),
    /** 禁止操作 */
    FORBIDDEN("403", "禁止操作"),;
    private String code;
    private String message;
}
