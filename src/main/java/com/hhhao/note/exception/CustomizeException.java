package com.hhhao.note.exception;

import com.hhhao.note.util.enums.RespondEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 自定义异常
 * 
 * @author luocc
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class CustomizeException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String code;

    public CustomizeException(String code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * 默认客户端异常
     * 
     * @param message
     */
    public CustomizeException(String message) {
        super(message);
        this.code = RespondEnum.BAD_REQUEST.getCode();
    }

    public CustomizeException(RespondEnum respondEnum) {
        super(respondEnum.getMessage());
        this.code = respondEnum.getCode();
    }
}
