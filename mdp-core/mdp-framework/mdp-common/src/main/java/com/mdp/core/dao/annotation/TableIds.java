package com.mdp.core.dao.annotation;

import com.baomidou.mybatisplus.annotation.IdType;

import java.lang.annotation.*;

/**
 * 多主键扩展
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface TableIds {
    String value() default "";

    IdType type() default IdType.NONE;
}
