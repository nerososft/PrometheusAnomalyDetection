package com.neroyang.anomalydetection.web.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2017/8/25
 * Time   下午12:40
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiMapping {
    String value();
}
