package com.hhhao.note.dto;

import com.hhhao.note.util.enums.RespondEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * API返回信息
 * 
 * @author Zhangjc
 *
 * @param <T> Api返回的内容的真实类型
 */
@ApiModel(description = "API返回信息")
@Data
@Accessors(chain = true)
public class Result<T> {

    @ApiModelProperty("api请求状态，参考RespondEnum定义")
    private String status;

    @ApiModelProperty("api请求不成功时，此字段记录对应的错误消息")
    private String message;

    @ApiModelProperty("api请求成功时，此字段回传对应的数据")
    private T data;

    /**
     * 生成错误回复
     * 
     * @param <T>
     * @param status  错误码
     * @param message 错误信息
     * @return
     */
    public static <T> Result<T> error(String status, String message) {
        Result<T> result = new Result<T>();
        result.setStatus(status);
        result.setMessage(message);
        return result;
    }

    /**
     * 生成错误回复
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(RespondEnum respondEnum) {
        Result<T> result = new Result<T>();
        result.setStatus(respondEnum.getCode());
        result.setMessage(respondEnum.getMessage());
        return result;
    }

    /**
     * 生成正常回复（无附带信息）
     * 
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok() {
        Result<T> result = new Result<T>();
        result.setStatus(RespondEnum.OK.getCode());
        result.setMessage(RespondEnum.OK.getMessage());
        return result;
    }

    /**
     * 生成正常回复（有附带信息）
     * 
     * @param <T>
     * @param data
     * @return
     */
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<T>();
        result.setStatus(RespondEnum.OK.getCode());
        result.setMessage(RespondEnum.OK.getMessage());
        result.setData(data);
        return result;
    }
}
