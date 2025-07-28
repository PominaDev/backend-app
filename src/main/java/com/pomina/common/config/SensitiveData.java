package com.pomina.common.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Annotation is used to mark fields that contain sensitive data
@Target(ElementType.FIELD)
// This annotation is used to specify that the annotation should be retained at runtime
@Retention(RetentionPolicy.RUNTIME)
public @interface SensitiveData {}
