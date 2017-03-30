package com.gateside.autotesting.Gat.dataobject;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckPointMethod
{
	public String description()default "";
	public String name() default "";
}
