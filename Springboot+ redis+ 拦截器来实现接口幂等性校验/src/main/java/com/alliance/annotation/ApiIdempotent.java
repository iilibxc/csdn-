package com.alliance.annotation;

/**
 * ClassName:    ApiIdempotent
 * Package:    com.alliance.redistest
 * Description:
 * Datetime:    2020/1/14   10:56
 * Author:   XXXXX@XX.com
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在需要保证 接口幂等性 的Controller的方法上使用此注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiIdempotent {
}
