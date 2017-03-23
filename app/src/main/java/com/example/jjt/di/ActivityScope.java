package com.example.jjt.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Activity 单例生命周期
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
