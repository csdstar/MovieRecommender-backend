package org.example.backend.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  // 表示该注解能放在方法参数上
@Retention(RetentionPolicy.RUNTIME)  // 运行时可读取该注解
public @interface CurrentUserId {
}

