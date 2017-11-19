package com.dim.fff.socialnetwork.dataprovider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that class is data provider
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 19.11.17
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Client {}
