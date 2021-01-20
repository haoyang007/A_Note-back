package com.hhhao.note.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义用户注解自定义 <br>
 * 此注解在验证token通过后 <br>
 * 获取当前token包含用户
 * 
 * @author haoy
 *
 */
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUser {

}
